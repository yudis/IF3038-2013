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

public class DeleteCategory extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      Connection conn = null;

      String idCategory = request.getParameter("idcat");
      
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
         
         PreparedStatement stmt = conn.prepareStatement("DELETE FROM category WHERE idcat=?");
         stmt.setString(1, idCategory);
         stmt.executeUpdate();
         
         stmt = conn.prepareStatement("DELETE FROM able WHERE idcat=?");
         stmt.setString(1, idCategory);
         stmt.executeUpdate();
         
         stmt = conn.prepareStatement("SELECT idtask FROM task WHERE idcat=?");
         stmt.setString(1, idCategory);
         ResultSet rs = stmt.executeQuery();
         
         while (rs.next()){
            stmt = conn.prepareStatement("DELETE FROM do WHERE idtask=?");
            stmt.setString(1, rs.getString(1));
            stmt.executeUpdate();
            
            stmt = conn.prepareStatement("DELETE FROM tag WHERE idtask=?");
            stmt.setString(1, rs.getString(1));
            stmt.executeUpdate();
            
            stmt = conn.prepareStatement("DELETE FROM comment WHERE idtask=?");
            stmt.setString(1, rs.getString(1));
            stmt.executeUpdate();
         }
         
         stmt = conn.prepareStatement("DELETE FROM task WHERE idcat=?");
         stmt.setString(1, idCategory);
         stmt.executeUpdate();
         
      } catch (SQLException e) {
         System.out.println("Connection to database failed");
         System.out.println(e.getMessage());
      } finally {
         try {
            conn.close();
            out.close();
         } catch (SQLException ex) {
            Logger.getLogger(DeleteCategory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}