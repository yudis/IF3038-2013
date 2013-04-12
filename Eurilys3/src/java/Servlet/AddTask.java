package Servlet;

import Class.FileManager;
import Class.HashGenerator;
import java.io.IOException;
import java.io.PrintWriter;
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
         try {
            Class.forName("com.mysql.jdbc.Driver");
         } catch (ClassNotFoundException ex) {
            System.out.println("JDCB driver not found");
         }
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510010", "root", "");

         String idTask = HashGenerator.getHash();
         PreparedStatement stmt = conn.prepareStatement("SELECT taskname FROM task WHERE idtask=?");
         stmt.setString(1, idTask);
         ResultSet rs = stmt.executeQuery();

         while (rs.next()) {
            idTask = HashGenerator.getHash();
            rs = stmt.executeQuery();
         }
         Part filePart = request.getPart("attachment_upload");
         String attachment = "attachment/" + request.getParameter("username") + "_" + idTask + "_" + FileManager.getFilename(filePart);
         String filePath = getServletContext().getRealPath("/") + attachment;

         stmt = conn.prepareStatement("INSERT INTO task VALUES (?,?,?,?,?,?,?)");
         stmt.setString(1, idTask);
         stmt.setString(2, request.getParameter("idcat"));
         stmt.setString(3, request.getParameter("task_name_input"));
         stmt.setString(4, "0");
         stmt.setString(5, attachment);
         stmt.setString(6, request.getParameter("deadline_input"));
         stmt.setString(7, request.getParameter("username"));

         if (stmt.executeUpdate() != 0) {
            Files.copy(filePart.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
         }

         stmt = conn.prepareStatement("INSERT INTO do VALUES (?,?)");
         stmt.setString(1, request.getParameter("username"));
         stmt.setString(2, idTask);

         stmt.executeUpdate();

         if (!request.getParameter("assignee_input").equals("")) {
            String[] assignee = request.getParameter("assignee_input").split(",");
            for (String a : assignee) {
               stmt = conn.prepareStatement("INSERT INTO do VALUES(?,?)");
               stmt.setString(1, a.trim());
               stmt.setString(2, idTask);
               stmt.executeUpdate();
            }
         }

         if (!request.getParameter("tag_input").equals("")) {
            String[] tag = request.getParameter("tag_input").split(",");
            for (String t : tag) {
               stmt = conn.prepareStatement("INSERT INTO tag VALUES (?,?)");
               stmt.setString(1, idTask);
               stmt.setString(2, t.trim());
               stmt.executeUpdate();
            }
         }

      } catch (SQLException e) {
         System.out.println("Connection to database failed");
         System.out.println(e.getMessage());
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
