/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.KendaraanVO;
import renderer.ShowKendaraanRenderer;
import java.util.List;
import java.util.ArrayList;
import org.zkoss.zul.ListModelList;
import java.math.BigDecimal;


/**
 *
 * @author yusup
 */
public class AddKendaraanController extends GenericForwardComposer {
    Textbox tbnomorpolisi, tbtipe, tbmerk;
    Window windowAddKendaraan;
    Intbox tbid, tbtahunp;
    Checkbox cebstatus;
    Button buttonSave, buttonClose;
    Decimalbox dbharga;
    String url = "jdbc:postgresql://localhost:5432/sekolah";
    String username = "postgres";
    String password = "postgres";
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        windowAddKendaraan.doModal();

    }
    
    public void onClick$buttonSave() {
        int id = tbid.getValue();
        String nomor_polisi = tbnomorpolisi.getValue();
        String merk = tbmerk.getValue();
        String tipe = tbtipe.getValue();
        int tahun_pembuatan = tbtahunp.getValue();
       
        
//        dibuatstring
//        boolean status_tersedia = cebstatus.isChecked();
        boolean ab = cebstatus.isChecked();
        String status_tersedia = ab ? "true" : "false"; 
        BigDecimal harga_per_hari = dbharga.getValue();
     
       
        
        
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO kendaraan (id, nomor_polisi, merk, tipe, tahun_pembuatan, status_tersedia, harga_per_hari) VALUES (?, ?, ?, ?, ?,?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            statement.setString(2, nomor_polisi);
            statement.setString(3, merk);
            statement.setString(4, tipe);
            statement.setInt(5, tahun_pembuatan);
            statement.setString(6, status_tersedia);
            statement.setBigDecimal(7,harga_per_hari);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data pegawai berhasil ditambahkan!");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Terjadi error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            Logger.getLogger(AddDataController.class.getName()).log(Level.SEVERE, null, e);
        }
        windowAddKendaraan.onClose();
    }

    public void onClick$buttonClose() {
        windowAddKendaraan.onClose();
    }
    
    
}
