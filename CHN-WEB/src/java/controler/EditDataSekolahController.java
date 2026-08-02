/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Window;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import java.sql.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.zk.ui.Executions;
import util.ConnectionUtil;


/**
 *
 * @author yusup
 */
public class EditDataSekolahController extends GenericForwardComposer {
     Window windowEditSekolahData;
        Textbox tbnamasekolah, tbalamatsekolah;
        Intbox tbid_sekolah;
        String url = "jdbc:postgresql://localhost:5432/sekolah";
        String username = "postgres";
        String password = "postgres";
      

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        
    Map hashMap = Executions.getCurrent().getArg();
        String id_sekolah = (String) hashMap.get("id_sekolah");
        String alamatsekolah = (String) hashMap.get("alamatsekolah");
        String namasekolah = (String) hashMap.get("namasekolah");
        tbid_sekolah.setValue(Integer.valueOf(id_sekolah));
        tbnamasekolah.setValue(namasekolah);
        tbalamatsekolah.setValue(alamatsekolah);
        windowEditSekolahData.doModal();
    }
        public void onClick$buttonSave() {
            String namasekolah = tbnamasekolah.getValue();
            String alamatsekolah = tbalamatsekolah.getValue();
            int id_sekolah = tbid_sekolah.getValue();
            
            
            
        
//          try {
//            Class.forName("org.postgresql.Driver");
//            String url = "jdbc:postgresql://localhost:5432/sekolah";
//             String username = "postgres";
//             String password = "postgres";
//            Connection connection = DriverManager.getConnection(url, username, password);
//            String sql = "UPDATE sekolah SET namasekolah=?, alamatsekolah=? WHERE id_sekolah=?";
//            PreparedStatement statement = connection.prepareStatement(sql);
//
//            statement.setString(1, namasekolah);
//            statement.setString(2, alamatsekolah);
//            statement.setInt(3, id_sekolah);
//            
//           
//            
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

       ConnectionUtil.getInstance().testUpdateSekolah(namasekolah, alamatsekolah, id_sekolah);

        windowEditSekolahData.onClose();
    }
    
       public void onClick$buttonClose() {
        windowEditSekolahData.onClose();
        
    }
    
}
