/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;

import org.zkoss.zul.Intbox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import util.ConnectionUtil;

/**
 *
 * @author yusup
 */
public class AddPasien extends GenericForwardComposer {

    Window WindowAdd;
    Textbox tbnama;
    Intbox tbID, tbumur;
    Datebox dbStart;
   
    Checkbox checkActive;
    Radiogroup jenkel, Admin;
    //menghubungkan ke database
   

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
       
        
        
        
        WindowAdd.doModal();
    }

    public void onClick$ButtonSave() {
        
        
        int id = tbID.getValue();
        String nama = tbnama.getValue();
        
        int umur = tbumur.getValue();
       
        
        
        int cd = jenkel.getSelectedIndex();
        String jeniskelamin = cd >0? "Laki-laki" : "Perempuan";
        
        int ef = Admin.getSelectedIndex();
        String administrasi = ef >0? "BPJS" : ef < 1? "KIS" : "Reguler";
        
        
        boolean status_pembayaran = checkActive.isChecked();
        java.util.Date tanggal_lahir = dbStart.getValue();
        
      
            ConnectionUtil.getInstance().testTambahDP(id, nama, umur, jeniskelamin, administrasi, status_pembayaran, tanggal_lahir);

        WindowAdd.onClose();
    }

    public void onClick$ButtonClose() {
        WindowAdd.onClose();
    }

    
}
