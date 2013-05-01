package Servlet;

import Helper.Appfog;
import Helper.FileManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

@WebServlet(name = "SaveEditProfile", urlPatterns = {"/SaveEditProfile"})
@MultipartConfig
public class SaveEditProfile extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      try {
         HttpSession session = request.getSession();
         String avatar = session.getAttribute("avatar").toString();
         Part filePart = request.getPart("avatar_edit");
         if (!FileManager.getFilename(filePart).equals("")) {
            System.out.println("hello");
            avatar = "img/avatar/" + request.getParameter("username_edit") + FileManager.getExtension(FileManager.getFilename(filePart));
            String filePath = getServletContext().getRealPath("/") + avatar;
            Files.copy(filePart.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
         }
         
         String serviceURL = Appfog.getBaseURL() 
                           + "SaveEditProfile?name=" + URLEncoder.encode(request.getParameter("name"), "UTF-8")
                           + "&username_edit=" + request.getParameter("username_edit")
                           + "&birthdate=" + request.getParameter("birthdate")
                           + "&password_edit=" + URLEncoder.encode(request.getParameter("password_edit"), "UTF-8")
                           + "&confirm_password=" + URLEncoder.encode(request.getParameter("confirm_password"), "UTF-8")
                           + "&avatar_edit=" + avatar;
         System.out.println(serviceURL);
         JSONObject responseMsg = new JSONObject (Appfog.getResponse(serviceURL));
         session.setAttribute("name", responseMsg.get("name"));
         session.setAttribute("avatar", responseMsg.get("avatar"));
         
         response.sendRedirect("src/profile.jsp");

      } catch (Exception e) {
         System.out.println("Connection to database failed");
         System.out.println(e.getMessage());
         System.out.println(e.getCause());
         System.out.println(e.getClass());
         System.out.println(e.getStackTrace());
      } finally {
         try {
            out.close();
         } catch (Exception ex) {
            Logger.getLogger(GetProfileInfo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
