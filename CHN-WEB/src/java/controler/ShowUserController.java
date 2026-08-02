package controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vo.UserVo;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import renderer.ShowUserRenderer;
import util.ConnectionUtil;
import javax.servlet.http.HttpSession;
import org.zkoss.zul.Textbox;

public class ShowUserController extends GenericForwardComposer {
    
    Textbox txtCari;
    Button buttonTambah;
    Button buttonEdit;
    Button buttonDelete;
    Button btnRefresh; 
    Button btnCari;
    Listbox listUser;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        HttpSession session = (HttpSession) Executions.getCurrent().getSession().getNativeSession();
        String role = (String) session.getAttribute("userRole");

        if (!"admin".equals(role)) {
            if (buttonTambah != null) buttonTambah.setVisible(false);
            if (buttonEdit != null) buttonEdit.setVisible(false);
            if (buttonDelete != null) buttonDelete.setVisible(false);
        }
        
        prepareList("");
    }

    public void onClick$buttonTambah() {
        if (!isAdmin()) return;
        Map hashMap = new HashMap();
        hashMap.put("id", "0");
        Executions.createComponents("/add_user.zul", null, hashMap);
        prepareList("");
    }

    public void onClick$buttonEdit() {
        if (!isAdmin()) return;
        if (listUser.getSelectedItem() == null) {
            Messagebox.show("Pilih data user terlebih dahulu");
            return;
        }
        UserVo vo = (UserVo) listUser.getSelectedItem().getAttribute("data");
        Map hashMap = new HashMap();
        hashMap.put("idUser", vo.getIdUser());
        hashMap.put("namaUser", vo.getNamaUser());
        hashMap.put("firstName", vo.getFirstName());
        hashMap.put("lastName", vo.getLastName());
        hashMap.put("password", vo.getPassword());
        hashMap.put("noTlp", vo.getNoTlp() != null ? vo.getNoTlp() : "");
        Executions.createComponents("/edit_user.zul", null, hashMap);
        prepareList(""); // Refresh list setelah edit data
    }

    public void onClick$buttonDelete() {
        if (!isAdmin()) return;
        if (listUser.getSelectedItem() == null) {
            Messagebox.show("Pilih data user terlebih dahulu");
            return;
        }
        UserVo vo = (UserVo) listUser.getSelectedItem().getAttribute("data");
        try {
            ConnectionUtil.getInstance().deleteUser(vo.getIdUser());
            Messagebox.show("Data user berhasil dihapus");
            prepareList(""); 
        } catch (Exception e) {
            e.printStackTrace();
            Messagebox.show("Error: " + e.getMessage());
        }
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

    public void prepareList(String keyword) {
        try {
            List rawList = ConnectionUtil.getInstance().getDataUser();
            List<UserVo> listData = new ArrayList<UserVo>();

            for (Object obj : rawList) {
                Object[] row = (Object[]) obj;
                UserVo vo = new UserVo();
                vo.setIdUser(row[0] != null ? row[0].toString() : ""); 
                vo.setNamaUser(row[1] != null ? row[1].toString() : "");
                vo.setFirstName(row[2] != null ? row[2].toString() : "");
                vo.setLastName(row[3] != null ? row[3].toString() : "");
                vo.setPassword(row[4] != null ? row[4].toString() : "");
                vo.setNoTlp(row[5] != null ? row[5].toString() : "");

                if (keyword.isEmpty() || 
                    vo.getNamaUser().toLowerCase().contains(keyword.toLowerCase()) || 
                    vo.getFirstName().toLowerCase().contains(keyword.toLowerCase()) ||
                    vo.getLastName().toLowerCase().contains(keyword.toLowerCase())) {
                    
                    listData.add(vo);
                }
            }

            listUser.setModel(new ListModelList<UserVo>(listData));
            listUser.setItemRenderer(new ShowUserRenderer());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void onClick$btnCari() {
        String keyword = txtCari.getValue().trim();
        prepareList(keyword); 
    }

    public void onClick$btnRefresh() {
        if (txtCari != null) {
            txtCari.setValue(""); 
        }
        prepareList(""); 
    }
}