/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Window;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;
import renderer.ComboSekolahRenderer;
import vo.SekolahVO;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import java.util.Date;
import util.ConnectionUtil;
import vo.SiswaVo;

/**
 *
 * @author yusup
 */
public class EditDataController extends GenericForwardComposer {

    Window windowEditData;
    Textbox tbNama, tbAlamat;
    Intbox tbUmur, tbid;
    Combobox cbSekolahEdit;
    Datebox dbStart;
    Checkbox checkActive;
    Radiogroup gender;
    String url = "jdbc:postgresql://localhost:5432/sekolah";
    String username = "postgres";
    String password = "postgres";
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
//        Map map = Executions.getCurrent().getArg();
//        System.out.println("map = " + map);
//        if (map.containsKey("id")) {
//            System.out.println("map.get(\"id\") = " + map.get("id"));
//            tbid.setValue((String) map.get("id"));
        Map hashMap = Executions.getCurrent().getArg();
        String id = (String) hashMap.get("id");
        String nama = (String) hashMap.get("nama");
        String alamat = (String) hashMap.get("alamat");
        String umur = (String) hashMap.get("umur");
        String idSekolah = (String) hashMap.get("id_sekolah");
        Date tanggallahir = (Date) hashMap.get("tanggallahir");
        String jeniskelamin = (String) hashMap.get("jeniskelamin");
        String statuslulus = (String) hashMap.get("statuslulus");
        tbid.setValue(Integer.valueOf(id));
        tbNama.setValue(nama);
        tbAlamat.setValue(alamat);
        tbUmur.setValue(Integer.valueOf(umur));
        dbStart.setValue(tanggallahir);
//        gender.setseValue(String.valueOf(jeniskelamin));
        List <Radio> listRadio = gender.getItems();
        int i = 0;
        for (Radio radio : listRadio) {
            if (jeniskelamin != null && radio.getValue().equalsIgnoreCase(jeniskelamin)) {
//                jika variabel tidaksamadengan null dan valueyang didapat dari radio sama dengan(mengabaikan case)variabel
                gender.setSelectedIndex(i);
//                maka akan memilih index i
                break;
            }
            i++;
        }
        checkActive.setChecked(statuslulus != null && statuslulus.equalsIgnoreCase("Y") ? true : false);

        cbSekolahEdit.addEventListener("onAfterRender", new EventListener() {
            @Override
            public void onEvent(Event t) throws Exception {
                setComboValue(idSekolah, cbSekolahEdit);
            }
        });
        prepareListSekolahCombobox();
        windowEditData.doModal();
    }

    protected void setComboValue(String val, Combobox combo) {
        List<Comboitem> listComboItem = combo.getItems();
        if (listComboItem != null && listComboItem.size() > 0) {
            for (int i = 0; i < listComboItem.size(); i++) {
                Comboitem comboitem = listComboItem.get(i);
                if (comboitem.getValue() != null && val != null && val.equalsIgnoreCase(comboitem.getValue().toString())) {
                    combo.setSelectedIndex(i);
                    break;
                }
            }
        }

    }

    public void onClick$buttonSave() {
        String nama = tbNama.getValue();
        String alamat = tbAlamat.getValue();
        int umur = tbUmur.getValue();
        int id = tbid.getValue();
//        Date tanggallahir = dbStart.getValue();
        boolean ab = checkActive.isChecked();
        String statuslulus = ab ? "Y" : "N";

        String jeniskelamin = gender.getSelectedItem().getValue();

        Integer namaSekolah = null;
        if (cbSekolahEdit.getSelectedItem() != null) {
            namaSekolah = (Integer) cbSekolahEdit.getSelectedItem().getValue();
        }
        
        Date tanggallahir = null;
        if (dbStart.getValue() != null) {
            tanggallahir = (Date) dbStart.getValue();
        }
//        Date tanggallahir = null;
//        if (dbStart.getValue() != null) {
//            tanggallahir = (Date) dbStart.getValue();
//        }

//       boolean statuslulus = false;
//        if (checkActive.isChecked() != false) {
//            statuslulus = (boolean) checkActive.isChecked();
//        }
//       Integer jeniskelamin = null;
//        if (gender.getSelectedItem() != null) {
//            jeniskelamin = (Integer) gender.getSelectedIndex();
//        }
            ConnectionUtil.getInstance().testUpdate(nama, alamat, id, umur, namaSekolah, tanggallahir, statuslulus, jeniskelamin);
//            System.out.println("list" +list.size());
//            List<SiswaVo> listData = new ArrayList<SiswaVo>();
//            SiswaVo vo = new SiswaVo();
//             vo.setId(id);
//                    vo.setNama(nama);
//                    vo.setAlamat(alamat);
//                    vo.setUmur(umur);
//                    vo.setId_sekolah(namaSekolah);
//                    vo.setTanggallahir(tanggallahir);
//                    vo.setJeniskelamin(jeniskelamin);
//                    vo.setStatuslulus(statuslulus);
//                    
//                    listData.add(vo);
//        try {
//            Class.forName("org.postgresql.Driver");
//
//            String url = "jdbc:postgresql://localhost:5432/sekolah";
//            String username = "postgres";
//            String password = "postgres";
//            Connection connection = DriverManager.getConnection(url, username, password);
//            String sql = "UPDATE siswaa SET nama=?, alamat=?, umur=?, id_sekolah=?, tanggallahir=?, jeniskelamin=?, statuslulus=?   WHERE id=?";
//            PreparedStatement statement = connection.prepareStatement(sql);
//
//            statement.setString(1, nama);
//            statement.setString(2, alamat);
//            statement.setInt(3, umur);
//            statement.setInt(4, namaSekolah);
//            statement.setDate(5, new java.sql.Date(tanggallahir.getTime()));
//            statement.setString(6, jeniskelamin);
//            statement.setString(7, statuslulus);
//            statement.setInt(8, id);
//
//            int rowsInserted = statement.executeUpdate();
//            if (rowsInserted > 0) {
//                System.out.println("Data siswa berhasil ditambahkan!");
//            }
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Terjadi error: " + e.getMessage());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(AddDataController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        windowEditData.onClose();
    }

    public void onClick$buttonClose() {
        windowEditData.onClose();

    }

    public void prepareListSekolahCombobox() throws Exception {
//        try {
//            Class.forName("org.postgresql.Driver");
//
//            Connection connection = DriverManager.getConnection(url, username, password);
//            //QUERY UNTUK MEMBACA DATA SISWA
//            String sql = "SELECT * FROM sekolah ORDER BY id_sekolah";
//            Statement statementSelect = connection.createStatement();
//            ResultSet resultSet = statementSelect.executeQuery(sql);
//            List<SekolahVO> ListData = new ArrayList<SekolahVO>();
//
//            //menampilkan data siswa
//            while (resultSet.next()) {
//                SekolahVO vo = new SekolahVO();
//                vo.setId_sekolah(resultSet.getInt("id_sekolah"));
//                vo.setNamasekolah(resultSet.getString("namasekolah"));
//                vo.setalamatSekolah(resultSet.getString("alamatsekolah"));
//                ListData.add(vo);
//                connection.close();
//            }
//            //menutup koneksi

List list = ConnectionUtil.getInstance().testSekolah();
            System.out.println("list" +list.size());
            List<SekolahVO> listDataSekolah = new ArrayList<SekolahVO>();
             for (Object obj: list) {
                 Object[] objArr = (Object[]) obj; 
            System.out.println("bojArr =" + objArr[0]);
            System.out.println("bojArr =" + objArr[1]);
            System.out.println("bojArr =" + objArr[2]);

            //menampilkan data siswa
//            while (resultSet.next()) {

                SekolahVO vo = new SekolahVO();
                int id_sekolah = (Integer) objArr[0];
                String namasekolah = (String) objArr[1];
                String alamatsekolah = (String) objArr[2];
                vo.setId_sekolah(id_sekolah);
                vo.setNamasekolah(namasekolah);
                vo.setalamatSekolah(alamatsekolah);
//                vo.setId_sekolah("id_sekolah"));
//                vo.setNamasekolah(resultSet.getString("namasekolah"));
//                vo.setalamatSekolah(resultSet.getString("alamatsekolah"));
                listDataSekolah.add(vo);
//                connection.close();
             }
//            }

            cbSekolahEdit.setModel(new ListModelList<SekolahVO>(listDataSekolah));
            cbSekolahEdit.setItemRenderer(new ComboSekolahRenderer());
//        } catch (SQLException e) {
//            System.out.println("Terjadi error:" + e.getMessage());
//        }

    }
}
