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
import org.json.JSONObject;

public class AutoComplete extends HttpServlet {

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
         
         ArrayList<String> username = new ArrayList<String>();
         ArrayList<String> category = new ArrayList<String>();
         ArrayList<String> task = new ArrayList<String>();
         ArrayList<String> tag = new ArrayList<String>();
         PreparedStatement stmt;
         if (request.getParameter("username").equals("1")){
            stmt = conn.prepareStatement("SELECT username FROM user WHERE username LIKE '%"+ request.getParameter("val") +"%' ");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
               username.add(rs.getString(1));
            }
         }
         
         if (request.getParameter("category").equals("1")){
            stmt = conn.prepareStatement("SELECT catname FROM category WHERE catname LIKE '%" + request.getParameter("val") + "%'");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
               category.add(rs.getString(1));
            }
         }
         if (request.getParameter("task").equals("1")){
            stmt = conn.prepareStatement("SELECT * FROM task WHERE taskname LIKE '%" + request.getParameter("val") + "%'");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
               task.add(rs.getString("taskname"));
            }
            
            stmt = conn.prepareStatement("SELECT name FROM tag WHERE name LIKE '%" + request.getParameter("val") + "%'");
            rs = stmt.executeQuery();
            while (rs.next()){
               tag.add(rs.getString(1));
            }
         }
         
         JSONObject responseMsg = new JSONObject();
         responseMsg.put("username", username);
         responseMsg.put("category", category);
         responseMsg.put("task", task);
         responseMsg.put("tag", tag);
         
         out.print(responseMsg.toString());
      } catch (SQLException e) {
//         System.out.println("Connection to database failed");
         System.out.println(e.getMessage());
         System.out.println(e.getCause());
      } finally {
         try {
            conn.close();
            out.close();
         } catch (SQLException ex) {
            Logger.getLogger(AutoComplete.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
