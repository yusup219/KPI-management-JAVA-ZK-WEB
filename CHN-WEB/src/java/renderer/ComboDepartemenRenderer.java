package renderer;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import vo.DepartemenVO; 
/**
 *
 * @author yusup
 */
public class ComboDepartemenRenderer implements ComboitemRenderer {    
    
    @Override
    public void render(Comboitem cmbtm, Object t, int i) throws Exception {
        DepartemenVO vo = (DepartemenVO) t;
        
        cmbtm.setValue(vo.getId_departemen());
        
        cmbtm.setLabel(vo.getDepartemen()+ " (" + vo.getKepala_departemen()+ ")");
        
        cmbtm.setAttribute("data", vo);
    }
}