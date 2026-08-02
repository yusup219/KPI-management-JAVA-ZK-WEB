package controler;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import util.ConnectionUtil;

/**
 *
 * @author yusup
 */
public class AddDepartemenController extends GenericForwardComposer {
    Window windowSekolahAddData;
    Textbox tbdepartemen, tbkepala_departemen;
    Textbox tbid;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        windowSekolahAddData.doModal();
    }

    public void onClick$buttonSave() {
        String id_departemen = "D" + System.currentTimeMillis();
        String departemen = tbdepartemen.getValue();
        String kepala_departemen = tbkepala_departemen.getValue();
        
        ConnectionUtil.getInstance().testInsertDepartemen(id_departemen, departemen, kepala_departemen);
        windowSekolahAddData.onClose();
    }
    
    public void onClick$buttonClose() {
        windowSekolahAddData.onClose();
    }
}