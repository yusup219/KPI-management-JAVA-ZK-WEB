/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Window;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import java.sql.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Textbox;

/**
 *
 * @author yusup
 */
public class DeleteSekolahController extends GenericForwardComposer {
Window windowDeleteSekolah;

    Textbox tbid_sekolah;
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    Map map = Executions.getCurrent().getArg();
        System.out.println("map = " + map);
        if (map.containsKey("id_sekolah")) {
            System.out.println("map.get(\"id_sekolah\") = " + map.get("id_sekolah"));
            tbid_sekolah.setValue((String) map.get("id_sekolah"));
        }
        windowDeleteSekolah.doModal();

    }
    
    public void onClick$buttonDelete() {

        String id_sekolah = tbid_sekolah.getValue();

        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/sekolah";
            String username = "postgres";
            String password = "postgres";
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "DELETE FROM sekolah WHERE id_sekolah = '" + id_sekolah + "'";
            PreparedStatement statement = connection.prepareStatement(sql);

//            statement.setInt(1,id);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("hapus!");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Terjadi error: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DeleteDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
        windowDeleteSekolah.onClose();
    }

    public void onClick$buttonClose() {
        windowDeleteSekolah.onClose();

    }
    }
    
    

