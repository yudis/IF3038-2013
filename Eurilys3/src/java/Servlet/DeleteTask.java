package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Agah
 */
@WebServlet(name = "DeleteTask", urlPatterns = {"/DeleteTask"})
public class DeleteTask extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510010", "root", "");

         PreparedStatement stmt = conn.prepareStatement("SELECT attachment FROM task WHERE idtask=?");
         stmt.setString(1, request.getParameter("idtask"));
         ResultSet rs = stmt.executeQuery();
         
         if (rs.next()){
            Files.delete(Paths.get(getServletContext().getRealPath("/") + rs.getString(1)));
         }
         
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
         
      } catch (SQLException e) {
         System.out.println("Connection to database failed");
      } finally {
         try {
            conn.close();
            out.close();
         } catch (SQLException ex) {
            Logger.getLogger(CheckUsername.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
