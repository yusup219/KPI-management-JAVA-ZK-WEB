package renderer;

import vo.UserVo;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public class ShowUserRenderer implements ListitemRenderer {

    @Override
    public void render(Listitem item, Object data, int index)
            throws Exception {
        UserVo vo = (UserVo) data;

        Listcell cell = new Listcell(
                String.valueOf(vo.getIdUser()));
        cell.setParent(item);

        cell = new Listcell(
                vo.getNamaUser() != null
                ? vo.getNamaUser() : "-");
        cell.setParent(item);

        cell = new Listcell(
                vo.getFirstName() != null
                ? vo.getFirstName() : "-");
        cell.setParent(item);

        cell = new Listcell(
                vo.getLastName() != null
                ? vo.getLastName() : "-");
        cell.setParent(item);

        cell = new Listcell(
                vo.getPassword() != null
                ? "••••••" : "-");
        cell.setParent(item);

        cell = new Listcell(
                vo.getNoTlp() != null
                ? vo.getNoTlp() : "-");
        cell.setParent(item);

        item.setAttribute("data", vo);
    }
}