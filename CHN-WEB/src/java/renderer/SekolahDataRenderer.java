/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package renderer;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import vo.SekolahVO;


/**
 *
 * @author yusup
 */

public class SekolahDataRenderer implements ListitemRenderer {

    @Override
    public void render(Listitem lstm, Object t, int i) throws Exception {
        SekolahVO vo1 = (SekolahVO) t;
        Listcell cell = new Listcell(String.valueOf(vo1.getId_sekolah()));
        cell.setParent(lstm);
        cell = new Listcell(String.valueOf(vo1.getNamasekolah()));
        cell.setParent(lstm);
        cell = new Listcell(String.valueOf(vo1.getalamatSekolah()));
        cell.setParent(lstm);
        lstm.setAttribute("data", vo1);
         // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    }
    

            
          
 
    

   
    
 
    
    

