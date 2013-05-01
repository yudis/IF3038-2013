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

@WebServlet(name = "AddComment", urlPatterns = {"/AddComment"})
public class AddComment extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      try {
         HttpSession session = request.getSession();
         
         String serviceURL = Appfog.getBaseURL()
                 + "AddComment?username=" + session.getAttribute("username")
                 + "&time=" + request.getParameter("time")
                 + "&idtask=" + request.getParameter("idtask")
                 + "&content=" + request.getParameter("content");
         out.print(Appfog.getResponse(serviceURL));

      } catch (Exception e) {
         System.out.println("Connection to database failed");
         System.out.println(e.getMessage());
      } finally {
         try {
            out.close();
         } catch (Exception ex) {
            Logger.getLogger(AddComment.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
