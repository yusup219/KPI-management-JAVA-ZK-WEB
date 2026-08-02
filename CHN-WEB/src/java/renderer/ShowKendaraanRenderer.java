/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package renderer;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.ListitemRenderer;
import vo.KendaraanVO;
/**
 *
 * @author yusup
 */
public class ShowKendaraanRenderer implements ListitemRenderer {

    @Override
    public void render(Listitem lstm, Object t, int i) throws Exception {
        KendaraanVO vo = (KendaraanVO) t;
        Listcell cell = new Listcell(String.valueOf(vo.getId()));
        cell.setParent(lstm);
        
        cell = new Listcell(String.valueOf(vo.getNomor_polisi()));
        cell.setParent(lstm);
               
        cell = new Listcell(String.valueOf(vo.getMerk()));
        cell.setParent(lstm);
        
        cell = new Listcell(String.valueOf(vo.getTipe()));
        cell.setParent(lstm);
        
        cell = new Listcell(String.valueOf(vo.getTahun_pembuatan()));
        cell.setParent(lstm);
        
        cell = new Listcell(String.valueOf(vo.isStatus_tersedia()));
        cell.setParent(lstm);
        
        cell = new Listcell(String.valueOf(vo.getHarga_per_hari()));
        cell.setParent(lstm);
 
        
        
        
        lstm.setAttribute("data", vo);
    }
    
    
}
