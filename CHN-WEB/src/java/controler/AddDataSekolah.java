/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import util.ConnectionUtil;


/**
 *
 * @author yusup
 */
public class AddDataSekolah extends GenericForwardComposer {
    Window windowSekolahAddData;
    Textbox tbasalsekolah, tbalamatSekolah;
    Intbox tbid;
    
    
//    menghubungkan ke database
//    String url = "jdbc:postgresql://localhost:5432/sekolah";
//    String username = "postgres";
//    String password = "postgres";;
   

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        
        windowSekolahAddData.doModal();
    }
    public void onClick$buttonSave() {
        int id = tbid.getValue();
        String namasekolah = tbasalsekolah.getValue();
        String alamatsekolah = tbalamatSekolah.getValue();
        
       
        
        
//          try {
//            Class.forName("org.postgresql.Driver");
//            Connection connection = DriverManager.getConnection(url, username, password);
//            String sql = "INSERT INTO sekolah (id_sekolah, namasekolah, alamatsekolah) VALUES (?, ?, ?)";
//            PreparedStatement statement = connection.prepareStatement(sql);
ConnectionUtil.getInstance().testInsertSEkolah(namasekolah, alamatsekolah, id);

          
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Terjadi error: " + e.getMessage());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(AddDataController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        windowSekolahAddData.onClose();
    }
    
    
    
    
    
    
       public void onClick$buttonClose() {
        windowSekolahAddData.onClose();
    }
    
    
    
    
}
