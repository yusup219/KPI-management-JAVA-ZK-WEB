/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Window;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.SekolahVO;
import renderer.ComboSekolahRenderer;
import java.util.List;
import java.util.ArrayList;
import org.zkoss.zul.ListModelList;


/**
 *
 * @author yusup
 */
public class AddGridController extends GenericForwardComposer {
    Combobox cbSekolah;
    Textbox tbNama, tbAlamat;
    Intbox tbUmur, tbid;
    Window windowAddGrid;
    String url = "jdbc:postgresql://localhost:5432/sekolah";
    String username = "postgres";
    String password = "postgres";

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    windowAddGrid.doModal();
    prepareListSekolahCombobox();
    
    }
    
    public void onClick$buttonSave() {
        int id = tbid.getValue();
        String nama = tbNama.getValue();
        String alamat = tbAlamat.getValue();
        int umur = tbUmur.getValue();

        Integer namaSekolah = null;
        if (cbSekolah.getSelectedItem() != null) {
            namaSekolah = cbSekolah.getSelectedItem().getValue();
        }
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO siswaa (id, nama, alamat, umur, id_sekolah) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            statement.setString(2, nama);
            statement.setString(3, alamat);
            statement.setInt(4, umur);
            statement.setInt(5, namaSekolah);

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
        windowAddGrid.onClose();
    }

    public void onClick$buttonClose() {
        windowAddGrid.onClose();
    }

    public void prepareListSekolahCombobox() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);
            //QUERY UNTUK MEMBACA DATA SISWA
            String sql = "SELECT * FROM sekolah ORDER BY id_sekolah";
            Statement statementSelect = connection.createStatement();
            ResultSet resultSet = statementSelect.executeQuery(sql);
            List<SekolahVO> ListData = new ArrayList<SekolahVO>();

            //menampilkan data siswa
            while (resultSet.next()) {
                SekolahVO vo = new SekolahVO();
                vo.setId_sekolah(resultSet.getInt("id_sekolah"));
                vo.setNamasekolah(resultSet.getString("namasekolah"));
                vo.setalamatSekolah(resultSet.getString("alamatsekolah"));
                ListData.add(vo);
                connection.close();
            }
            //menutup koneksi
            
cbSekolah.setModel(new ListModelList<SekolahVO>(ListData));
        cbSekolah.setItemRenderer(new ComboSekolahRenderer());
        } catch (SQLException e) {
            System.out.println("Terjadi error:" + e.getMessage());
        }
        
    }
    
}
