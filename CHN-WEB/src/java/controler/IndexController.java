package controler;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Window;

public class IndexController extends GenericForwardComposer {

    private Include includeForm;
    private Window apaaja;
    private Tree menuTree;
    private Button buttonLogout;
    private Treeitem treeDataUser;
    private Label lblWelcome;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

        apaaja.setTitle("Perusahaan Teknologi Inovasi");

        String nama = (String) Sessions.getCurrent().getAttribute("nama");

        if (nama == null || nama.trim().isEmpty()) {
            nama = "User";
        }

        lblWelcome.setValue(nama);

        String userSession = (String) Sessions.getCurrent().getAttribute("userLogin");
        String urlUser = Executions.getCurrent().getParameter("user");

        if (userSession == null || urlUser == null || !urlUser.equals(userSession)) {

            Sessions.getCurrent().removeAttribute("userLogin");
            Executions.sendRedirect("username_login.zul");
            return;
        }
    }

    public void onSelect$menuTree() {

        Treeitem selectedItem = menuTree.getSelectedItem();

        if (selectedItem == null) {
            return;
        }

        String idMenu = selectedItem.getId();

        includeForm.invalidate();

        if ("treePegawai".equals(idMenu)) {
            includeForm.setSrc("show_pegawai1.zul");
        } else if ("treeDepartemen".equals(idMenu)) {
            includeForm.setSrc("show_departemen.zul");
        } else if ("treeKPI".equals(idMenu)) {
            includeForm.setSrc("show_kpi.zul");
        } else if ("treeDataUser".equals(idMenu)) {
            includeForm.setSrc("show_user.zul");
        }
    }

    public void onClick$buttonLogout() {

        Sessions.getCurrent().invalidate();

        Executions.sendRedirect("username_login.zul");
    }
}