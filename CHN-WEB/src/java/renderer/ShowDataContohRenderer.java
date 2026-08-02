///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package renderer;
//
//import org.zkoss.zul.Listcell;
//import org.zkoss.zul.Listitem;
//import org.zkoss.zul.ListitemRenderer;
//import vo.DataContohVO;
//
///**
// *
// * @author yusup
// */
//public class ShowDataContohRenderer implements ListitemRenderer{
//
//    @Override
//    public void render(Listitem item, Object data, int index) throws Exception {
//        DataContohVO vo = (DataContohVO) data;
//        Listcell cell = new Listcell(String.valueOf(vo.getNama()));
//        cell.setParent(item);
//        
//        cell = new Listcell(String.valueOf(vo.getUmur()));
//        cell.setParent(item);
//        
//        cell = new Listcell(String.valueOf(vo.getAlamat()));
//        cell.setParent(item);
//        
//        cell = new Listcell(String.valueOf(vo.getIdSekolah()));
//        cell.setParent(item);
//        
//        cell = new Listcell(String.valueOf(vo.getStatusLulus()));
//        cell.setParent(item);
//        
//        cell = new Listcell(String.valueOf(vo.getJenisKelamin()));
//        cell.setParent(item);
//        
//        cell = new Listcell(String.valueOf(vo.getTanggalLahir()));
//        cell.setParent(item);
//        
//        item.setAttribute("data", vo);
//        
//    }
//    
//    
//}
