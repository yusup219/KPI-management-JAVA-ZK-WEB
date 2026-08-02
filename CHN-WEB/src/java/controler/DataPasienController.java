/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import renderer.PasienRenderer;
import util.ConnectionUtil;
import vo.PasienVO;

/**
 *
 * @author yusup
 */
public class DataPasienController extends GenericForwardComposer {

    Listbox ListboxDP;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        Preparelist();
    }

    public void onClick$BtAdd() throws ClassNotFoundException {
         Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", "123");

        Executions.createComponents("add_pasien.zul", null, hashMap);

        Preparelist();

    }
    public void onClick$BtEdit() throws ClassNotFoundException {
        PasienVO vo = (PasienVO) ListboxDP.getSelectedItem().getAttribute("data");
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", String.valueOf(vo.getId()));
        hashMap.put("nama", vo.getNama());
        hashMap.put("umur", String.valueOf(vo.getUmur()));
        hashMap.put("jenis_kelamin",vo.getJenis_kelamin());
        hashMap.put("administrasi", vo.getAdministrasi());
        hashMap.put("status_pembayaran", vo.isStatus_pembayaran());
        hashMap.put("status_pembayaran", vo.getTanggal_lahir());
        
        Executions.createComponents("edit_DP.zul", null, hashMap);

        Preparelist();
    }
      public void onClick$BtDelete() throws ClassNotFoundException {
        PasienVO vo = (PasienVO) ListboxDP.getSelectedItem().getAttribute("data");
                      ConnectionUtil.getInstance().testDeleteDP(vo.getId());


        Preparelist();
    }


    public void Preparelist() throws ClassNotFoundException {
         List list = ConnectionUtil.getInstance().testConnectionh();
        System.out.println("List" + list.size());
        List<PasienVO> listData = new ArrayList<PasienVO>();
        for (Object obj : list) {
            Object[] objArr = (Object[]) obj;
            System.out.println("bojArr =" + objArr[0]);
            System.out.println("bojArr =" + objArr[1]);
            System.out.println("bojArr =" + objArr[2]);
            System.out.println("bojArr =" + objArr[3]);
            System.out.println("bojArr =" + objArr[4]);
            System.out.println("bojArr =" + objArr[5]);
            System.out.println("bojArr =" + objArr[6]);

            PasienVO vo = new PasienVO();
            int id = (Integer) objArr[0];
            String nama = (String) objArr[1];
            int umur = (Integer) objArr[2];
            String jeniskelamin = (String) objArr[3];
            String administrasi = (String) objArr[4];
            boolean status_pembayaran = (boolean) objArr[5];
            Date tanggal_lahir = (Date) objArr[6];

            vo.setId(id);
            vo.setNama(nama);
            vo.setUmur(umur);
            vo.setJenis_kelamin(jeniskelamin);
            vo.setAdministrasi(administrasi);
            vo.setStatus_pembayaran(status_pembayaran);
            vo.setTanggal_lahir(tanggal_lahir);
            listData.add(vo);
            
            
            ListboxDP.setModel(new ListModelList<Object>(listData));
            ListboxDP.setItemRenderer(new PasienRenderer());
        }
    }
}

