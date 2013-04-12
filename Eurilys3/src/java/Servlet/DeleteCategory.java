package Servlet;

import Class.HashGenerator;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteCategory", urlPatterns = {"/DeleteCategory"})
public class DeleteCategory extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510010", "root", "");

         PreparedStatement stmt = conn.prepareStatement("DELETE FROM category WHERE idcat=?");
         stmt.setString(1, idCategory);
         stmt.executeUpdate();
         
         stmt = conn.prepareStatement("DELETE FROM able WHERE idcat=?");
         stmt.setString(1, idCategory);
         stmt.executeUpdate();
         
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
            Logger.getLogger(CheckUsername.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
