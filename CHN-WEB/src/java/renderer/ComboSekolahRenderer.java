/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package renderer;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import vo.SekolahVO;

/**
 *
 * @author yusup
 */
public class ComboSekolahRenderer implements ComboitemRenderer {    
// 
    @Override
    public void render(Comboitem cmbtm, Object t, int i) throws Exception {
        SekolahVO vo = (SekolahVO) t;
        cmbtm.setValue(vo.getId_sekolah());
        cmbtm.setLabel(vo.getNamasekolah() + " di " + vo.getalamatSekolah());
        
        cmbtm.setAttribute("data", vo);
        
       
    }
    
}
// combo untuk merender combo unruk reder spesifik komponen 
