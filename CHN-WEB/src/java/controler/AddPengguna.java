package controler;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import util.ConnectionUtil;
import java.util.List; 

/**
 *
 * @author yusup
 */
public class AddPengguna extends GenericForwardComposer {

    Window windowAddPengguna;
    Textbox tbnamaUser, tbfirstName, tblastName, tbpassword, tbnoTlp;

    private String currentfirstName = "";
    private String currentlastName = "";

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        tbnamaUser.setReadonly(true);
        windowAddPengguna.doModal();
    }

    public void onChanging$tbfirstName(InputEvent event) {
        currentfirstName = event.getValue();
        updateFullName();
    }

    public void onChanging$tblastName(InputEvent event) {
        currentlastName = event.getValue();
        updateFullName();
    }

    private void updateFullName() {
        String fullName = (currentfirstName.trim() + " " + currentlastName.trim()).trim();
        tbnamaUser.setValue(fullName);
    }

    public void onClick$buttonSimpanUser() {
        try {
            String idUser = "U" + System.currentTimeMillis();
            String namaUser = tbnamaUser.getValue().trim();
            String firstName = tbfirstName.getValue().trim();
            String lastName = tblastName.getValue().trim();
            String password = tbpassword.getValue().trim();
            String noTlp = tbnoTlp.getValue().trim();

            if (firstName.isEmpty()) {
                Messagebox.show("First Name wajib diisi");
                return;
            }
            if (lastName.isEmpty()) {
                Messagebox.show("Last Name wajib diisi");
                return;
            }
            if (password.isEmpty()) {
                Messagebox.show("Password wajib diisi");
                return;
            }
            if (noTlp.isEmpty()) {
                Messagebox.show("Nomor Telepon wajib diisi");
                return;
            }

            List rawList = ConnectionUtil.getInstance().getDataUser();
            boolean dataSudahAda = false;

            for (Object obj : rawList) {
                Object[] row = (Object[]) obj;
                String dbFirstName = row[2] != null ? row[2].toString().trim() : "";
                String dbLastName = row[3] != null ? row[3].toString().trim() : "";

                if (firstName.equalsIgnoreCase(dbFirstName) && lastName.equalsIgnoreCase(dbLastName)) {
                    dataSudahAda = true;
                    break;
                }
            }

            if (dataSudahAda) {
                Messagebox.show("Akun sudah terdaftar silakan Login", "Peringatan", Messagebox.OK, Messagebox.EXCLAMATION);
                return;
            }
//            ---------------------------------------------------------

            ConnectionUtil.getInstance()
                    .testInsertUser(idUser, namaUser, firstName, lastName, password, noTlp);

            Messagebox.show("Pengguna baru berhasil didaftarkan!", "Sukses", Messagebox.OK, Messagebox.INFORMATION);
            windowAddPengguna.detach();

        } catch (Exception e) {
            e.printStackTrace();
            Messagebox.show("Gagal mendaftarkan pengguna: " + e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
        }
    }

    public void onClick$buttonBatal() {
        windowAddPengguna.detach();
    }
}
