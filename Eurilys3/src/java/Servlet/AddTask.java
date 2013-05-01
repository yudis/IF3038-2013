package Servlet;

import Helper.Appfog;
import Helper.FileManager;
import Helper.HashGenerator;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.json.JSONObject;

@WebServlet(name = "AddTask", urlPatterns = {"/AddTask"})
@MultipartConfig
public class AddTask extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      Connection conn = null;

      try {
         
         Part filePart = request.getPart("attachment_upload");
         String attachment = FileManager.getFilename(filePart);
         
         
         String serviceURL = Appfog.getBaseURL() 
                           + "AddTask?idcat=" + request.getParameter("idcat")
                           + "&task_name_input=" + request.getParameter("task_name_input")
                           + "&username=" + request.getParameter("username")
                           + "&attachment=" + attachment
                           + "&deadline_input=" + request.getParameter("deadline_input")
                           + "&assignee_input=" + URLEncoder.encode(request.getParameter("assignee_input"), "UTF-8")
                           + "&tag_input=" + URLEncoder.encode(request.getParameter("tag_input"), "UTF-8");
         
         JSONObject responseMsg = new JSONObject(Appfog.getResponse(serviceURL));
         
         attachment = responseMsg.getString("attachment");
         
         String filePath = getServletContext().getRealPath("/") + attachment;
         Files.copy(filePart.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
         response.sendRedirect("src/taskDetail.jsp?idtask=" + responseMsg.getString("idtask"));
         
      } catch (Exception e) {
         System.out.println("Connection to database failed");
         System.out.println(e.getMessage());
      } finally {
         try {
            out.close();
         } catch (Exception ex) {
            Logger.getLogger(AddTask.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
