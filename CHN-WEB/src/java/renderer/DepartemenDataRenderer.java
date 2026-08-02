package renderer;

import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import vo.DepartemenVO;

/**
 *
 * @author yusup
 */
public class DepartemenDataRenderer implements ListitemRenderer {

    @Override
    public void render(Listitem lstm, Object t, int i) throws Exception {
        DepartemenVO vo = (DepartemenVO) t;
        
        Listcell cell = new Listcell(String.valueOf(vo.getId_departemen()));
        cell.setParent(lstm);
        
        cell = new Listcell(vo.getDepartemen());
        cell.setParent(lstm);
        
        cell = new Listcell(vo.getKepala_departemen());
        cell.setParent(lstm);
        
        lstm.setAttribute("data", vo);
    }
}