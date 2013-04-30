package Service;

import Helper.HashGenerator;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddComment extends HttpServlet {

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
         
         String idComment = HashGenerator.getHash();
         PreparedStatement stmt = conn.prepareStatement("SELECT content FROM comment WHERE idcomment=?");
         stmt.setString(1, idComment);
         ResultSet rs = stmt.executeQuery();

         while (rs.next()) {
            idComment = HashGenerator.getHash();
            rs = stmt.executeQuery();
         }
         
         Timestamp time = new Timestamp(Long.parseLong(request.getParameter("time")));
         
         stmt = conn.prepareStatement("INSERT INTO comment VALUES (?,?,?,?,?)");
         stmt.setString(1, idComment);
         stmt.setString(2, request.getParameter("username"));
         stmt.setString(3, request.getParameter("idtask"));
         stmt.setString(4, request.getParameter("content"));
         stmt.setString(5, time.toString());
         
         if (stmt.executeUpdate() != 0){
            out.print("true");
         }
         else{
            out.print("false");
         }

      } catch (SQLException e) {
         System.out.println("Connection to database failed");
         System.out.println(e.getMessage());
      } finally {
         try {
            conn.close();
            out.close();
         } catch (SQLException ex) {
            Logger.getLogger(AddComment.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
