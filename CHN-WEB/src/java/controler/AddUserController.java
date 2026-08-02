package controler;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import util.ConnectionUtil;

public class AddUserController extends GenericForwardComposer {

    Window windowAddUser;
    Textbox tbNamaUser;
    Textbox tbFirstName;
    Textbox tbLastName;
    Textbox tbPassword;
    Textbox tbNoTlp;

    private String currentFirstName = "";

    private String currentLastName = "";

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        tbNamaUser.setReadonly(true);
        windowAddUser.doModal();
    }

    public void onChanging$tbFirstName(InputEvent event) {
        currentFirstName = event.getValue();
        FullName();
    }

    public void onChanging$tbLastName(InputEvent event) {
        currentLastName = event.getValue();
        FullName();
    }

    private void FullName() {
        String fullName = (currentFirstName.trim() + " " + currentLastName.trim()).trim();
        tbNamaUser.setValue(fullName);
    }

    public void onClick$buttonSave() {
        try {
            String idUser = "U" + System.currentTimeMillis();
            String namaUser = tbNamaUser.getValue().trim();
            String firstName = tbFirstName.getValue().trim();
            String lastName = tbLastName.getValue().trim();
            String password = tbPassword.getValue().trim();
            String noTlp = tbNoTlp.getValue().trim();

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
//           -------------------------------------------------
            ConnectionUtil.getInstance()
                    .insertUser(idUser, namaUser, firstName, lastName, password, noTlp);

            Messagebox.show("User berhasil ditambahkan");
            windowAddUser.detach();

        } catch (Exception e) {
            e.printStackTrace();
            Messagebox.show("Error: " + e.getMessage());
        }
    }

    public void onClick$buttonClose() {
        windowAddUser.detach();
    }
}
