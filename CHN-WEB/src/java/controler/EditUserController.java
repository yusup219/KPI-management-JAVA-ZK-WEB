package controler;

import java.util.Map;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import util.ConnectionUtil;

public class EditUserController extends GenericForwardComposer {

    Window windowEditUser;
    Textbox tbNamaUser;
    Textbox tbFirstName;
    Textbox tbLastName;
    Textbox tbPassword;
    Textbox tbNoTlp;

    private String idUser;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

        Map map = Executions.getCurrent().getArg();

        idUser        = (String) map.get("idUser");
        String namaUser  = (String)  map.get("namaUser");
        String firstName = (String)  map.get("firstName");
        String lastName  = (String)  map.get("lastName");
        String password  = (String)  map.get("password");
        String noTlp     = (String)  map.get("noTlp");

        tbNamaUser.setValue(namaUser  != null ? namaUser  : "");
        tbFirstName.setValue(firstName != null ? firstName : "");
        tbLastName.setValue(lastName  != null ? lastName  : "");
        tbPassword.setValue(password  != null ? password  : "");
        tbNoTlp.setValue(noTlp       != null ? noTlp     : "");

        windowEditUser.doModal();
    }

    public void onClick$buttonSave() {
        try {
            String namaUser  = tbNamaUser.getValue().trim();
            String firstName = tbFirstName.getValue().trim();
            String lastName  = tbLastName.getValue().trim();
            String password  = tbPassword.getValue().trim();
            String noTlp     = tbNoTlp.getValue().trim();

            if (namaUser.isEmpty()) {
                Messagebox.show("Nama User wajib diisi");
                return;
            }
            if (firstName.isEmpty()) {
                Messagebox.show("First Name wajib diisi");
                return;
            }
            if (password.isEmpty()) {
                Messagebox.show("Password wajib diisi");
                return;
            }

            ConnectionUtil.getInstance()
                    .updateUser(idUser, namaUser, firstName,
                            lastName, password, noTlp);
            Messagebox.show("Data user berhasil diupdate");
            windowEditUser.detach();

        } catch (Exception e) {
            e.printStackTrace();
            Messagebox.show("Error: " + e.getMessage());
        }
    }

    public void onClick$buttonClose() {
        windowEditUser.detach();
    }
}