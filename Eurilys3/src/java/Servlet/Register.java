package Servlet;

import Helper.Appfog;
import Helper.FileManager;
import java.io.IOException;
import java.net.URLEncoder;
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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.json.JSONObject;

@WebServlet(name = "Register", urlPatterns = {"/Register"})
@MultipartConfig
public class Register extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");

      String username = request.getParameter("signup_username");
      String password = request.getParameter("signup_password");
      String name = request.getParameter("signup_name");
      String email = request.getParameter("signup_email");
      String birthdate = request.getParameter("signup_birthdate");
      Part filePart = request.getPart("avatar_upload");
      String avatar = "img/avatar/" + username + FileManager.getExtension(FileManager.getFilename(filePart));
      String filePath = getServletContext().getRealPath("/") + avatar;

      try {
         String serviceURL = Appfog.getBaseURL()
                 + "Register?signup_username=" + username
                 + "&signup_password=" + URLEncoder.encode(password, "UTF-8")
                 + "&signup_name=" + URLEncoder.encode(name, "UTF-8")
                 + "&signup_email=" + email
                 + "&signup_birthdate=" + birthdate
                 + "&avatar_upload=" + avatar;
         
         JSONObject responseMsg = new JSONObject(Appfog.getResponse(serviceURL));

         Files.copy(filePart.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
         HttpSession session = request.getSession(true);
         session.setAttribute("username", responseMsg.get("username"));
         session.setAttribute("name", responseMsg.get("name"));
         session.setAttribute("avatar", responseMsg.get("avatar"));
         response.sendRedirect("src/dashboard.jsp");
      } catch (Exception e) {
         System.out.println("Connection to database failed");
         System.out.println(e.getMessage());
      } finally {
         try {
         } catch (Exception ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
