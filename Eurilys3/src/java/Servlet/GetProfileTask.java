package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "GetProfileTask", urlPatterns = {"/GetProfileTask"})
public class GetProfileTask extends HttpServlet {

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
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_471_13510008", "root", "");
         String current, finished;
         current = finished = "";
         
         PreparedStatement stmt = conn.prepareStatement("SELECT idtask FROM do WHERE username=?");
         stmt.setString(1, request.getParameter("username"));
         ResultSet rs = stmt.executeQuery();
         
         while (rs.next()){
            stmt = conn.prepareStatement("SELECT * FROM task WHERE idtask=?");
            stmt.setString(1, rs.getString(1));
            ResultSet rs2 = stmt.executeQuery();
            if (rs2.next()){
               if (rs2.getString("status").equals("0")){
                  current += "<li><a href='taskDetail.jsp?idtask="+rs.getString("idtask") +"'>" + rs2.getString("taskname") + "</a></li>";
               }
               else{
                  finished += "<li><a href='taskDetail.jsp?idtask="+rs.getString("idtask") +"'>" + rs2.getString("taskname") + "</a></li>";
               }
            }
         }
         out.print(current + "|@|" + finished);
      } catch (SQLException e) {
         System.out.println("Connection to database failed");
      } finally {
         try {
            conn.close();
            out.close();
         } catch (SQLException ex) {
            Logger.getLogger(GetProfileInfo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
