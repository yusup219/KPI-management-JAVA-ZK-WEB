package controler;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;
import java.util.List;
import java.util.Date; 
import java.text.SimpleDateFormat;
import util.ConnectionUtil;
import javax.servlet.http.HttpSession;
import org.zkoss.zk.ui.Executions;

// Import Java I/O Stream
import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Import MANDIRI Apache POI
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class KpiController extends GenericForwardComposer {

    private Textbox txtCari;
    private Datebox dbAwal;
    private Datebox dbAkhir;
    private Listbox listboxKpi;
    private Button btnExport;
    private Button btnFilter;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        tampilkanDataKpi("", null, null);

        if (!isAdmin()) {
            btnExport.setVisible(false);
        }
    }

    private boolean isAdmin() {
        HttpSession session = (HttpSession) Executions.getCurrent().getSession().getNativeSession();
        String role = (String) session.getAttribute("userRole");
        return "admin".equals(role);
    }

    private void tampilkanDataKpi(String keyword, Date tglAwal, Date tglAkhir) {
        listboxKpi.getItems().clear();

        HttpSession session = (HttpSession) Executions.getCurrent().getSession().getNativeSession();
        String role = (String) session.getAttribute("userRole");
        boolean isAdmin = "admin".equals(role);

        try {
            List<Object[]> listKpi = ConnectionUtil.getInstance().testConnectionKpi(keyword, tglAwal, tglAkhir);

            for (Object[] row : listKpi) {
                final String id = row[0].toString();
                String nama_Pegawai = row[1].toString();
                double hadirTarget = Double.parseDouble(row[2].toString());
                double hadirAktual = Double.parseDouble(row[3].toString());
                double bobot = Double.parseDouble(row[4].toString());

                java.util.Date tglDb = (java.util.Date) row[5];
                String tglFormat = "-";

                if (tglDb != null) {
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
                    tglFormat = sdf.format(tglDb);
                }

                double skorAkhir = 0;
                if (hadirAktual > 0 && hadirTarget > 0) {
                    skorAkhir = (hadirAktual / hadirTarget) * bobot;
                }

                String indexNilai = "0";
                if (skorAkhir >= 85.01 && skorAkhir <= 100.0) {
                    indexNilai = "A";
                } else if (skorAkhir >= 61.0 && skorAkhir <= 85.0) {
                    indexNilai = "B";
                } else if (skorAkhir >= 41.0 && skorAkhir <= 60.0) {
                    indexNilai = "C";
                } else if (skorAkhir >= 1.0 && skorAkhir <= 41.0) {
                    indexNilai = "D";
                } else {
                    indexNilai = "";
                }

                Listitem li = new Listitem();
                li.appendChild(new Listcell(id));
                li.appendChild(new Listcell(nama_Pegawai));
                li.appendChild(new Listcell(String.valueOf((int) hadirTarget) + " Hari"));

                Listcell cellInput = new Listcell();
                final Intbox ibHadir = new Intbox((int) hadirAktual);
                ibHadir.setWidth("80px");
                if (!isAdmin()) {
                    ibHadir.setReadonly(true);
                    ibHadir.setDisabled(true);

                }
                cellInput.appendChild(ibHadir);
                li.appendChild(cellInput);

                li.appendChild(new Listcell(String.valueOf((int) bobot) + "%"));
                li.appendChild(new Listcell(String.format("%.2f", skorAkhir)));
                li.appendChild(new Listcell(indexNilai));

                Listcell cellTanggal = new Listcell();
                final Datebox dbTanggalInput = new Datebox(tglDb);
                dbTanggalInput.setFormat("dd-MM-yyyy");
                dbTanggalInput.setWidth("120px");
                if (!isAdmin) {
                    dbTanggalInput.setReadonly(true);
                    dbTanggalInput.setDisabled(true);
                }
                cellTanggal.appendChild(dbTanggalInput);
                li.appendChild(cellTanggal);

                Listcell cellAksi = new Listcell();
                Button btnSimpan = new Button("Simpan");
                if (isAdmin) {
                    btnSimpan.setStyle("background:#1e3c72; color:white; cursor:pointer; font-size:11px;");

                    btnSimpan.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
                        @Override
                        public void onEvent(Event event) throws Exception {
                            Integer angkaBaru = ibHadir.getValue();
                            if (angkaBaru == null) {
                                angkaBaru = 0;
                            }
                            Date tanggalBaru = dbTanggalInput.getValue();
                            if (tanggalBaru == null) {
                                tanggalBaru = new Date();
                            }
                            ConnectionUtil.getInstance().testUpdateHadirKpi(id, angkaBaru, tanggalBaru);

                            Messagebox.show("Data KPI Berhasil Diupdate!", "Sukses", Messagebox.OK, Messagebox.INFORMATION);

                            tampilkanDataKpi(txtCari.getValue().trim(), dbAwal.getValue(), dbAkhir.getValue());
                        }
                    });
                } else {
                    btnSimpan.setDisabled(true);
                }
                cellAksi.appendChild(btnSimpan);
                li.appendChild(cellAksi);

                listboxKpi.appendChild(li);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick$btnCari() {
        String keyword = txtCari != null ? txtCari.getValue().trim() : "";
        Date tglAwal = dbAwal.getValue();
        Date tglAkhir = dbAkhir.getValue();

        if (tglAwal == null || tglAkhir == null) {
            Messagebox.show("Silakan tentukan Periode Penilaian (Tanggal Awal & Akhir) terlebih dahulu!", "Peringatan", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }

        if (tglAwal.after(tglAkhir)) {
            Messagebox.show("Tanggal Awal tidak boleh melebihi Tanggal Akhir!", "Peringatan", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }

        tampilkanDataKpi(keyword, tglAwal, tglAkhir);
    }

    public void onClick$btnRefresh() {
        if (txtCari != null) {
            txtCari.setValue("");
        }
        if (dbAwal != null) {
            dbAwal.setValue(null);
        }
        if (dbAkhir != null) {
            dbAkhir.setValue(null);
        }

        tampilkanDataKpi("", null, null);
    }

    /////    HALAMAN EXCEL /////
    public void onClick$btnExport() {
        String keyword = txtCari != null ? txtCari.getValue().trim() : "";
        Date tglAwal = dbAwal.getValue();
        Date tglAkhir = dbAkhir.getValue();

        if (tglAwal != null && tglAkhir != null) {
            if (tglAwal.after(tglAkhir)) {
                Messagebox.show("Tanggal Awal tidak boleh melebihi Tanggal Akhir!", "Peringatan", Messagebox.OK, Messagebox.EXCLAMATION);
                return;
            }
        }

        try {
            List<Object[]> listKpi = ConnectionUtil.getInstance().testConnectionKpi(keyword, tglAwal, tglAkhir);

            if (listKpi == null || listKpi.isEmpty()) {
                Messagebox.show("Tidak ada data KPI pada periode ini yang bisa diexport!", "Peringatan", Messagebox.OK, Messagebox.EXCLAMATION);
                return;
            }

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Laporan KPI");

            Font titleFont = workbook.createFont();
            titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
            titleFont.setFontHeightInPoints((short) 14);

            Font subTitleFont = workbook.createFont();
            subTitleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
            subTitleFont.setFontHeightInPoints((short) 11);

            Font headerFont = workbook.createFont();
            headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerFont.setFontHeightInPoints((short) 11);

            Font dataFont = workbook.createFont();
            dataFont.setFontHeightInPoints((short) 10);

            CellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setFont(titleFont);

            CellStyle subTitleStyle = workbook.createCellStyle();
            subTitleStyle.setFont(subTitleFont);

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            headerCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
            headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            headerCellStyle.setBorderTop(CellStyle.BORDER_THIN);
            headerCellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
            headerCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
            headerCellStyle.setBorderRight(CellStyle.BORDER_THIN);

            CellStyle cellStyleText = workbook.createCellStyle();
            cellStyleText.setFont(dataFont);
            cellStyleText.setBorderTop(CellStyle.BORDER_THIN);
            cellStyleText.setBorderBottom(CellStyle.BORDER_THIN);
            cellStyleText.setBorderLeft(CellStyle.BORDER_THIN);
            cellStyleText.setBorderRight(CellStyle.BORDER_THIN);
            cellStyleText.setAlignment(CellStyle.ALIGN_LEFT);

            CellStyle cellStyleNum = workbook.createCellStyle();
            cellStyleNum.cloneStyleFrom(cellStyleText);
            cellStyleNum.setAlignment(CellStyle.ALIGN_RIGHT);

            CellStyle cellStyleDecimal = workbook.createCellStyle();
            cellStyleDecimal.cloneStyleFrom(cellStyleText);
            cellStyleDecimal.setAlignment(CellStyle.ALIGN_RIGHT);
            cellStyleDecimal.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));

            CellStyle cellStyleCenter = workbook.createCellStyle();
            cellStyleCenter.cloneStyleFrom(cellStyleText);
            cellStyleCenter.setAlignment(CellStyle.ALIGN_CENTER);

            String tahunCetak;
            if (tglAwal != null && tglAkhir != null) {
                java.text.SimpleDateFormat formatTahun = new java.text.SimpleDateFormat("yyyy");
                String thnAwal = formatTahun.format(tglAwal);
                String thnAkhir = formatTahun.format(tglAkhir);
                if (thnAwal.equals(thnAkhir)) {
                    tahunCetak = thnAwal;
                } else {
                    tahunCetak = thnAwal + " - " + thnAkhir;
                }
            } else {
                tahunCetak = "Semua Periode";
            }

            Row rowJudul = sheet.createRow(0);
            Cell cellJudul = rowJudul.createCell(0);
            cellJudul.setCellValue("LAPORAN PENILAIAN KINERJA (KPI) PEGAWAI");
            cellJudul.setCellStyle(titleStyle);

            Row rowTahun = sheet.createRow(1);
            Cell cellTahun = rowTahun.createCell(0);
            cellTahun.setCellValue("Tahun Penilaian: " + tahunCetak);
            cellTahun.setCellStyle(subTitleStyle);

            sheet.createRow(2);

            String[] headers = {"No", "ID KPI", "Nama Pegawai", "Hadir Target", "Hadir Aktual", "Bobot", "Skor Akhir", "Index Nilai", "Tanggal Penilaian"};
            Row headerRow = sheet.createRow(3);
            headerRow.setHeightInPoints(25);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowIdx = 4;
            int noUrut = 1;
            for (Object[] row : listKpi) {
                Row excelRow = sheet.createRow(rowIdx++);
                excelRow.setHeightInPoints(18);

                String id = row[0].toString();
                String namaPegawai = row[1].toString();
                double hadirTarget = Double.parseDouble(row[2].toString());
                double hadirAktual = Double.parseDouble(row[3].toString());
                double bobot = Double.parseDouble(row[4].toString());

                java.util.Date tglDb = (java.util.Date) row[5];
                String tglFormat = "-";
                if (tglDb != null) {
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
                    tglFormat = sdf.format(tglDb);
                }

                double skorAkhir = 0;
                if (hadirAktual > 0 && hadirTarget > 0) {
                    skorAkhir = (hadirAktual / hadirTarget) * bobot;
                }

                String indexNilai = "";
                if (skorAkhir >= 85.01 && skorAkhir <= 100.0) {
                    indexNilai = "A";
                } else if (skorAkhir >= 61.0 && skorAkhir <= 85.0) {
                    indexNilai = "B";
                } else if (skorAkhir >= 41.0 && skorAkhir <= 60.0) {
                    indexNilai = "C";
                } else if (skorAkhir >= 1.0 && skorAkhir <= 41.0) {
                    indexNilai = "D";
                }

                Cell cellNo = excelRow.createCell(0);
                cellNo.setCellValue(noUrut++);
                cellNo.setCellStyle(cellStyleCenter);

                Cell cellId = excelRow.createCell(1);
                cellId.setCellValue(id);
                cellId.setCellStyle(cellStyleCenter);

                Cell cellNama = excelRow.createCell(2);
                cellNama.setCellValue(namaPegawai);
                cellNama.setCellStyle(cellStyleText);

                Cell cellTarget = excelRow.createCell(3);
                cellTarget.setCellValue((int) hadirTarget);
                cellTarget.setCellStyle(cellStyleNum);

                Cell cellAktual = excelRow.createCell(4);
                cellAktual.setCellValue((int) hadirAktual);
                cellAktual.setCellStyle(cellStyleNum);

                Cell cellBobot = excelRow.createCell(5);
                cellBobot.setCellValue((int) bobot);
                cellBobot.setCellStyle(cellStyleNum);

                Cell cellSkor = excelRow.createCell(6);
                cellSkor.setCellValue(skorAkhir);
                cellSkor.setCellStyle(cellStyleDecimal);

                Cell cellIndex = excelRow.createCell(7);
                cellIndex.setCellValue(indexNilai);
                cellIndex.setCellStyle(cellStyleCenter);

                Cell cellTgl = excelRow.createCell(8);
                cellTgl.setCellValue(tglFormat);
                cellTgl.setCellStyle(cellStyleCenter);
            }

            sheet.setColumnWidth(0, 1500);
            for (int i = 1; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);

            Filedownload.save(out.toByteArray(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "Laporan_KPI_Pegawai.xlsx");

        } catch (IOException e) {
            e.printStackTrace();
            Messagebox.show("Terjadi kesalahan saat memproses data Excel: " + e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
        }
    }

    public void onClick$btnFilter() {
        prosesFilter();
    }

    private void prosesFilter() {
        String keyword = txtCari != null ? txtCari.getValue().trim() : "";
        Date tglAwal = dbAwal.getValue();
        Date tglAkhir = dbAkhir.getValue();

        if (tglAwal == null || tglAkhir == null) {
            Messagebox.show("Silakan tentukan Periode Penilaian (Tanggal Awal & Akhir) terlebih dahulu!", "Peringatan", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }

        if (tglAwal.after(tglAkhir)) {
            Messagebox.show("Tanggal Awal tidak boleh melebihi Tanggal Akhir!", "Peringatan", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }

        tampilkanDataKpi(keyword, tglAwal, tglAkhir);
    }
}
