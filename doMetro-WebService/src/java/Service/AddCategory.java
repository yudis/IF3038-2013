package Service;

import Helper.HashGenerator;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCategory extends HttpServlet {

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

         String idCategory = HashGenerator.getHash();
         PreparedStatement stmt = conn.prepareStatement("SELECT catname FROM category WHERE idcat=?");
         stmt.setString(1, idCategory);
         ResultSet rs = stmt.executeQuery();

         while (rs.next()) {
            idCategory = HashGenerator.getHash();
            rs = stmt.executeQuery();
         }

         stmt = conn.prepareStatement("INSERT INTO category VALUES (?,?,?)");
         stmt.setString(1, idCategory);
         stmt.setString(2, request.getParameter("category"));
         stmt.setString(3, request.getParameter("username"));

         stmt.executeUpdate();

         stmt = conn.prepareStatement("INSERT INTO able VALUES (?,?)");
         stmt.setString(1, request.getParameter("username"));
         stmt.setString(2, idCategory);

         stmt.executeUpdate();

         if (!request.getParameter("assignee").equals("")) {
            String[] assignee = URLDecoder.decode(request.getParameter("assignee"), "UTF-8").split(",");
            for (String a : assignee) {
               stmt = conn.prepareStatement("INSERT INTO able VALUES(?,?)");
               stmt.setString(1, a.trim());
               stmt.setString(2, idCategory);
               stmt.executeUpdate();
            }
         }

      } catch (SQLException e) {
         System.out.println("Connection to database failed");
         System.out.println(e.getMessage());
      } finally {
         try {
            conn.close();
            out.close();
         } catch (SQLException ex) {
            Logger.getLogger(AddCategory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
