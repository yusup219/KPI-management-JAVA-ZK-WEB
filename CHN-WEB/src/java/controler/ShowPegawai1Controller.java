/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import java.util.HashMap;
import java.util.Map;
import org.zkoss.zul.Button;
import java.util.List;
import java.util.ArrayList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zk.ui.Executions;
import renderer.ShowPegawai1Renderer;
import vo.Pegawai1VO;
import java.util.Date;
import util.ConnectionUtil;
import javax.servlet.http.HttpSession;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

/**
 *
 * @author yusup
 */
public class ShowPegawai1Controller extends GenericForwardComposer {

    Listbox listboxShowData;
    Button buttonAdd, buttonEdit, buttonDelete, btnCari, btnRefresh;
    Textbox txtCari;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        HttpSession session = (HttpSession) Executions.getCurrent().getSession().getNativeSession();
        String role = (String) session.getAttribute("userRole");

        if (!"admin".equals(role)) {
            if (buttonAdd != null) {
                buttonAdd.setVisible(false);
            }
            if (buttonEdit != null) {
                buttonEdit.setVisible(false);
            }
            if (buttonDelete != null) {
                buttonDelete.setVisible(false);
            }
        }
        prepareList();
    }
    
    private boolean isAdmin() {
        HttpSession session = (HttpSession) Executions.getCurrent().getSession().getNativeSession();
        String role = (String) session.getAttribute("userRole");
        if (!"admin".equals(role)) {
            Messagebox.show("Maaf, Anda tidak memiliki akses untuk aksi ini!");
            return false;
        }
        return true;
    }

    public void onClick$buttonAdd() throws ClassNotFoundException {
        if (!isAdmin()) return;
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("id", "123");
        Executions.createComponents("add_pegawai1.zul", null, hashMap);
        prepareList();
    }

    public void onClick$buttonDelete() throws ClassNotFoundException {
        if (!isAdmin()) return;
        if (listboxShowData.getSelectedItem() == null) {
            return;
        }
        Pegawai1VO vo = (Pegawai1VO) listboxShowData.getSelectedItem().getAttribute("data");
        if (vo != null) {
            ConnectionUtil.getInstance().testDeletePegawai1(vo.getId());
            prepareList();
        }
    }

    public void onClick$buttonEdit() throws ClassNotFoundException {
        if (!isAdmin()) return;
        if (listboxShowData.getSelectedItem() == null) {
            return;
        }
        Pegawai1VO vo = (Pegawai1VO) listboxShowData.getSelectedItem().getAttribute("data");
        if (vo != null) {
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("id", vo.getId());
            hashMap.put("nama", vo.getNama());
            hashMap.put("umur", String.valueOf(vo.getUmur()));
            hashMap.put("alamat", vo.getAlamat());
            hashMap.put("id_departemen", String.valueOf(vo.getId_departemen()));
            hashMap.put("tanggallahir", vo.getTanggal_lahir());
            hashMap.put("gender", vo.getGender());

            Executions.createComponents("edit_pegawai1.zul", null, hashMap);
            prepareList();
        }
    }

    // Method default tanpa parameter (untuk load awal)
    public void prepareList() throws ClassNotFoundException {
        prepareList("");
    }

    // Overload method dengan parameter untuk menyaring data (pencarian)
    public void prepareList(String keyword) throws ClassNotFoundException {
        List list = ConnectionUtil.getInstance().testConnectionPegawai1();
        List<Pegawai1VO> listData = new ArrayList<Pegawai1VO>();
        
        for (Object obj : list) {
            Object[] objArr = (Object[]) obj;
            Pegawai1VO vo = new Pegawai1VO();
            vo.setId(objArr[0] != null ? objArr[0].toString() : "");
            vo.setNama(objArr[1] != null ? objArr[1].toString() : "");
            vo.setAlamat(objArr[2] != null ? objArr[2].toString() : "");
            vo.setUmur(objArr[3] != null ? (Integer) objArr[3] : 0);
            vo.setNama_departemen(objArr[4] != null ? objArr[4].toString() : "");
            vo.setTanggal_lahir(objArr[5] != null ? (Date) objArr[5] : null);
            vo.setGender(objArr[6] != null ? objArr[6].toString() : "");

            // Logika Filter: Menyaring berdasarkan Nama, Alamat, atau Departemen
            if (keyword.isEmpty() || 
                vo.getNama().toLowerCase().contains(keyword.toLowerCase()) || 
                vo.getAlamat().toLowerCase().contains(keyword.toLowerCase()) || 
                vo.getNama_departemen().toLowerCase().contains(keyword.toLowerCase())) {
                
                listData.add(vo);
            }
        }
        
        listboxShowData.setModel(new ListModelList<Object>(listData));
        listboxShowData.setItemRenderer(new ShowPegawai1Renderer());
    }
     public void onClick$btnCari() {
        String keyword = txtCari.getValue().trim();
        try {
            prepareList(keyword); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Messagebox.show("Gagal memuat pencarian data: " + e.getMessage());
        }
    }
    public void onClick$btnRefresh() {
        if (txtCari != null) {
            txtCari.setValue(""); // 1. Clear semua input teks di kolom pencarian
        }
        try {
            prepareList("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Messagebox.show("Gagal refresh data: " + e.getMessage());
        }
    }
}