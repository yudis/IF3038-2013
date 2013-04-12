/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Agah
 */
@WebServlet(name = "EditTask", urlPatterns = {"/EditTask"})
public class EditTask extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510010", "root", "");

         PreparedStatement stmt = conn.prepareStatement("UPDATE task SET deadline=? WHERE idtask=?");
         stmt.setString(1, request.getParameter("deadline"));
         stmt.setString(2, request.getParameter("idtask"));
         stmt.executeUpdate();

         stmt = conn.prepareStatement("DELETE FROM tag WHERE idtask=?");
         stmt.setString(1, request.getParameter("idtask"));
         stmt.executeUpdate();

         stmt = conn.prepareStatement("DELETE FROM do WHERE idtask=?");
         stmt.setString(1, request.getParameter("idtask"));
         stmt.executeUpdate();

         stmt = conn.prepareStatement("SELECT creator FROM task WHERE idtask=?");
         stmt.setString(1, request.getParameter("idtask"));

         ResultSet rs = stmt.executeQuery();

         if (rs.next()) {
            stmt = conn.prepareStatement("INSERT IGNORE INTO do VALUES(?,?)");
            stmt.setString(1, rs.getString(1));
            stmt.setString(2, request.getParameter("idtask"));
            stmt.executeUpdate();
         }

         if (!request.getParameter("assignee").equals("")) {
            String[] assignee = request.getParameter("assignee").split(",");
            for (String a : assignee) {
               stmt = conn.prepareStatement("INSERT IGNORE INTO do VALUES(?,?)");
               stmt.setString(1, a.trim());
               stmt.setString(2, request.getParameter("idtask"));
               stmt.executeUpdate();
            }
         }

         if (!request.getParameter("tag").equals("")) {
            String[] tag = request.getParameter("tag").split(",");
            for (String t : tag) {
               stmt = conn.prepareStatement("INSERT IGNORE INTO tag VALUES(?,?)");
               stmt.setString(1, request.getParameter("idtask"));
               stmt.setString(2, t.trim());
               stmt.executeUpdate();
            }
         }

      } catch (SQLException e) {
         System.out.println("Connection to database failed");
      } finally {
         try {
            conn.close();
            out.close();
         } catch (SQLException ex) {
            Logger.getLogger(CheckUsername.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection can not be closed");
         }
      }
   }
}
