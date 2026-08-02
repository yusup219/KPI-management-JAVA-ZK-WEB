package controler;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Window;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;
import renderer.ComboDepartemenRenderer;
import vo.DepartemenVO;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import java.util.Date;
import util.ConnectionUtil;
import vo.Pegawai1VO;
import renderer.ShowPegawai1Renderer;

/**
 *
 * @author yusup
 */
public class EditPegawai1Controller extends GenericForwardComposer {

    Window windowEditData;
    Textbox tbid, tbNama, tbAlamat;
    Intbox tbUmur;
    Combobox cbSekolahEdit;
    Datebox dbStart;
    Radiogroup gender;
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        Map hashMap = Executions.getCurrent().getArg();
        String id = (String) hashMap.get("id");
        String nama = (String) hashMap.get("nama");
        String alamat = (String) hashMap.get("alamat");
        String umur = (String) hashMap.get("umur");
        String idDepartemen = (String) hashMap.get("id_departemen");
        Date tanggal_lahir = (Date) hashMap.get("tanggal_lahir");
        String genderValue = (String) hashMap.get("gender");
        
        tbid.setValue(id);
        tbNama.setValue(nama);
        tbAlamat.setValue(alamat);
        if (umur != null) {
            tbUmur.setValue(Integer.valueOf(umur));
        }
        dbStart.setValue(tanggal_lahir);
        
        List<Radio> listRadio = gender.getItems();
        int i = 0;
        for (Radio radio : listRadio) {
            if (genderValue != null && radio.getValue().equalsIgnoreCase(genderValue)) {
                gender.setSelectedIndex(i);
                break;
            }
            i++;
        }

        cbSekolahEdit.addEventListener("onAfterRender", new EventListener() {
            @Override
            public void onEvent(Event t) throws Exception {
                setComboValue(idDepartemen, cbSekolahEdit);
            }
        });
        
        prepareListDepartemenCombobox();
        windowEditData.doModal();
    }

    protected void setComboValue(String val, Combobox combo) {
        List<Comboitem> listComboItem = combo.getItems();
        if (listComboItem != null && listComboItem.size() > 0) {
            for (int i = 0; i < listComboItem.size(); i++) {
                Comboitem comboitem = listComboItem.get(i);
                if (comboitem.getValue() != null && val != null && val.equalsIgnoreCase(comboitem.getValue().toString())) {
                    combo.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    public void onClick$buttonSave() {
        String id = tbid.getValue();
        String nama = tbNama.getValue();
        String alamat = tbAlamat.getValue();
        int umur = tbUmur.getValue();
        Date tanggal_lahir = dbStart.getValue();
        String genderValue = gender.getSelectedItem().getValue();

        String id_departemen = null;
        if (cbSekolahEdit.getSelectedItem() != null) {
            id_departemen = (String) cbSekolahEdit.getSelectedItem().getValue();
        }
        
        ConnectionUtil.getInstance().testUpdatePegawai1(
            nama, alamat, id, umur, id_departemen, tanggal_lahir, genderValue
        );
        
        windowEditData.onClose();
    }

    public void onClick$buttonClose() {
        windowEditData.onClose();
    }

    public void prepareListDepartemenCombobox() throws Exception {
        List list = ConnectionUtil.getInstance().testDepartemen();
        List<DepartemenVO> listDataDepartemen = new ArrayList<DepartemenVO>();
        for (Object obj : list) {
            Object[] objArr = (Object[]) obj; 
            DepartemenVO vo = new DepartemenVO();
            vo.setId_departemen((String) objArr[0]);
            vo.setDepartemen((String) objArr[1]);
            vo.setKepala_departemen((String) objArr[2]);
            listDataDepartemen.add(vo);
        }

        cbSekolahEdit.setModel(new ListModelList<DepartemenVO>(listDataDepartemen));
        cbSekolahEdit.setItemRenderer(new ComboDepartemenRenderer());
    }
}