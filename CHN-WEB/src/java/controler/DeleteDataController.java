///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package controler;
//
//import org.zkoss.zk.ui.Component;
//import org.zkoss.zul.Window;
//import org.zkoss.zk.ui.util.GenericForwardComposer;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.zkoss.zk.ui.Executions;
//import org.zkoss.zul.Textbox;
//import util.ConnectionUtil;
//import vo.SiswaVo;
//
///**
// *
// * @author yusup
// */
//public class DeleteDataController extends GenericForwardComposer {
//
//    Window windowDeleteData;
//
//    Textbox tbid;
//
//    @Override
//    public void doAfterCompose(Component comp) throws Exception {
//        super.doAfterCompose(comp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
//
//      Map hashMap = Executions.getCurrent().getArg();
//        String id = (String) hashMap.get("id");
//        tbid.setValue(Integer.valueOf(id));
//
//        windowDeleteData.doModal();
//        
//
//    }
//
//    public void onClick$buttonDelete() {
////
////        int id = tbid.getValue();
////ConnectionUtil.getInstance().testDelete(id);
////List<SiswaVo> listData = new ArrayList<SiswaVo>();
////            SiswaVo vo = new SiswaVo();
//////             vo.setId(id);
////                    listData.add(vo);
////        try {
////            Class.forName("org.postgresql.Driver");
////            String url = "jdbc:postgresql://localhost:5432/sekolah";
////            String username = "postgres";
////            String password = "postgres";
////            Connection connection = DriverManager.getConnection(url, username, password);
////            String sql = "DELETE FROM siswaa WHERE id = '" + id + "'";
////            PreparedStatement statement = connection.prepareStatement(sql);
//
////            statement.setInt(1,id);
////            int rowsInserted = statement.executeUpdate();
////            if (rowsInserted > 0) {
////                System.out.println("hapus!");
//            }
////            connection.close();
////        } catch (SQLException e) {
////            System.out.println("Terjadi error: " + e.getMessage());
////        } catch (ClassNotFoundException ex) {
////            Logger.getLogger(DeleteDataController.class.getName()).log(Level.SEVERE, null, ex);
////        }
////        windowDeleteData.onClose();
////    }
//
//    public void onClick$buttonClose() {
//        windowDeleteData.onClose();
//
//    }
//}
