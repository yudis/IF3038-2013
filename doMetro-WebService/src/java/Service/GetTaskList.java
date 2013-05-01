package Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetTaskList extends HttpServlet {

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
         
         String output = "";

         PreparedStatement stmt = conn.prepareStatement("SELECT idtask FROM do WHERE username='" + request.getParameter("username") + "'");
         ResultSet rs = stmt.executeQuery();
         while (rs.next()){
            stmt = conn.prepareStatement("SELECT * FROM task WHERE idtask=? AND idcat=?");
            stmt.setString(1, rs.getString(1));
            stmt.setString(2, request.getParameter("idcat"));
            ResultSet rs2 = stmt.executeQuery();
            while (rs2.next()) {
               String status;
               if ((rs2.getString("status").equals("0"))) {
                  status = "Belum selesai";
               } else {
                  status = "Sudah selesai";
               }

               output += "<div class=\"task_view left\">"
                       + "<img src=\"../img/done.png\" class=\"task_done_button\" onclick=deleteTask('" + rs2.getString("idtask") + "') />"
                       + "<div class=\"left dynamic_content_left\">Task Name</div>"
                       + "<div class=\"left dynamic_content_right\"><a href=\"taskDetail.jsp?idtask=" + rs2.getString("idtask") + "\">" + rs2.getString("taskname") + "</a></div>"
                       + "<div class=\"left dynamic_content_left\">Deadline</div>"
                       + "<div class=\"left dynamic_content_right\">" + rs2.getString("deadline") + "</div>"
                       + "<div class=\"left dynamic_content_left\">Status</div>"
                       + "<div class=\"left dynamic_content_right\">" + status;

               if ("Belum selesai".equals(status)) {
                  output += "<a href=\"javascript:void(0)\" onclick=\"javascript:markAsFinished('" + rs2.getString("idtask") + "')\"> Mark as finished</a>";
               }

               output += "</div>"
                       + "<div class=\"left dynamic_content_left\">Tag</div>"
                       + "<div class=\"left dynamic_content_right\">";

               stmt = conn.prepareStatement("SELECT name FROM tag WHERE idtask='" + rs2.getString("idtask") + "'");
               ResultSet rs3 = stmt.executeQuery();
               while (rs3.next()) {
                  output += rs3.getString(1) + ", ";
               }
               output = output.substring(0, output.length() - 2);
               output += "</div></div>";
            }
         }
         out.print(output);
      } catch (SQLException e) {
         System.out.println("Connection to database failed");
         System.out.println(e.getMessage());
      } finally {
         try {
            conn.close();
            out.close();
         } catch (SQLException ex) {
            Logger.getLogger(GetTaskList.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
