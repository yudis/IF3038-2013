package Service;

import java.io.IOException;
import java.io.PrintWriter;
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

public class DeleteTask extends HttpServlet {

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
        
         String username = request.getParameter("username");
         System.out.println(username + request.getParameter("idtask"));
         boolean isCreator = false;
         PreparedStatement stmt = conn.prepareStatement("SELECT attachment,creator FROM task WHERE idtask=?");
         stmt.setString(1, request.getParameter("idtask"));
         ResultSet rs = stmt.executeQuery();
         
         String attachment = "";
         if (rs.next()) {
            if (rs.getString(2).equals(username)) {
               isCreator = true;
               attachment = rs.getString(1);
            }
         }
         if (isCreator) {
            stmt = conn.prepareStatement("DELETE FROM task WHERE idtask=?");
            stmt.setString(1, request.getParameter("idtask"));
            stmt.executeUpdate();

            stmt = conn.prepareStatement("DELETE FROM do WHERE idtask=?");
            stmt.setString(1, request.getParameter("idtask"));
            stmt.executeUpdate();

            stmt = conn.prepareStatement("DELETE FROM tag WHERE idtask=?");
            stmt.setString(1, request.getParameter("idtask"));
            stmt.executeUpdate();

            stmt = conn.prepareStatement("DELETE FROM comment WHERE idtask=?");
            stmt.setString(1, request.getParameter("idtask"));
            stmt.executeUpdate();
            
            JSONObject responseMsg = new JSONObject();
            responseMsg.put("type", "hard");
            responseMsg.put("attachment", attachment);
            out.print(responseMsg.toString());
         }
         else{
            stmt = conn.prepareStatement("DELETE FROM do WHERE idtask=? AND username=?");
            stmt.setString(1, request.getParameter("idtask"));
            stmt.setString(2, username);
            stmt.executeUpdate();
         
            JSONObject responseMsg = new JSONObject();
            responseMsg.put("type", "soft");
            out.print(responseMsg.toString());
         }

      } catch (SQLException e) {
         System.out.println("Connection to database failed");
      } finally {
         try {
            conn.close();
            out.close();
         } catch (SQLException ex) {
            Logger.getLogger(DeleteTask.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}

