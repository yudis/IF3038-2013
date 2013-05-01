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

public class GetComment extends HttpServlet {

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

         PreparedStatement stmt = conn.prepareStatement("SELECT * FROM comment WHERE idtask=? ORDER BY time");
         stmt.setString(1, request.getParameter("idtask"));
         ResultSet rs = stmt.executeQuery();

         while (rs.next()) {
            stmt = conn.prepareStatement("SELECT * FROM user WHERE username=?");
            stmt.setString(1, rs.getString("username"));

            ResultSet rs2 = stmt.executeQuery();
            if (rs2.next()) {
               String timestamp = rs.getString("time");
               String date = timestamp.substring(8,10) + "/" + timestamp.substring(5,7);
               String time = timestamp.substring(11, 16);
               
               output += "<div class=\"comment_row\">"
                       + " <div class=\"comment_picture_container left\">"
                       + "<img src=\"../"+ rs2.getString("avatar") +"\" class=\"comment_picture left\" />"
                       + "</div>"
                       + "<div class =\"left comment_content\">"
                       + "<b><a href='profile.jsp?username=" + rs2.getString("username") + "'>"+ rs2.getString("name") +"</a> </b>" + rs.getString("content")
                       + "<br>"
                       + "<i>"+ time + " - " + date +"</i>"
                       + "</div></div>";
            }
         }

         out.print(output);

      } catch (SQLException e) {
         System.out.println("Connection to database failed");
         System.out.println(e.getMessage());
         System.out.println(e.getCause());
      } finally {
         try {
            conn.close();
            out.close();
         } catch (SQLException ex) {
            Logger.getLogger(GetComment.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}