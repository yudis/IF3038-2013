package Servlet;

import Helper.Appfog;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

@WebServlet(name = "DeleteTask", urlPatterns = {"/DeleteTask"})
public class DeleteTask extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      try {
         HttpSession session = request.getSession();
         String serviceURL = Appfog.getBaseURL() 
                              + "DeleteTask?username=" + session.getAttribute("username") 
                              + "&idtask=" + request.getParameter("idtask");
         JSONObject responseMsg = new JSONObject(Appfog.getResponse(serviceURL));
         System.out.println(responseMsg.toString());
         if (responseMsg.getString("type").equals("hard")) {
               Files.deleteIfExists(Paths.get(getServletContext().getRealPath("/") + responseMsg.getString("attachment")));
         }

      } catch (Exception e) {
         System.out.println("Connection to database failed");
      } finally {
         try {
            out.close();
         } catch (Exception ex) {
            Logger.getLogger(DeleteTask.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
