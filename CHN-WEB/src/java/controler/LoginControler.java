/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Button;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;


/**
 *
 * @author yusup
 */
public class LoginControler extends GenericForwardComposer {
    Textbox tbNama;
    Intbox tbUmur, tbId;
    Button buttonSubmit, buttomDelete, buttonEdit;
    
    @Override //akan diakses pertamakali di load
    
    public void doAfterCompose(Component comp) throws Exception {
        
        Class.forName("org.postgresql.Driver");
        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        System.out.println("");
        System.out.println("");
        System.out.println("Pertama kali masuk");
//        tbUserName.setValue("");
//        tbPassword.setValue("");
        
    }
  public void onClick$buttonSubmit()
         
  {
     
        String url = "jdbc:postgresql://localhost:5432/sekolah";
        String username = "postgres";
        String password = "postgres";
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            // Query untuk menambahkan data siswa
            String sql = "INSERT INTO siswaa (nama, umur)VALUES(?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            //MENGISI PARAMETER
            statement.setString(1 , tbNama.getValue()); //mengisi nama siswa
            statement.setInt(2, tbUmur.getValue()); //umur siswa
            
            //menjalankan query
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted>0){
                System.out.println("Data Sisiwa Berhasil ditambahkan!");
            }
            //enutup koneksi
            connection.close();
        }catch (SQLException e){
            System.out.println("Terjadi eror :" + e.getMessage());
        }
    }public void onClick$buttonDelete(){
            
         String url = "jdbc:postgresql://localhost:5432/sekolah";
        String username = "postgres";
        String password = "postgres";
        
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            // Query untuk menambahkan data siswa
            String sql = "DELETE FROM siswaa WHERE id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            //MENGISI PARAMETER
           
//            System.out.println("id");
            statement.setInt(1, tbId.getValue() ); //id siswa
            
            //menjalankan query
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted>0){
                System.out.println("berhasil diperbarui!");
            }
            //enutup koneksi
            connection.close();
        }catch (SQLException e){
            System.out.println("Terjadi eror :" + e.getMessage());
        }
}
    public void onClick$buttonEdit() {
         String url = "jdbc:postgresql://localhost:5432/sekolah";
        String username = "postgres";
        String password = "postgres";
        
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            // Query untuk menambahkan data siswa
            String sql = "UPDATE siswaa SET nama = ?,umur = ? WHERE id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            //MENGISI PARAMETER
//            System.out.println("nama baru");
            statement.setString(1, tbNama.getValue()); //mengisi nama siswa
//            System.out.println("umur ");
            statement.setInt(2, tbUmur.getValue()); //umur siswa
//            System.out.println("id");
            statement.setInt(3,tbId.getValue()); //id siswa
            
            //menjalankan query
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted>0){
                System.out.println("berhasil diperbarui!");
            }
            //enutup koneksi
            connection.close();
        }catch (SQLException e){
            System.out.println("Terjadi eror :" + e.getMessage());
        }
}
    }

        

        
    
    
    

