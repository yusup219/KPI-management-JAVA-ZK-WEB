/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import java.util.HashMap;
import java.util.Map;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Button;
import java.util.List;
import java.util.ArrayList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zk.ui.Executions;
import renderer.ShowDataRenderer;
import renderer.SekolahDataRenderer;
import renderer.ComboSekolahRenderer;
import vo.SiswaVo;
import vo.SekolahVO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import util.ConnectionUtil;

/**
 *
 * @author yusup
 */
public class ShowDataController extends GenericForwardComposer {

    
    Listbox listboxShowData, listboxSekolahShowData;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); 
//        List list = ConnectionUtil.getInstance().testConnection();
//        System.out.println("list =" + list.size());
//        for (Object obj : list) {
//            Object[] objArr = (Object[]) obj; 
//            System.out.println("bojArr =" + objArr[0]);
//            System.out.println("bojArr =" + objArr[1]);
//            System.out.println("bojArr =" + objArr[2]);
//            System.out.println("bojArr =" + objArr[3]);
//            
//        }
        
        prepareList();
        prepareListSekolah();
    }

    public void onClick$buttonAdd() throws ClassNotFoundException {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("id", "123");
        Executions.createComponents("add_pegawai1.zul", null, hashMap);

        prepareList();
        prepareListSekolah();
    }

    public void onClick$buttonDelete() throws ClassNotFoundException {
        SiswaVo vo = (SiswaVo) listboxShowData.getSelectedItem().getAttribute("data");
        
//        Map<String, Object> hashMap = new HashMap<String, Object>();
        ConnectionUtil.getInstance().testDelete(vo.getId());
//        hashMap.put("id", String.valueOf(vo.getId()));
//        hashMap.put("nama", vo.getNama());
//        hashMap.put("alamat", vo.getAlamat());
//        hashMap.put("umur", String.valueOf(vo.getUmur()));
//        Executions.createComponents("delete_data.zul", null, hashMap);

        prepareList();
    }

    public void onClick$buttonEdit() throws ClassNotFoundException {
        SiswaVo vo = (SiswaVo) listboxShowData.getSelectedItem().getAttribute("data");
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", String.valueOf(vo.getId()));
        hashMap.put("nama", vo.getNama());
        hashMap.put("umur", String.valueOf(vo.getUmur()));
        hashMap.put("alamat", vo.getAlamat());
        hashMap.put("statuslulus", vo.getStatuslulus());
        hashMap.put("id_sekolah", String.valueOf(vo.getId_sekolah()));
        hashMap.put("tanggallahir",vo.getTanggallahir());
        hashMap.put("jeniskelamin",vo.getJeniskelamin());

        Executions.createComponents("edit_data.zul", null, hashMap);

        prepareList();
    }

    public void prepareList() throws ClassNotFoundException {
//        try {
//            Class.forName("org.postgresql.Driver");
//            String url = "jdbc:postgresql://localhost:5432/sekolah";
//            String username = "postgres";
//            String password = "postgres";
//            Connection connection = DriverManager.getConnection(url, username, password);
            //QUERY UNTUK MEMBACA DATA SISWA
//            String sql = "SELECT * FROM siswaa ORDER BY id";
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
            List list = ConnectionUtil.getInstance().testConnection();
            System.out.println("list" +list.size());
            List<SiswaVo> listData = new ArrayList<SiswaVo>();
            for (Object obj: list) {
                 Object[] objArr = (Object[]) obj; 
            System.out.println("bojArr =" + objArr[0]);
            System.out.println("bojArr =" + objArr[1]);
            System.out.println("bojArr =" + objArr[2]);
            System.out.println("bojArr =" + objArr[4]);
            System.out.println("bojArr =" + objArr[5]);
            System.out.println("bojArr =" + objArr[6]);
            System.out.println("bojArr =" + objArr[7]);
            

            //menampilkan data siswa
//            while (resultSet.next()) {
//                try {
//
//                    int id = resultSet.getInt("id");
//                    String nama = resultSet.getString("nama");
//                    String alamat = resultSet.getString("alamat");
//                    int umur = resultSet.getInt("umur");
//                    String id_sekolah = resultSet.getString("id_sekolah");
//                    Date tanggallahir = resultSet.getDate("tanggallahir");
//                    String statuslulus = resultSet.getString("statuslulus");
//                    String jeniskelamin = resultSet.getString("jeniskelamin");
                    SiswaVo vo = new SiswaVo();
                    String id = (String) objArr[0];
                    String nama = (String) objArr[1];
                    String alamat = (String) objArr[2];
                    int umur = (Integer) objArr[3];
                    int id_sekolah = objArr[4] != null ? (Integer) objArr[4] : 0;
                    Date tanggallahir = (Date) objArr[5];
                    String statuslulus = (String) objArr[6];
                    String jeniskelamin = (String) objArr[7];
                    
                    vo.setId(id);
                    vo.setNama(nama);
                    vo.setAlamat(alamat);
                    vo.setUmur(umur);
                    vo.setId_sekolah(id_sekolah);
                    vo.setTanggallahir(tanggallahir);
                    vo.setJeniskelamin(jeniskelamin);
                    vo.setStatuslulus(statuslulus);
                    listData.add(vo);
            }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
            listboxShowData.setModel(new ListModelList<Object>(listData));
            listboxShowData.setItemRenderer(new ShowDataRenderer());
            //menutup koneksi
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Terjadi error:" + e.getMessage());
//        }

    }
    public void onClick$buttonLoguut() throws ClassNotFoundException {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("id", "123");
        Executions.createComponents("add_sekolah.zul", null, hashMap);
           prepareList();
    }

   
    public void onClick$buttonDeletes() throws ClassNotFoundException {
        SekolahVO vo = (SekolahVO) listboxSekolahShowData.getSelectedItem().getAttribute("data");
      ConnectionUtil.getInstance().testDeleteSekolah(vo.getId_sekolah());

        prepareList();
    }

    public void onClick$buttonEdits() throws ClassNotFoundException {
        SekolahVO vo = (SekolahVO) listboxSekolahShowData.getSelectedItem().getAttribute("data");
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("id_sekolah", String.valueOf(vo.getId_sekolah()));
        hashMap.put("namasekolah", vo.getNamasekolah());
        hashMap.put("alamatsekolah", vo.getalamatSekolah());

        Executions.createComponents("edit_sekolah.zul", null, hashMap);

        prepareList();
    }

    public void prepareListSekolah() throws ClassNotFoundException {
//        Class.forName("org.postgresql.Driver");
//        try {
//            Class.forName("org.postgresql.Driver");
//
//            String url = "jdbc:postgresql://localhost:5432/sekolah";
//            String username = "postgres";
//            String password = "postgres";
//            Connection connection = DriverManager.getConnection(url, username, password);
//            //QUERY UNTUK MEMBACA DATA SISWA
//            String sql = "SELECT * FROM sekolah ORDER BY id_sekolah";
//            Statement statementSelect = connection.createStatement();
//            ResultSet resultSet = statementSelect.executeQuery(sql);
            List list = ConnectionUtil.getInstance().testSekolah();
            System.out.println("list" +list.size());
            List<SekolahVO> listDataSekolah = new ArrayList<SekolahVO>();
            for (Object obj: list) {
                Object[] objArr = (Object[]) obj; 
                System.out.println("bojArr =" + objArr[0]);
                System.out.println("bojArr =" + objArr[1]);
                System.out.println("bojArr =" + objArr[2]);

                SekolahVO vo = new SekolahVO();
                int id_sekolah = objArr[0] != null ? (Integer) objArr[0] : 0;
                String namasekolah = (String) objArr[1];
                String alamatsekolah = (String) objArr[2];
                vo.setId_sekolah(id_sekolah);
                vo.setNamasekolah(namasekolah);
                vo.setalamatSekolah(alamatsekolah);
                listDataSekolah.add(vo);
            }
            listboxSekolahShowData.setModel(new ListModelList<Object>(listDataSekolah));
            listboxSekolahShowData.setItemRenderer(new SekolahDataRenderer());
            Map<String, String> anjay = new HashMap<>();
            anjay.put("okelah", "anjay");
            System.out.println("anjay = " + anjay);
}
}