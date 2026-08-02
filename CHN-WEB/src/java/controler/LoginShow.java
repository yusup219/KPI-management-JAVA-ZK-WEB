package controler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Button;
import org.zkoss.zul.A;
import util.ConnectionUtil;

public class LoginShow extends GenericForwardComposer {

    Textbox tbuser, tbpass;
    Button buttonLogin, buttonClose;
    A linkDaftar, linkLupaPass;

    public void onClick$buttonLogin() throws ClassNotFoundException {
        String user = tbuser.getValue().trim();
        String pass = tbpass.getValue().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            Messagebox.show("Nama atau password belum terisi");
            return;
        }
        
        List list = ConnectionUtil.getInstance().loginCheckSecure(user, pass);

        if (list != null && !list.isEmpty()) {
            
            HttpSession session = (HttpSession) Executions.getCurrent().getSession().getNativeSession();

            String role = "admin".equalsIgnoreCase(user) ? "admin" : "user";
            session.setAttribute("userRole", role);
            
            org.zkoss.zk.ui.Sessions.getCurrent().setAttribute("nama", user); 
            org.zkoss.zk.ui.Sessions.getCurrent().setAttribute("userLogin", user);

            Executions.sendRedirect("index.zul?user=" + user);
            
        } else {
            Messagebox.show("Username atau Password salah!");
        }
    }

    public void onClick$linkDaftar() throws ClassNotFoundException {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("id", "123");
        Executions.createComponents("add_Pengguna.zul", null, hashMap);
    }
    
    public void onClick$linkLupaPass() {
        Executions.createComponents("lupa_password.zul", null, null);
    }
}