/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
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
import renderer.ShowPegawaiRenderer;
import vo.PegawaiVO;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


/**
 *
 * @author yusup
 */
public class ShowPegawaiController extends GenericForwardComposer{
    
Listbox listboxShowData;
Button buttonAdd, buttonEdit, ButtonDelete;
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        prepareList();
    }
    public void onClick$buttonAdd() throws ClassNotFoundException {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("id", "123");
        Executions.createComponents("add_pegawai1.zul", null, hashMap);

        prepareList();
      
    }

    public void onClick$buttonDelete() throws ClassNotFoundException {
        PegawaiVO vo = (PegawaiVO) listboxShowData.getSelectedItem().getAttribute("data");
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("id", String.valueOf(vo.getId()));
        Executions.createComponents("delete_pegawai.zul", null, hashMap);

        prepareList();
    }

    public void onClick$buttonEdit() throws ClassNotFoundException {
        PegawaiVO vo = (PegawaiVO) listboxShowData.getSelectedItem().getAttribute("data");
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", String.valueOf(vo.getId()));
        hashMap.put("nama", vo.getNama());
        hashMap.put("umur", String.valueOf(vo.getUmur()));
        hashMap.put("jabatan", vo.getJabatan());
        hashMap.put("status_aktif",vo.isStatus_aktif());
        hashMap.put("jenis_kelamin",vo.getJenis_kelamin());
        hashMap.put("tanggallahir",vo.getTanggal_masuk());
        

        Executions.createComponents("edit_pegawai.zul", null, hashMap);

        prepareList();
    }
   public void prepareList() throws ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/sekolah";
            String username = "postgres";
            String password = "postgres";
            Connection connection = DriverManager.getConnection(url, username, password);
            //QUERY UNTUK MEMBACA DATA SISWA
            String sql = "SELECT * FROM pegawai ORDER BY id";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<PegawaiVO> listData = new ArrayList<PegawaiVO>();

            //menampilkan data siswa
            while (resultSet.next()) {
//                try {

                    int id = resultSet.getInt("id");
                    String nama = resultSet.getString("nama");
                    int umur = resultSet.getInt("umur");
                    String jabatan = resultSet.getString("jabatan");
                    Boolean status_aktif = resultSet.getBoolean("status_aktif");
                    String jenis_kelamin = resultSet.getString("jenis_kelamin");
                    Date tanggal_masuk = resultSet.getDate("tanggal_masuk");
                    PegawaiVO vo = new PegawaiVO();
                    vo.setId(id);
                    vo.setNama(nama);
                    vo.setUmur(umur);
                    vo.setJabatan(jabatan);
                    vo.setStatus_aktif(status_aktif);
                    vo.setJenis_kelamin(jenis_kelamin);
                    vo.setTanggal_masuk(tanggal_masuk);
                    listData.add(vo);
//                } catch (SQLException e) {
//                }
            }
            listboxShowData.setModel(new ListModelList<Object>(listData));
            listboxShowData.setItemRenderer(new ShowPegawaiRenderer());
            //menutup koneksi
            connection.close();
        } catch (SQLException e) {
            System.out.println("Terjadi error:" + e.getMessage());
        }

    
}
}
