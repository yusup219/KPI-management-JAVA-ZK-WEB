package controler;

import java.util.List;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import util.ConnectionUtil;

public class LupaPasswordController extends GenericForwardComposer {

    private Window winLupaPass;
    private Textbox tbUsername, tbPassLama, tbPassBaru;
    private Button btnCekUser, btnSimpan;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
    }

    // 1. FUNGSI TOMBOL CEK USERNAME
    public void onClick$btnCekUser() {
        String usernameInput = tbUsername.getValue().trim();
        if (usernameInput.isEmpty()) {
            Messagebox.show("Masukan Username Anda terlebih dahulu!");
            return;
        }
        
        List list = ConnectionUtil.getInstance().testConnection123();
        boolean userKetemu = false;

        for (Object obj : list) {
            Object[] objArri = (Object[]) obj;
            String dbUser = objArri[0] != null ? objArri[0].toString().trim() : "";
            String dbPass = objArri[1] != null ? objArri[1].toString().trim() : "";

            if (usernameInput.equals(dbUser)) {
                tbPassLama.setValue(dbPass);
                userKetemu = true;
                Messagebox.show("User ditemukan! Silakan masukkan password baru.");
                break;
            }
        }

        if (!userKetemu) {
            tbPassLama.setValue("");
            Messagebox.show("Username tidak terdaftar di sistem!");
        }
    }

    public void onClick$btnSimpan() {
        String username = tbUsername.getValue().trim();
        String passLama = tbPassLama.getValue().trim();
        String passBaru = tbPassBaru.getValue().trim();

        if (username.isEmpty() || passLama.isEmpty()) {
            Messagebox.show("Masukan Username dan lalukan proses pencarian terlebih dahulu!");
            return;
        }
        if (passBaru.isEmpty()) {
            Messagebox.show("Password baru tidak boleh kosong!");
            return;
        }
        
        // PROTEKSI: JIKA PASSWORD BARU SAMA DENGAN PASSWORD LAMA
        if (passBaru.equals(passLama)) {
            Messagebox.show("Gagal! Password baru tidak boleh sama dengan password lama Anda!", 
                    "Peringatan!!!", Messagebox.OK, Messagebox.ERROR);
            return;
        }

        try {
            ConnectionUtil.getInstance().updatePassword(username, passBaru); 
            
            Messagebox.show("Berhasil! Password telah diperbarui. Silakan login kembali.", 
                    "Sukses", Messagebox.OK, Messagebox.INFORMATION);
            
            winLupaPass.detach(); 
            
        } catch (Exception e) {
            e.printStackTrace();
            Messagebox.show("Terjadi kesalahan database: " + e.getMessage());
        }
    }
}