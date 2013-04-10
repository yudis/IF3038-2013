/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Agah
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
@MultipartConfig
public class Register extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      Connection conn = null;

      String username = request.getParameter("signup_username");
      String password = request.getParameter("signup_password");
      String name = request.getParameter("signup_name");
      String email = request.getParameter("signup_email");
      String birthdate = request.getParameter("signup_birthdate");
      Part filePart = request.getPart("avatar_upload");
      String avatar = "img/avatar/" + getFilename(filePart);
      String filePath = getServletContext().getRealPath("/") + avatar;

      /* BEGIN File exist resolver */
      File file = new File(filePath);
      int i = 2;
      while (file.exists()) {
         avatar = incrementFilename(avatar, i++);
         filePath = getServletContext().getRealPath("/") + avatar;
         file = new File(filePath);
      }
      /* END File exist resolver */
      
      try {
         try {
            Class.forName("com.mysql.jdbc.Driver");
         } catch (ClassNotFoundException ex) {
            System.out.println("JDCB driver not found");
         }
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510010", "root", "");

         PreparedStatement stmt = conn.prepareStatement("INSERT INTO user VALUES (?,?,?,?,?,?);");
         stmt.setString(1, username);
         stmt.setString(2, password);
         stmt.setString(3, name);
         stmt.setString(4, email);
         stmt.setString(5, birthdate);
         stmt.setString(6, avatar);
         
         if (stmt.executeUpdate() != 0) {
            Files.copy(filePart.getInputStream(), Paths.get(filePath));
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            session.setAttribute("name", name);
            response.sendRedirect("src/dashboard.jsp");
         }
      } catch (SQLException e) {
         System.out.println("Connection to database failed");
         System.out.println(e.getMessage());
      } finally {
         try {
            conn.close();
         } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }

   private static String getFilename(Part part) {
      for (String cd : part.getHeader("content-disposition").split(";")) {
         if (cd.trim().startsWith("filename")) {
            String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
         }
      }
      return null;
   }

   private static String incrementFilename(String filename, int i) {
      int indexOfDot = filename.indexOf(".");
      if (filename.indexOf(")") != indexOfDot - 1) {
         return filename.substring(0, indexOfDot) + "(" + i + ")"
                 + filename.substring(indexOfDot);
      } else {
         return filename.substring(0, indexOfDot - 2) + i + ")"
                 + filename.substring(indexOfDot);
      }
   }
}
