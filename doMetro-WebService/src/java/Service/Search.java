package Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Search extends HttpServlet {

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

         ArrayList<String> task = new ArrayList<String>();
         ArrayList<String> tag = new ArrayList<String>();
         PreparedStatement stmt;

         String output = "";

         if (request.getParameter("username").equals("1")) {
            stmt = conn.prepareStatement("SELECT * FROM user WHERE username LIKE '%" + request.getParameter("search") + "%' ");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
               output += "<div class=\"search_result\">"
                       + "<div class=\"search_title\">User</div>"
                       + constructUser(rs.getString("username"), rs.getString("name"), rs.getString("avatar"), rs.getString("email"));

               while (rs.next()) {
                  output += constructUser(rs.getString("username"), rs.getString("name"), rs.getString("avatar"), rs.getString("email"));
               }
               output += "</div></div>";
            }
         }

         if (request.getParameter("category").equals("1")) {
            stmt = conn.prepareStatement("SELECT catname FROM category WHERE catname LIKE '%" + request.getParameter("search") + "%' ");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
               output += "<div class=\"search_result\">"
                       + "<div class=\"search_title\">Category</div>"
                       + constructCategory(rs.getString(1));

               while (rs.next()) {
                  output += constructCategory(rs.getString(1));
               }
               output += "</div></div>";
            }
         }

         if (request.getParameter("task").equals("1")) {
            stmt = conn.prepareStatement("SELECT * FROM task WHERE taskname LIKE '%" + request.getParameter("search") + "%' ");
            ResultSet rs = stmt.executeQuery();
            boolean found = false;
            if (rs.next()) {
               found = true;
               task.add(rs.getString("idtask"));
               output += "<div class=\"search_result\">"
                       + "<div class=\"search_title\">Task</div>"
                       + constructTask(rs.getString("idtask"), rs.getString("taskname"), rs.getString("deadline"), rs.getString("status"), getTag(rs.getString("idtask"), conn));

               while (rs.next()) {
                  task.add(rs.getString("idtask"));
                  output += constructTask(rs.getString("idtask"), rs.getString("taskname"), rs.getString("deadline"), rs.getString("status"), getTag(rs.getString("idtask"), conn));
               }
               output += "</div></div>";
            }

            stmt = conn.prepareStatement("SELECT idtask FROM tag WHERE name LIKE '%" + request.getParameter("search") + "%' ");
            rs = stmt.executeQuery();
            if (rs.next()) {
               if (found) {
                  output = output.substring(0, output.length() - "</div></div>".length());
               } else {
                  output += "<div class=\"search_result\">"
                          + "<div class=\"search_title\">Task</div>";
               }
               rs.beforeFirst();
               while (rs.next()) {
                  if (!task.contains(rs.getString(1))) {
                     stmt = conn.prepareStatement("SELECT * FROM task WHERE idtask='" + rs.getString(1) + "'");
                     ResultSet rs2 = stmt.executeQuery();
                     if (rs2.next()) {
                        output += constructTask(rs2.getString("idtask"), rs2.getString("taskname"), rs2.getString("deadline"), rs2.getString("status"), getTag(rs.getString(1), conn));
                     }
                  }
               }
               output += "</div></div>";
            }
         }

         if (output.equals("")) {
            output += "Tidak ada hasil yang ditemukan";
         }
         out.print(output);

      } catch (SQLException e) {
//         System.out.println("Connection to database failed");
         System.out.println(e.getMessage());
         System.out.println(e.getCause());
      } finally {
         try {
            conn.close();
            out.close();
         } catch (SQLException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }

   String constructUser(String username, String fullname, String avatar, String email) {
      return "<div class=\"search_view left\">"
              + "<div class=\"left\">"
              + "<img src=\"../" + avatar + "\" class=\"search_picture\" />"
              + "</div>"
              + " <div class=\"left search_right\">"
              + "<div class=\"left dynamic_content_left\">Username</div>"
              + "<div class=\"left dynamic_content_right\"><a href='profile.jsp?username=" + username + "'>" + username + "</a></div>"
              + "<div class=\"left dynamic_content_left\">Fullname</div>"
              + "<div class=\"left dynamic_content_right\">" + fullname + "</div>"
              + "<div class=\"left dynamic_content_left\">Email</div>"
              + "<div class=\"left dynamic_content_right\">" + email + "</div>"
              + "</div></div>";
   }

   String constructCategory(String name) {
      return "<div class=\"search_view left\">"
              + " <div class=\"left dynamic_content_left\">Name</div>"
              + "<div class=\"left dynamic_content_right\">" + name + "</div>"
              + "</div>";
   }

   String constructTask(String idtask, String taskname, String deadline, String status, String tag) {

      String stat;
      if (status.equals("1")) {
         stat = "Sudah selesai";
      } else {
         stat = "Belum seelesai";
      }

      String out = "<div class=\"search_view left\">"
              + "<img src=\"../img/done.png\" class=\"task_done_button\" onclick=deleteTask2('" + idtask + "') />"
              + "<div class=\"left dynamic_content_left\">Task Name</div>"
              + "<div class=\"left dynamic_content_right\"><a href=\"taskDetail.jsp?idtask=" + idtask + "\">" + taskname + "</a></div>"
              + "<div class=\"left dynamic_content_left\">Deadline</div>"
              + "<div class=\"left dynamic_content_right\">" + deadline + "</div>"
              + "<div class=\"left dynamic_content_left\">Status</div>"
              + "<div class=\"left dynamic_content_right\">" + stat;

      if (status.equals("0")) {
         out += "<a href=\"javascript:void(0)\" onclick=\"javascript:markAsFinished2('" + idtask + "')\"> Mark as finished</a>";
      }
      out += "</div>"
              + "<div class=\"left dynamic_content_left\">Tag</div>"
              + "<div class=\"left dynamic_content_right\">" + tag
              + "</div></div>";
      return out;
   }

   String getTag(String idtask, Connection conn) throws SQLException {
      PreparedStatement stmt = conn.prepareStatement("SELECT name FROM tag WHERE idtask='" + idtask + "'");
      ResultSet rs = stmt.executeQuery();

      ArrayList<String> tag = new ArrayList<String>();
      while (rs.next()) {
         tag.add(rs.getString(1));
      }
      String out = "";
      for (String s : tag) {
         out += s + ", ";
      }
      if (out.contains(",")) {
         return out.substring(0, out.length() - 2);
      } else {
         return out;
      }
   }
}
