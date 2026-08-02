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
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zk.ui.Executions;
import renderer.ShowGridRenderer;
import renderer.SekolahDataRenderer;
import renderer.ComboSekolahRenderer;
import vo.SiswaVo;
import vo.SekolahVO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author yusup
 */
public class ShowGridController extends GenericForwardComposer{ 
    Grid gridSiswa;
    Listbox listboxSekolahShowData;
    Button buttonAdd, buttonEdan, buttonHapud;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    prepareListSekolah();
    prepareList();
    }
    
    
    
    
    
    
    
     
    public void prepareList() throws ClassNotFoundException {
         try{
             Class.forName("org.postgresql.Driver");
             String url = "jdbc:postgresql://localhost:5432/sekolah";
        String username = "postgres";
        String password = "postgres";
             Connection connection = DriverManager.getConnection(url, username, password); 
             //QUERY UNTUK MEMBACA DATA SISWA
             String sql = "SELECT * FROM siswaa ORDER BY id";
             Statement statement = connection.createStatement();
             ResultSet resultSet= statement.executeQuery(sql);
             List<SiswaVo> listData = new ArrayList<SiswaVo>();
            
             
             //menampilkan data siswa
             while(resultSet.next()){
                 String id = resultSet.getString("id");
                 String nama =resultSet.getString("nama");
                 String alamat =resultSet.getString("alamat");
                 int umur = resultSet.getInt("umur");
                 int id_sekolah = resultSet.getInt("id_sekolah");
                 SiswaVo vo = new SiswaVo();
                 vo.setId(id);
                 vo.setNama(nama);
                 vo.setAlamat(alamat);
                 vo.setUmur(umur);
                 vo.setId_sekolah (id_sekolah);
                 listData.add(vo);
             }
             gridSiswa.setModel(new ListModelList<Object>(listData));
        gridSiswa.setRowRenderer(new ShowGridRenderer());
             //menutup koneksi
             connection.close();
         }catch(SQLException e){
             System.out.println("Terjadi error:" +e.getMessage());
         }
    
    
}public void onClick$buttonAdd() throws ClassNotFoundException {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("id", "123");
        Executions.createComponents("add_grid.zul", null, hashMap);
        
    }
public void onClick$buttonAdds() throws ClassNotFoundException {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("id", "123");
        Executions.createComponents("add_sekolah.zul", null, hashMap);
        

        prepareList();
    }
     public void onClick$buttonDeletes() throws ClassNotFoundException {
        SekolahVO vo = (SekolahVO) listboxSekolahShowData.getSelectedItem().getAttribute("data");
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("id_sekolah", String.valueOf(vo.getId_sekolah()));
        hashMap.put("namasekolah", vo.getNamasekolah());
        hashMap.put("alamatsekolah", vo.getalamatSekolah());
     
        Executions.createComponents("delete_sekolah.zul", null, hashMap);

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
  
    
    
       public void prepareListSekolah() throws ClassNotFoundException{
           Class.forName("org.postgresql.Driver");  
         try{
             Class.forName("org.postgresql.Driver");
            
             String url = "jdbc:postgresql://localhost:5432/sekolah";
        String username = "postgres";
        String password = "postgres";
             Connection connection = DriverManager.getConnection(url, username, password); 
             //QUERY UNTUK MEMBACA DATA SISWA
             String sql = "SELECT * FROM sekolah ORDER BY id_sekolah";
             Statement statementSelect = connection.createStatement();
             ResultSet resultSet= statementSelect.executeQuery(sql);
             List<SekolahVO> listDataSekolah = new ArrayList<SekolahVO>();
            
             
             //menampilkan data siswa
             while(resultSet.next()){
                
                 
                 SekolahVO vo = new SekolahVO();
                 vo.setId_sekolah(resultSet.getInt("id_sekolah"));
                 vo.setNamasekolah(resultSet.getString("namasekolah"));
                 vo.setalamatSekolah(resultSet.getString("alamatsekolah"));
                 listDataSekolah.add(vo);
                 connection.close();
        
             }
             listboxSekolahShowData.setModel(new ListModelList<Object>(listDataSekolah));
        listboxSekolahShowData.setItemRenderer(new SekolahDataRenderer());
             //menutup koneksi
            
         }catch(SQLException e){
             System.out.println("Terjadi error:" +e.getMessage());
         }
    
       }
}
