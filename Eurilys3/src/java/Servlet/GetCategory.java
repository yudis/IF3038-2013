package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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

@WebServlet(name = "GetCategory", urlPatterns = {"/GetCategory"})
public class GetCategory extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      Connection conn = null;

      String username = request.getParameter("username");
      try {
         try {
            Class.forName("com.mysql.jdbc.Driver");
         } catch (ClassNotFoundException ex) {
            System.out.println("JDCB driver not found");
         }
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510010", "root", "");

         PreparedStatement stmt = conn.prepareStatement("SELECT idcat FROM able WHERE username=?");
         stmt.setString(1, username);
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
            Logger.getLogger(CheckUsername.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
