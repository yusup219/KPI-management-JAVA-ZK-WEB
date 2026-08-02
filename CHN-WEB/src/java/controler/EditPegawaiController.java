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
import vo.PegawaiVO;
import renderer.ShowPegawaiRenderer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Datebox;
import java.util.Date;


/**
 *
 * @author yusup
 */
public class EditPegawaiController extends GenericForwardComposer {
     Window windowEditPegawai;
    Textbox tbNama;
    Intbox tbid, tbUmur;
    Checkbox checkActive;
    Datebox dbStart;
    Radiogroup gender, jabatanS;
    String url = "jdbc:postgresql://localhost:5432/sekolah";
    String username = "postgres";
    String password = "postgres";

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                Map hashMap = Executions.getCurrent().getArg();
        String id = (String) hashMap.get("id");
        String nama = (String) hashMap.get("nama");
        String umur = (String) hashMap.get("umur");
        String jenis_kelamin = (String) hashMap.get("jenis_kelamin");
        String jabatan = (String) hashMap.get("jabatan");
        Boolean status_aktif = (Boolean) hashMap.get("status_aktif");
        Date tanggal_masuk = (Date) hashMap.get("tanggallahir");
        tbid.setValue(Integer.valueOf(id));
        tbNama.setValue(nama);
        tbUmur.setValue(Integer.valueOf(umur));
        dbStart.setValue(tanggal_masuk);
        
//        gender.setseValue(String.valueOf(jeniskelamin));
        List <Radio> listRadio = gender.getItems();
        int i = 0;
        for (Radio radio : listRadio) {
            if (jenis_kelamin != null && radio.getValue().equalsIgnoreCase(jenis_kelamin)) {
//                jika variabel tidaksamadengan null dan valueyang didapat dari radio sama dengan(mengabaikan case)variabel
                gender.setSelectedIndex(i);
//                maka akan memilih index i
                break;
            }
            i++;
        }
       
        
        checkActive.setChecked(status_aktif != null && status_aktif ? true : false);
         List <Radio> listRadios = jabatanS.getItems();
        int ia = 0;
        for (Radio radios : listRadios) {
            if (jabatan != null && radios.getValue().equalsIgnoreCase(jabatan)) {
//                jika variabel tidaksamadengan null dan valueyang didapat dari radio sama dengan(mengabaikan case)variabel
                jabatanS.setSelectedIndex(ia);
//                maka akan memilih index i
                break;
            }
            ia++;
        }
        windowEditPegawai.doModal();
    }
    public void onClick$buttonSave() {
        String nama = tbNama.getValue();
        int umur = tbUmur.getValue();
        int id = tbid.getValue();
        boolean status_aktif = checkActive.isChecked();
        String jabatan = jabatanS.getSelectedItem().getValue();
        String jenis_kelamin = gender.getSelectedItem().getValue();
        Date tanggal_masuk = dbStart.getValue();
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/sekolah";
            String username = "postgres";
            String password = "postgres";
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE pegawai SET nama=?, umur=?, jenis_kelamin=?,jabatan=?, status_aktif=?, tanggal_masuk=?  WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, nama);
            statement.setInt(2, umur);
            statement.setString(3, jenis_kelamin);
            statement.setString(4, jabatan);
            statement.setBoolean(5, status_aktif);
            statement.setDate(6, new java.sql.Date(tanggal_masuk.getTime()));
            statement.setInt(7, id);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data siswa berhasil ditambahkan!");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Terjadi error: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
        windowEditPegawai.onClose();
    }

    public void onClick$buttonClose() {
        windowEditPegawai.onClose();

    }
}
