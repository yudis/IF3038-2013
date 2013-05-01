package Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

public class Register extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      Connection conn = null;

      String username = request.getParameter("signup_username");
      String password = URLDecoder.decode(request.getParameter("signup_password"), "UTF-8");
      String name = URLDecoder.decode(request.getParameter("signup_name"), "UTF-8");
      String email = request.getParameter("signup_email");
      String birthdate = request.getParameter("signup_birthdate");
      String avatar = request.getParameter("avatar_upload");
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
         
         PreparedStatement stmt = conn.prepareStatement("INSERT INTO user VALUES (?,?,?,?,?,?);");
         stmt.setString(1, username);
         stmt.setString(2, password);
         stmt.setString(3, name);
         stmt.setString(4, email);
         stmt.setString(5, birthdate);
         stmt.setString(6, avatar);
         
         if (stmt.executeUpdate() != 0) {
            JSONObject responseMsg = new JSONObject();
            responseMsg.put("username", username);
            responseMsg.put("name", name);
            responseMsg.put("avatar", avatar);
            
            out.print(responseMsg.toString());
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
}
