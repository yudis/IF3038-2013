package Service;

import Helper.HashGenerator;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class AddTask extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      Connection conn = null;

      try {
         try {
            Class.forName("com.mysql.jdbc.Driver");
         } catch (ClassNotFoundException ex) {
            System.out.println("JDCB driver not found");
         }
         
         try {
            conn = dbConnection.getConnection();
         } catch (ClassNotFoundException ex) {
            Logger.getLogger(Service.Login.class.getName()).log(Level.SEVERE, null, ex);
         }

         String idTask = HashGenerator.getHash();
         PreparedStatement stmt = conn.prepareStatement("SELECT taskname FROM task WHERE idtask=?");
         stmt.setString(1, idTask);
         ResultSet rs = stmt.executeQuery();
         while (rs.next()) {
            idTask = HashGenerator.getHash();
            rs = stmt.executeQuery();
         }
         
         String attachment = "attachment/" + request.getParameter("username") + "_" + idTask + "_" + request.getParameter("attachment");
         stmt = conn.prepareStatement("INSERT INTO task VALUES (?,?,?,?,?,?,?)");
         stmt.setString(1, idTask);
         stmt.setString(2, request.getParameter("idcat"));
         stmt.setString(3, request.getParameter("task_name_input"));
         stmt.setString(4, "0");
         stmt.setString(5, attachment);
         stmt.setString(6, request.getParameter("deadline_input"));
         stmt.setString(7, request.getParameter("username"));
         stmt.executeUpdate();
         
         stmt = conn.prepareStatement("INSERT INTO do VALUES (?,?)");
         stmt.setString(1, request.getParameter("username"));
         stmt.setString(2, idTask);

         stmt.executeUpdate();
         if (!request.getParameter("assignee_input").equals("")) {
            String[] assignee = URLDecoder.decode(request.getParameter("assignee_input"), "UTF-8").split(",");
            for (String a : assignee) {
               stmt = conn.prepareStatement("INSERT INTO do VALUES(?,?)");
               stmt.setString(1, a.trim());
               stmt.setString(2, idTask);
               stmt.executeUpdate();
               
               stmt = conn.prepareCall("INSERT IGNORE INTO able VALUES(?,?)");
               stmt.setString(1, a.trim());
               stmt.setString(2, request.getParameter("idcat"));
               stmt.executeUpdate();
            }
         }
         if (!request.getParameter("tag_input").equals("")) {
            String[] tag = URLDecoder.decode(request.getParameter("tag_input"), "UTF-8").split(",");
            for (String t : tag) {
               stmt = conn.prepareStatement("INSERT INTO tag VALUES (?,?)");
               stmt.setString(1, idTask);
               stmt.setString(2, t.trim());
               stmt.executeUpdate();
            }
         }
         
         JSONObject responseMsg = new JSONObject();
         responseMsg.put("idtask", idTask);
         responseMsg.put("attachment", attachment);
         
         out.print(responseMsg.toString());
      } catch (SQLException e) {
         System.out.println("Connection to database failed");
         System.out.println(e.getMessage());
      } finally {
         try {
            conn.close();
            out.close();
         } catch (SQLException ex) {
            Logger.getLogger(AddTask.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}