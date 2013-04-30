package Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@MultipartConfig
public class SaveEditProfile extends HttpServlet {

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

         PreparedStatement stmt;
         JSONObject responseMsg = new JSONObject();

         String name = URLDecoder.decode(request.getParameter("name"), "UTF-8");
         if (!name.equals("")) {
            stmt = conn.prepareStatement("UPDATE user SET name=? WHERE username=?");
            stmt.setString(1, name);
            stmt.setString(2, request.getParameter("username_edit"));
            stmt.executeUpdate();
            responseMsg.put("name", name);
         }

         if (!request.getParameter("birthdate").equals("")) {
            stmt = conn.prepareStatement("UPDATE user SET birthdate=? WHERE username=?");
            stmt.setString(1, request.getParameter("birthdate"));
            stmt.setString(2, request.getParameter("username_edit"));
            stmt.executeUpdate();
         }
         
         String password = URLDecoder.decode(request.getParameter("password_edit"), "UTF-8");
         String confirm = URLDecoder.decode(request.getParameter("confirm_password"), "UTF-8");
         if (!password.equals("")) {
            if (password.equals(confirm)) {
               stmt = conn.prepareStatement("UPDATE user SET password=? WHERE username=?");
               stmt.setString(1, password);
               stmt.setString(2, request.getParameter("username_edit"));
               stmt.executeUpdate();
            }
         }

         String avatar = request.getParameter("avatar_edit");
         stmt = conn.prepareStatement("UPDATE user SET avatar=? WHERE username=?");
         stmt.setString(1, avatar);
         stmt.setString(2, request.getParameter("username_edit"));
         
         if (stmt.executeUpdate() != 0){
            responseMsg.put("avatar", avatar);
         }
         
         out.print(responseMsg.toString());
         
      } catch (SQLException e) {
         System.out.println("Connection to database failed");
         System.out.println(e.getMessage());
      } finally {
         try {
            conn.close();
            out.close();
         } catch (SQLException ex) {
            Logger.getLogger(GetProfileInfo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}