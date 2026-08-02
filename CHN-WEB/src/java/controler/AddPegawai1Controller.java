package controler;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Window;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import org.zkoss.zul.ListModelList;
import vo.DepartemenVO; 
import renderer.ComboDepartemenRenderer; 
import util.ConnectionUtil;
import util.IdUtil;
/**
 *
 * @author yusup
 */
public class AddPegawai1Controller extends GenericForwardComposer {
    Window windowAddPegawai1;
    Textbox tbNama, tbAlamat, tbid;;
    Intbox tbUmur;
                
    Combobox cbSekolah; 
    Datebox dbStart;
    Radiogroup gender;
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        prepareListDepartemenCombobox();
        windowAddPegawai1.doModal();
    }
    public void onClick$buttonSave() {
        String id = "P" + System.currentTimeMillis();
        String nama = tbNama.getValue();
        String alamat = tbAlamat.getValue();
        int umur = tbUmur.getValue();
        Date tanggal_lahir = dbStart.getValue();
        
        int cd = gender.getSelectedIndex();
        String genderValue = cd > 0 ? "M" : "F";
        
        String id_departemen = null;
        if (cbSekolah.getSelectedItem() != null) {
            id_departemen = cbSekolah.getSelectedItem().getValue();
        }
        
        ConnectionUtil.getInstance().testInsertPegawai1(
            id, nama, alamat, umur, id_departemen, tanggal_lahir, genderValue
        );
        windowAddPegawai1.onClose();
    }
    public void onClick$buttonClose() {
        windowAddPegawai1.onClose();
    }
    public void prepareListDepartemenCombobox() throws Exception {
        List list = ConnectionUtil.getInstance().testDepartemen();
        List<DepartemenVO> listDataDepartemen = new ArrayList<>();
        for (Object obj : list) {
            Object[] objArr = (Object[]) obj; 
            DepartemenVO vo = new DepartemenVO();
            vo.setId_departemen((String) objArr[0]);
            vo.setDepartemen((String) objArr[1]);
            vo.setKepala_departemen((String) objArr[2]);
            listDataDepartemen.add(vo);
        }
        
        cbSekolah.setModel(new ListModelList<>(listDataDepartemen));
        cbSekolah.setItemRenderer(new ComboDepartemenRenderer());
    }
}