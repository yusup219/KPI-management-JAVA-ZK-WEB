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

import java.util.Map;
import org.zkoss.zk.ui.Executions;
import java.math.BigDecimal;

/**
 *
 * @author yusup
 */
public class EditKendaraanController extends GenericForwardComposer {

    Textbox tbnomorpolisi, tbtipe, tbmerk;
    Window windowEditKendaraan;
    Intbox tbid, tbtahunp;
    Checkbox cebstatus;
    Button buttonSave, buttonClose;
    Decimalbox dbharga;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        Map hashMap = Executions.getCurrent().getArg();
        String id = (String) hashMap.get("id");
        String nomor_polisi = (String) hashMap.get("nomor_polisi");
        String tipe = (String) hashMap.get("tipe");
        String merk = (String) hashMap.get("merk");
        String tahun_pembuatan = (String) hashMap.get("tahun_pembuatan");
        Boolean status_tersedia = (Boolean) hashMap.get("status_tersedia");
        BigDecimal harga_per_hari = (BigDecimal) hashMap.get("harga_per_hari");
        tbid.setValue(Integer.valueOf(id));
        tbnomorpolisi.setValue(nomor_polisi);
        tbtipe.setValue(tipe);
        tbmerk.setValue(merk);
        tbtahunp.setValue(Integer.valueOf(tahun_pembuatan));
        cebstatus.setChecked(status_tersedia  != null && status_tersedia ? true : false);
        dbharga.setValue(harga_per_hari);

        windowEditKendaraan.doModal();
    }

    public void onClick$buttonEdit() {
        int id = tbid.getValue();
        String nomor_polisi = tbnomorpolisi.getValue();
        String tipe = tbtipe.getValue();
        String merk = tbmerk.getValue();
        int tahun_pembuatan = tbtahunp.getValue();
//        Boolean status_tersedia = cebstatus.isChecked();
         boolean ab = cebstatus.isChecked();
        String status_tersedia = ab ? "true" : "false"; 
        BigDecimal harga_per_hari = dbharga.getValue();

        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/sekolah";
            String username = "postgres";
            String password = "postgres";
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE kendaraan SET nomor_polisi=?, merk=?, tipe=?, tahun_pembuatan=?, status_tersedia=?, harga_per_hari=?  WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, nomor_polisi);
            statement.setString(2, merk);
            statement.setString(3, tipe);
            statement.setInt(4, tahun_pembuatan);
            statement.setString(5, status_tersedia);
            statement.setBigDecimal(6, harga_per_hari);
            statement.setInt(7, id);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data berhasil ditambahkan!");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Terjadi error: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
        windowEditKendaraan.onClose();
    }

    public void onClick$buttonClose() {
        windowEditKendaraan.onClose();

    }

}
