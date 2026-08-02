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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;
import renderer.ComboSekolahRenderer;
import vo.SekolahVO;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Comboitem;

/**
 *
 * @author yusup
 */
public class EditGridController extends GenericForwardComposer {
 


    Window windowEditData;
    Textbox tbNama, tbAlamat;
    Intbox tbUmur, tbid;
    Combobox cbSekolahEdit;
    String url = "jdbc:postgresql://localhost:5432/sekolah";
    String username = "postgres";
    String password = "postgres";

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
//        Map map = Executions.getCurrent().getArg();
//        System.out.println("map = " + map);
//        if (map.containsKey("id")) {
//            System.out.println("map.get(\"id\") = " + map.get("id"));
//            tbid.setValue((String) map.get("id"));
        Map hashMap = Executions.getCurrent().getArg();
        String id = (String) hashMap.get("id");
        String nama = (String) hashMap.get("nama");
        String alamat = (String) hashMap.get("alamat");
        String umur = (String) hashMap.get("umur");
        String idSekolah = (String) hashMap.get("id_sekolah");
        tbid.setValue(Integer.valueOf(id));
        tbNama.setValue(nama);
        tbAlamat.setValue(alamat);
        tbUmur.setValue(Integer.valueOf(umur));
        
        
        


        cbSekolahEdit.addEventListener("onAfterRender", new EventListener() {
            @Override
            public void onEvent(Event t) throws Exception {
                setComboValue(idSekolah, cbSekolahEdit);
            }
        });
        prepareListSekolahCombobox();
        windowEditData.doModal();
    }


    protected void setComboValue(String val, Combobox combo) {
        List<Comboitem> listComboItem = combo.getItems();
        if (listComboItem != null && listComboItem.size() > 0) {
            for (int i = 0; i < listComboItem.size(); i++) {
                Comboitem comboitem = listComboItem.get(i);
                if (comboitem.getValue() != null && val != null && val.equalsIgnoreCase(comboitem.getValue().toString())) {
                    combo.setSelectedIndex(i);
                    break;
                }
            }
        }

    }
    

    public void onClick$buttonSave() {
        String nama = tbNama.getValue();
        String alamat = tbAlamat.getValue();
        int umur = tbUmur.getValue();
        int id = tbid.getValue();

        Integer namaSekolah = null;
        if (cbSekolahEdit.getSelectedItem() != null) {
            namaSekolah = (Integer) cbSekolahEdit.getSelectedItem().getValue();
        }

        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/sekolah";
            String username = "postgres";
            String password = "postgres";
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE siswaa SET nama=?, alamat=?, umur=?, id_sekolah=?  WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, nama);
            statement.setString(2, alamat);
            statement.setInt(3, umur);
            statement.setInt(4, namaSekolah);
            statement.setInt(5, id);

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
        windowEditData.onClose();
    }

    public void onClick$buttonClose() {
        windowEditData.onClose();

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
            
cbSekolahEdit.setModel(new ListModelList<SekolahVO>(ListData));
        cbSekolahEdit.setItemRenderer(new ComboSekolahRenderer());
        } catch (SQLException e) {
            System.out.println("Terjadi error:" + e.getMessage());
        }
        
    }
}
