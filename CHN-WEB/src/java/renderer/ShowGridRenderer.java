/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package renderer;

import java.util.HashMap;
import java.util.Map;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import vo.SiswaVo;

/**
 *
 * @author yusup
 */
public class ShowGridRenderer implements RowRenderer{

    @Override
    public void render(Row row, Object t, int i) throws Exception {
        final SiswaVo vo = (SiswaVo) t;
        
        Label lbl = new Label(String.valueOf(vo.getId()));
        lbl.setParent(row);

        lbl = new Label(vo.getNama());
        lbl.setParent(row);

        lbl = new Label(vo.getAlamat());
        lbl.setParent(row);

        lbl = new Label(String.valueOf(vo.getUmur()));
        lbl.setParent(row);
        
        lbl = new Label(String.valueOf(vo.getId_sekolah()));
        lbl.setParent(row);
        
        
        
      

        Button buttonEdit = new Button("Edit");
        buttonEdit.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
            @Override
            public void onEvent(Event t) throws Exception {
                Map<String, Object> hashMap = new HashMap<String, Object>();
                hashMap.put("vo", vo);
                
        hashMap.put("id", String.valueOf(vo.getId()));
        hashMap.put("nama", vo.getNama());
        hashMap.put("umur", String.valueOf(vo.getUmur()));
        hashMap.put("alamat", vo.getAlamat());
        hashMap.put("id_sekolah", String.valueOf(vo.getId_sekolah()));
                Executions.createComponents("edit_grid.zul", null, hashMap);
            }
        });
        buttonEdit.setParent(row);
        
        
        
        Button buttonDelete = new Button("Delete");
        buttonDelete.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
            @Override
            public void onEvent(Event t) throws Exception {
                Map<String, Object> hashMap = new HashMap<String, Object>();
                hashMap.put("vo", vo);
                hashMap.put("id", String.valueOf(vo.getId()));
        hashMap.put("nama", vo.getNama());
        hashMap.put("alamat", vo.getAlamat());
        hashMap.put("umur", String.valueOf(vo.getUmur()));
                Executions.createComponents("delete_grid.zul", null, hashMap);
            }
        });
        buttonDelete.setParent(row);

        row.setAttribute("data", vo);
                

        
        

    }

    
    
    
    
    
    
}
