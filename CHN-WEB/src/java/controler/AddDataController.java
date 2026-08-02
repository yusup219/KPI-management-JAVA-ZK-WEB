/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Window;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.SekolahVO;
import renderer.ComboSekolahRenderer;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Radio;

import renderer.SekolahDataRenderer;
import util.ConnectionUtil;
import util.IdUtil;
import vo.SiswaVo;

/**
 *
 * @author yusup
 */
public class AddDataController extends GenericForwardComposer {

    Window windowAddData;
    Textbox tbNama, tbAlamat;
    Intbox  tbUmur;
    Combobox cbSekolah;
    Datebox dbStart;
    Checkbox checkActive;
    Radiogroup gender;
    //menghubungkan ke database
    String url = "jdbc:postgresql://localhost:5432/sekolah";
    String username = "postgres";
    String password = "postgres";

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        prepareListSekolahCombobox();
  
    }

    public void onClick$buttonSave() {
        String id = IdUtil.generateId();
        String nama = tbNama.getValue();
        String alamat = tbAlamat.getValue();
        int umur = tbUmur.getValue();
        Date tanggallahir = dbStart.getValue();
//        
//        dibuatstring
        boolean ab = checkActive != null && checkActive.isChecked();
        String statuslulus = ab ? "Y" : "N";
        int cd = gender.getSelectedIndex();
        String jeniskelamin = cd >0? "M" : "F";
        
        Integer id_sekolah = null;
        if (cbSekolah.getSelectedItem() != null) {
            id_sekolah = cbSekolah.getSelectedItem().getValue();
        }
            ConnectionUtil.getInstance().testInsert(id, nama, alamat, umur, id_sekolah, tanggallahir, statuslulus, jeniskelamin );
//            System.out.println("list" +list.size());
//            List<SiswaVo> listData = new ArrayList<>();
//            SiswaVo vo = new SiswaVo();
//            vo.setId(id);
//                    vo.setNama(nama);
//                    vo.setAlamat(alamat);
//                    vo.setUmur(umur);
//                    vo.setId_sekolah(id_sekolah);
//                    vo.setTanggallahir(tanggallahir);
//                    vo.setJeniskelamin(jeniskelamin);
//                    vo.setStatuslulus(statuslulus);
//                    
//                    listData.add(vo);
            
//          
//        try {
//            Class.forName("org.postgresql.Driver");
//            Connection connection = DriverManager.getConnection(url, username, password);
//            String sql = "INSERT INTO siswaa (id, nama, alamat, umur, id_sekolah, tanggallahir, statuslulus, jeniskelamin) VALUES (?, ?, ?, ?, ?,?,?,?)";
//            PreparedStatement statement = connection.prepareStatement(sql);

//            statement.setInt(1, id);
//            statement.setString(2, nama);
//            statement.setString(3, alamat);
//            statement.setInt(4, umur);
//            statement.setInt(5, namaSekolah);
//            statement.setDate(6, new java.sql.Date(tanggallahir.getTime()));
//            statement.setString(7, statuslulus);
//            statement.setString(8, jeniskelamin);

//            int rowsInserted = statement.executeUpdate();
//            if (rowsInserted > 0) {
//                System.out.println("Data siswa berhasil ditambahkan!");
//            }
//
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Terjadi error: " + e.getMessage());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(AddDataController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        windowAddData.onClose();
    }

    public void onClick$buttonClose() {
        windowAddData.onClose();
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
            //menutup koneksi
            
cbSekolah.setModel(new ListModelList<SekolahVO>(listDataSekolah));
        cbSekolah.setItemRenderer(new ComboSekolahRenderer());
//        } catch (SQLException e) {
//            System.out.println("Terjadi error:" + e.getMessage());
//        }
        
    }
   

}
