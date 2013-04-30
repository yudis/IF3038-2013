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

public class GetCategory extends HttpServlet {

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

         PreparedStatement stmt = conn.prepareStatement("SELECT idcat FROM able WHERE username=?");
         stmt.setString(1, request.getParameter("username"));
         ResultSet rs = stmt.executeQuery();
         ResultSet rs2;

         String output = "";
         while (rs.next()) {
            stmt = conn.prepareStatement("SELECT catname FROM category WHERE idcat=?");
            stmt.setString(1, rs.getString(1));
            rs2 = stmt.executeQuery();
            if (rs2.next()) {
               output += "<li><a onclick=\"" + "categoryActive('" + rs.getString(1) 
                       + "')\">" + rs2.getString(1) + "</a></li>";
            }
         }
         out.print(output);

      } catch (SQLException e) {
         System.out.println("Connection to database failed");
      } finally {
         try {
            conn.close();
            out.close();
         } catch (SQLException ex) {
            Logger.getLogger(GetCategory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
