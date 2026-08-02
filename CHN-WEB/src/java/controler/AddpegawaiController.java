/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Window;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.zul.Datebox;

import vo.PegawaiVO;
import renderer.ShowPegawaiRenderer;
import java.util.List;
import java.util.ArrayList;
import org.zkoss.zul.ListModelList;
/**
 *
 * @author yusup
 */
public class AddpegawaiController extends GenericForwardComposer {
     Window windowAddPegawai;
    Textbox tbNama;
    Intbox tbid, tbUmur;
    Checkbox checkActive;
    Datebox dbStart;
    Radiogroup gender, jabatan;
    String url = "jdbc:postgresql://localhost:5432/sekolah";
    String username = "postgres";
    String password = "postgres";
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        
        windowAddPegawai.doModal();
    }
    
    public void onClick$buttonSave() {
        int id = tbid.getValue();
        String nama = tbNama.getValue();
        int umur = tbUmur.getValue();
        java.util.Date tanggal_masuk = dbStart.getValue();
        
//        dibuatstring
        boolean status_aktif = checkActive.isChecked();
        
        int cd = gender.getSelectedIndex();
        String jenis_kelamin = cd >0? "Laki-laki" : "Perempuan";
        
        int ef = jabatan.getSelectedIndex();
        String jabatana = (ef ==0)? "Manager" : (ef==1)?  "Staff" : "Admin";
        
       
        
        
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO pegawai (id, nama, umur, jenis_kelamin, jabatan, status_aktif, tanggal_masuk) VALUES (?, ?, ?, ?, ?,?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            statement.setString(2, nama);
            statement.setInt(3, umur);
            statement.setString(4, jenis_kelamin);
            statement.setString(5, jabatana);
            statement.setBoolean(6, status_aktif);
            statement.setDate(7, new java.sql.Date(tanggal_masuk.getTime()));

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
        windowAddPegawai.onClose();
    }

    public void onClick$buttonClose() {
        windowAddPegawai.onClose();
    }
}
