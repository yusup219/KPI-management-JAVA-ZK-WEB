/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package renderer;



import org.zkoss.zul.Listitem;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.ListitemRenderer;
import vo.SiswaVo;


/**
 *
 * @author yusup
 */
public class ShowDataRenderer implements ListitemRenderer{

    @Override
    public void render(Listitem lstm, Object t, int i) throws Exception {
        SiswaVo vo = (SiswaVo) t;
        Listcell cell = new Listcell(String.valueOf(vo.getId()));
        cell.setParent(lstm);
        
        cell = new Listcell(String.valueOf(vo.getNama()));
        cell.setParent(lstm);
        
        cell = new Listcell(String.valueOf(vo.getAlamat()));
        cell.setParent(lstm);
        
        cell = new Listcell(String.valueOf(vo.getUmur()));
        cell.setParent(lstm);
        
        cell = new Listcell(String.valueOf(vo.getId_sekolah()));
        cell.setParent(lstm);
        
        cell = new Listcell(String.valueOf(vo.getTanggallahir()));
        cell.setParent(lstm);
        
        cell = new Listcell(String.valueOf(vo.getJeniskelamin()));
        cell.setParent(lstm);
        

        lstm.setAttribute("data", vo);
    }
   
    
    
}
