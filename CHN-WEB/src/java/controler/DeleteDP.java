/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import java.util.Map;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Window;
import org.zkoss.zul.Intbox;
import util.ConnectionUtil;

/**
 *
 * @author yusup
// */
//public class DeleteDP extends GenericForwardComposer{
//   Window WindowEdit;
//   Intbox tbid;
//    @Override
//    public void doAfterCompose(Component comp) throws Exception {
//        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
//    WindowEdit.doModal();Map hashMap = Executions.getCurrent().getArg();
//        String id = (String) hashMap.get("id");
//        tbid.setValue(Integer.valueOf(id));
//    }
//    public void onClick$BtDelete() throws ClassNotFoundException{
//        Integer id = tbid.getValue();
//                ConnectionUtil.getInstance().testDeleteDP(vo.getId);
//     WindowEdit.onClose();
//    }
//    
//    public void onClick$BtClose() {
//        WindowEdit.onClose();
//    }
//}
