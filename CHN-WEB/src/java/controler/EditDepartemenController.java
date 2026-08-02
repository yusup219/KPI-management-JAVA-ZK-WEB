package controler;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Window;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import java.util.Map;
import org.zkoss.zk.ui.Executions;
import util.ConnectionUtil;
import vo.DepartemenVO;
import renderer.ComboDepartemenRenderer;
import renderer.DepartemenDataRenderer;
/**
 *
 * @author yusup
 */
public class EditDepartemenController extends GenericForwardComposer {
    Window windowEditSekolahData;
    Textbox tbdepartemen, tbkepala_departemen;
    Textbox tbid_departemen;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        Map hashMap = Executions.getCurrent().getArg();
        String id_departemen = (String) hashMap.get("id_departemen");
        String departemen = (String) hashMap.get("departemen");
        String kepala_departemen = (String) hashMap.get("Kepala_departemen");
        
        tbid_departemen.setValue(String.valueOf(id_departemen));
        tbdepartemen.setValue(departemen);
        tbkepala_departemen.setValue(kepala_departemen);
        
        windowEditSekolahData.doModal();
    }

    public void onClick$buttonSave() {
        String id_departemen = tbid_departemen.getValue();
        String departemen = tbdepartemen.getValue();
        String kepala_departemen = tbkepala_departemen.getValue();
        
        ConnectionUtil.getInstance().testUpdateDepartemen(departemen, kepala_departemen, id_departemen);
        windowEditSekolahData.onClose();
    }
    
    public void onClick$buttonClose() {
        windowEditSekolahData.onClose();
    }
}
