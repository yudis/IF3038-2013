package Servlet;

import Helper.Appfog;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      String username = request.getParameter("username");
      String password = request.getParameter("password");
      try {
         try {
            Class.forName("com.mysql.jdbc.Driver");
         } catch (ClassNotFoundException ex) {
            System.out.println("JDCB driver not found");
         }
         
         String serviceURL = Appfog.getBaseURL() + "Login?username=" + username + "&password=" + password;
         String responseAF = Appfog.getResponse(serviceURL);
         
         if (responseAF.equalsIgnoreCase("false")) {
            out.print("false");
         } else {
            JSONObject sessionInfo = new JSONObject(responseAF);
            HttpSession session = request.getSession(true);
            session.setAttribute("username", sessionInfo.get("username"));
            session.setAttribute("name", sessionInfo.get("name"));
            session.setAttribute("avatar", sessionInfo.get("avatar"));
            out.print("true");
         }
      } catch (Exception e) {
         System.out.println("Connection to database failed");
         System.out.println(e.getMessage());
      } finally {
         try {
            out.close();
         } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
