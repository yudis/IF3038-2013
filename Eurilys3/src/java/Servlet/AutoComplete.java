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
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "AutoComplete", urlPatterns = {"/AutoComplete"})
public class AutoComplete extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      try {
         String serviceURL = Appfog.getBaseURL() 
                           + "AutoComplete?val=" + request.getParameter("val") 
                           + "&username=" + request.getParameter("username")
                           + "&category=" + request.getParameter("category")
                           + "&task=" + request.getParameter("task");
         String responseAF = Appfog.getResponse(serviceURL);
         
         JSONObject searchResult = new JSONObject(responseAF);
         String output = "";
         
         JSONArray tmp = new JSONArray(searchResult.get("username").toString());
         for (int i = 0; i < tmp.length(); i++){
            output += "<option value='" + tmp.getString(i) + "' />username";
         }
         
         tmp = new JSONArray(searchResult.get("category").toString());
         for (int i = 0; i < tmp.length(); i++){
            output += "<option value='" + tmp.getString(i) + "' />category";
         }
         
         tmp = new JSONArray(searchResult.get("task").toString());
         for (int i = 0; i < tmp.length(); i++){
            output += "<option value='" + tmp.getString(i) + "' />task";
         }
         
         tmp = new JSONArray(searchResult.get("tag").toString());
         for (int i = 0; i < tmp.length(); i++){
            output += "<option value='" + tmp.getString(i) + "' />tag";
         }
         
         out.print(output);

      } catch (Exception e) {
//         System.out.println("Connection to database failed");
         System.out.println(e.getMessage());
         System.out.println(e.getCause());
      } finally {
         try {
            out.close();
         } catch (Exception ex) {
            Logger.getLogger(AutoComplete.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
