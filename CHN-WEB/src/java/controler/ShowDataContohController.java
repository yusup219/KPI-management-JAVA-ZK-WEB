///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package controler;
//
//import bo.ContohBO;
////import com.google.protobuf.ListValue;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import org.zkoss.zk.ui.Component;
//import org.zkoss.zk.ui.util.GenericForwardComposer;
//import org.zkoss.zul.ListModelList;
//import org.zkoss.zul.Listbox;
//import renderer.ShowDataContohRenderer;
//import vo.DataContohVO;
//
///**
// *
// * @author yusup
// */
//public class ShowDataContohController extends GenericForwardComposer {
//
//    Listbox listBoxContoh;
//
//    @Override
//    public void doAfterCompose(Component comp) throws Exception {
//        super.doAfterCompose(comp);
//        preparelist();
//    }
//
//    public void preparelist() throws ClassNotFoundException {
//        try {
//            List list = ContohBO.getInstance().listDataContoh();
//            List<DataContohVO> listVO = new ArrayList<>();
//            System.out.println("list" + list);
//            if (list.size() > 0) {
//                for (Object obj : list) {
//                    Object[] anjay = (Object[]) obj;
//                    System.out.println("1 " + anjay[0]);
//                    System.out.println("2 " + anjay[1]);
//                    System.out.println("3 " + anjay[2]);
//                    System.out.println("4 " + anjay[3]);
//                    System.out.println("5 " + anjay[4]);
//                    System.out.println("6 " + anjay[5]);
//                    System.out.println("7 " + anjay[6]);
//                    System.out.println("8 " + anjay[7]);
////                    System.out.println("9 " + anjay[8]);
////                    System.out.println("1 " + anjay[0]);
//                    
//                    
//                    
//                    DataContohVO vo = new DataContohVO();
//                    String id = (String) anjay[0];
//                    String nama = (String) anjay[1];
//                    String alamat = (String) anjay[3];
//                    int umur = (Integer) anjay[2];
//                    int id_sekolah = (int) anjay[4];
//                    Date tanggallahir = (Date) anjay[7];
//                    String statuslulus = (String) anjay[5];
//                    String jeniskelamin = (String) anjay[6];
//                    
//                    vo.setIdSisWa(id);
//                    vo.setNama(nama);
//                    vo.setAlamat(alamat);
//                    vo.setUmur(umur);
//                    vo.setIdSekolah(id_sekolah);
//                    vo.setTanggalLahir(tanggallahir);
//                    vo.setJenisKelamin(jeniskelamin);
//                    vo.setStatusLulus(statuslulus);
////                    vo.setIdSisWa((String) anjay[0]);
////                    vo.setNama((String) anjay[1]);
////                    vo.setUmur((Integer) anjay[2]);
////                    vo.setAlamat((String) anjay[3]);
////                    vo.setIdSekolah((Integer) anjay[4]);
////                    vo.setStatusLulus((String) anjay[5]);
////                    vo.setJenisKelamin((String) anjay[6]);
////                    vo.setTanggalLahir((Date) anjay[7]);
//                    listVO.add(vo);
//                }
//                listBoxContoh.setModel(new ListModelList<Object>(listVO));
//                listBoxContoh.setItemRenderer(new ShowDataContohRenderer());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//}
