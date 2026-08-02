/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package renderer;

import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import vo.PasienVO;

/**
 *
 * @author yusup
 */
public class PasienRenderer implements ListitemRenderer {

    @Override
    public void render(Listitem lstm, Object t, int i) throws Exception {
      PasienVO vo = (PasienVO) t;
      Listcell cell = new Listcell(String.valueOf(vo.getId()));
      cell.setParent(lstm);
      
      cell = new Listcell(String.valueOf(vo.getNama()));
      cell.setParent(lstm);
      
      cell = new Listcell(String.valueOf(vo.getUmur()));
      cell.setParent(lstm);
      
      cell = new Listcell(String.valueOf(vo.getJenis_kelamin()));
      cell.setParent(lstm);
      
      cell = new Listcell(String.valueOf(vo.getAdministrasi()));
      cell.setParent(lstm);
      
      cell = new Listcell(String.valueOf(vo.isStatus_pembayaran()));
      cell.setParent(lstm);
      cell = new Listcell(String.valueOf(vo.getTanggal_lahir()));
      cell.setParent(lstm);
      
      lstm.setAttribute("data", vo);
    }
    
}