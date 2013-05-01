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

@WebServlet(name = "EditTask", urlPatterns = {"/EditTask"})
public class EditTask extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      try {
         String serviceURL = Appfog.getBaseURL() 
                           + "EditTask?idtask=" + request.getParameter("idtask")
                           + "&deadline=" + request.getParameter("deadline")
                           + "&assignee=" + request.getParameter("assignee").replaceAll(" ", "")
                           + "&tag=" + request.getParameter("tag").replaceAll(" ", "");
         Appfog.getResponse(serviceURL);
      } catch (Exception e) {
         System.out.println("Connection to database failed");
      } finally {
         try {
            out.close();
         } catch (Exception ex) {
            Logger.getLogger(EditTask.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
