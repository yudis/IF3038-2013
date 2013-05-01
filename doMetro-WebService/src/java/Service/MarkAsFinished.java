package Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MarkAsFinished extends HttpServlet {

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

         PreparedStatement stmt = conn.prepareStatement("UPDATE task SET status='1' WHERE idtask=?");
         stmt.setString(1, request.getParameter("idtask"));
         stmt.executeUpdate();
         
      } catch (SQLException e) {
         System.out.println("Connection to database failed");
      } finally {
         try {
            conn.close();
            out.close();
         } catch (SQLException ex) {
            Logger.getLogger(MarkAsFinished.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
