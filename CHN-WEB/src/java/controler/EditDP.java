/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import util.ConnectionUtil;


/**
 *
 * @author yusup
 */
public class EditDP extends GenericForwardComposer {

    Window WindowEdit;
    Textbox tbnama;
    Intbox tbumur, tbID;
    Checkbox checkActive;
    Radiogroup jenkel,Admin;
        Datebox dbStart;

   
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        Map hashMap = Executions.getCurrent().getArg();
        String id = (String) hashMap.get("id");
        String nama = (String) hashMap.get("nama");
        String umur = (String) hashMap.get("umur");
        String jenis_kelamin = (String) hashMap.get("jenis_kelamin");
        String administrasi = (String) hashMap.get("administrasi");
        Boolean status_pembayaran = (Boolean) hashMap.get("status_pembayaran");
        Date tanggal_lahir = (Date) hashMap.get("tanggallahir");

        tbID.setValue(Integer.valueOf(id));
        tbnama.setValue(nama);
        tbumur.setValue(Integer.valueOf(umur));
        List <Radio> listRadio = jenkel.getItems();
        int i = 0;
        for (Radio radio : listRadio) {
            if (jenis_kelamin != null && radio.getValue().equalsIgnoreCase(jenis_kelamin)) {

                jenkel.setSelectedIndex(i);

                break;
            }
            i++;
        } 
         List <Radio> listRadios = Admin.getItems();
        int j = 0;
        for (Radio radiou : listRadios) {
            if (jenis_kelamin != null && radiou.getValue().equalsIgnoreCase(administrasi)) {

                Admin.setSelectedIndex(j);

                break;
            }
            j++;
        } 
       checkActive.setChecked(status_pembayaran != null && status_pembayaran.equals(true) ? true : false);
       dbStart.setValue(tanggal_lahir);
       
         
        WindowEdit.doModal();
    }
    public void onClick$ButtonSave() {
        String nama = tbnama.getValue();
        int umur = tbumur.getValue();
        int id = tbID.getValue();
        String jenis_kelamin = jenkel.getSelectedItem().getValue();
        boolean status_pembayaran= checkActive.isChecked();
        String administrasi = Admin.getSelectedItem().getValue();
        Date tanggal_lahir = dbStart.getValue();

        ConnectionUtil.getInstance().UpdateDP(nama, umur, jenis_kelamin, administrasi, status_pembayaran, tanggal_lahir, id);

        WindowEdit.onClose();
    }

    public void onClick$ButtonClose() {
        WindowEdit.onClose();

    }
}
