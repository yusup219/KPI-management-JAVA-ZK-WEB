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
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import vo.KendaraanVO;
import renderer.ShowKendaraanRenderer;
import java.math.BigDecimal;

/**
 *
 * @author yusup
 */
public class ShowKendaraanController extends GenericForwardComposer{
    
    Button buttonAdd, buttonEdit, buttonDelete;
    Listbox listboxShowKendaraan;
    
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        prepareList();
    } 
    public void onClick$buttonAdd() throws ClassNotFoundException {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("id", "123");
        Executions.createComponents("add_kendaraan.zul", null, hashMap);

        prepareList();
    }
    public void onClick$buttonEdit()  throws ClassNotFoundException{
        KendaraanVO vo = (KendaraanVO)listboxShowKendaraan.getSelectedItem().getAttribute("data");
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", String.valueOf(vo.getId()));
        hashMap.put("nomor_polisi", vo.getNomor_polisi());
        hashMap.put("merk", vo.getMerk());
        hashMap.put("tipe", vo.getTipe());
        hashMap.put("tahun_pembuatan", String.valueOf(vo.getTahun_pembuatan()));
        hashMap.put("merk", vo.isStatus_tersedia());
        hashMap.put("harga_per_hari", String.valueOf(vo.getHarga_per_hari()));
        
        Executions.createComponents("edit_kendaraan.zul", null, hashMap);
        
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
            String sql = "SELECT * FROM kendaraan ORDER BY id";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<KendaraanVO> listData = new ArrayList<KendaraanVO>();

            //menampilkan data siswa
            while (resultSet.next()) {
//                try {

                    int id = resultSet.getInt("id");
                    String nomor_polisi = resultSet.getString("nomor_polisi");
                    int tahun_pembuatan = resultSet.getInt("tahun_pembuatan");
                    String merk = resultSet.getString("merk");
                    Boolean status_tersedia = resultSet.getBoolean("status_tersedia");
                    String tipe = resultSet.getString("tipe");
                    BigDecimal harga_per_hari = resultSet.getBigDecimal("harga_per_hari");
                    KendaraanVO vo = new KendaraanVO();
                    vo.setId(id);
                    vo.setNomor_polisi(nomor_polisi);
                    vo.setTahun_pembuatan(tahun_pembuatan);
                    vo.setMerk(merk);
                    vo.setStatus_tersedia(status_tersedia);
                    vo.setTipe(tipe);
                    vo.setHarga_per_hari(harga_per_hari);
                    listData.add(vo);
//                } catch (SQLException e) {
//                }
            }
            listboxShowKendaraan.setModel(new ListModelList<Object>(listData));
            listboxShowKendaraan.setItemRenderer(new ShowKendaraanRenderer());
            //menutup koneksi
            connection.close();
        } catch (SQLException e) {
            System.out.println("Terjadi error:" + e.getMessage());
        }
    
}}
