/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import java.util.HashMap;
import java.util.Map;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Button;
import java.util.List;
import java.util.ArrayList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zk.ui.Executions;
import renderer.DepartemenDataRenderer;
import vo.DepartemenVO;
import util.ConnectionUtil;
import javax.servlet.http.HttpSession;
import org.zkoss.zul.Textbox;

/**
 *
 * @author yusup
 */
public class ShowDepartemenController extends GenericForwardComposer {

    Listbox listboxSekolahShowData;
    Button buttonAdds, buttonEdits, buttonDeletes, btnRefresh, btnCari;
    Textbox txtCari;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        HttpSession session = (HttpSession) Executions.getCurrent().getSession().getNativeSession();
        String role = (String) session.getAttribute("userRole");

        // Sembunyikan jika bukan admin
        if (!"admin".equals(role)) {
            if (buttonAdds != null) {
                buttonAdds.setVisible(false);
            }
            if (buttonEdits != null) {
                buttonEdits.setVisible(false);
            }
            if (buttonDeletes != null) {
                buttonDeletes.setVisible(false);
            }
        }
        prepareListSekolah();
    }

    private boolean isAdmin() {
        HttpSession session = (HttpSession) Executions.getCurrent().getSession().getNativeSession();
        return "admin".equals(session.getAttribute("userRole"));
    }

    public void onClick$buttonAdds() throws ClassNotFoundException {
        if (!isAdmin()) {
            return;
        }
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("id", "123");
        Executions.createComponents("add_departemen.zul", null, hashMap);
        prepareListSekolah();
    }

    public void onClick$buttonDeletes() throws ClassNotFoundException {
        if (!isAdmin()) {
            return;
        }
        if (listboxSekolahShowData.getSelectedItem() == null) {
            return;
        }
        DepartemenVO vo = (DepartemenVO) listboxSekolahShowData.getSelectedItem().getAttribute("data");
        if (vo != null) {
            ConnectionUtil.getInstance().testDeleteDepartemen(vo.getId_departemen());
            prepareListSekolah();
        }
    }

    public void onClick$buttonEdits() throws ClassNotFoundException {
        if (!isAdmin()) {
            return;
        }
        if (listboxSekolahShowData.getSelectedItem() == null) {
            return;
        }
        DepartemenVO vo = (DepartemenVO) listboxSekolahShowData.getSelectedItem().getAttribute("data");
        if (vo != null) {
            Map<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("id_departemen", String.valueOf(vo.getId_departemen()));
            hashMap.put("departemen", vo.getDepartemen());
            hashMap.put("Kepala_departemen", vo.getKepala_departemen());

            Executions.createComponents("edit_departemen.zul", null, hashMap);
            prepareListSekolah();
        }
    }

    // Method default tanpa parameter (untuk load awal dan refresh setelah CRUD)
    public void prepareListSekolah() throws ClassNotFoundException {
        prepareListSekolah("");
    }

    // Overload method dengan parameter untuk menyaring data berdasarkan keyword
    public void prepareListSekolah(String keyword) throws ClassNotFoundException {
        try {
            List list = ConnectionUtil.getInstance().testDepartemen();
            List<DepartemenVO> listDataDepartemen = new ArrayList<DepartemenVO>();

            for (Object obj : list) {
                Object[] objArr = (Object[]) obj;
                DepartemenVO vo = new DepartemenVO();
                vo.setId_departemen((String) objArr[0]);
                vo.setDepartemen((String) objArr[1]);
                vo.setKepala_departemen((String) objArr[2]);

                // Logika Filter (Berdasarkan nama departemen atau nama kepala departemen)
                if (keyword.isEmpty() || 
                    vo.getDepartemen().toLowerCase().contains(keyword.toLowerCase()) || 
                    vo.getKepala_departemen().toLowerCase().contains(keyword.toLowerCase())) {
                    
                    listDataDepartemen.add(vo);
                }
            }

            listboxSekolahShowData.setModel(new ListModelList<Object>(listDataDepartemen));
            listboxSekolahShowData.setItemRenderer(new DepartemenDataRenderer());
        } catch (Exception e) {
            System.out.println("Terjadi error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Fungsi aksi tombol cari
    public void onClick$btnCari() {
        String keyword = txtCari.getValue().trim();
        try {
            prepareListSekolah(keyword); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void onClick$btnRefresh() {
        if (txtCari != null) {
            txtCari.setValue(""); // 1. Kosongkan teks di kolom pencarian
        }
        try {
            prepareListSekolah(""); // 2. Kembalikan ke list awal tanpa filter data
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}