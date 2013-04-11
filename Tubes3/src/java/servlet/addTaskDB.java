/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author M Afif Al Hawari
 */
@WebServlet(name = "addTaskDB", urlPatterns = {"/addTaskDB"})
public class addTaskDB extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PrintWriter out = response.getWriter();
        String taskname = "";
        String deadline = "";
        String assignee = "";
        String tag = "";
        String files = "";
        int category = 0;
        int userID = 0;
        ArrayList<String> target = new ArrayList<String>();

        try {
            //make a connection
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Berhasil connect ke Mysql JDBC Driver ... ");
            } catch (ClassNotFoundException ex) {
                System.out.println("Where is your MySQL JDBC Driver?");
            }
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510020", "root", "");

            File file;
            DiskFileItemFactory factory = new DiskFileItemFactory();
            String contextRoot = getServletContext().getRealPath("/");
            String filePath = contextRoot + "..\\..\\web\\attachment\\";
            System.out.println(filePath);
            factory.setRepository(new File(contextRoot + "..\\..\\web\\temp"));
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List fileItems = upload.parseRequest(request);
                Iterator i = fileItems.iterator();
                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (!fi.isFormField()) {
                        String fieldName = fi.getFieldName();
                        String fileName = fi.getName();
                        System.out.println("File name : " + fileName);
                        if (fileName.lastIndexOf("\\") >= 0) {
                            file = new File(filePath
                                    + taskname + "-" + fileName.substring(fileName.lastIndexOf("\\")));
                        } else {
                            file = new File(filePath
                                    + taskname + "-" + fileName.substring(fileName.lastIndexOf("\\") + 1));
                        }
                        fi.write(file);
                        String temptarget = "attachment/" + taskname + "-" + fileName;
                        target.add(temptarget);
                    } else {
                        String fieldName = fi.getFieldName();
                        String fieldValue = fi.getString();
                        if (fieldName.compareTo("namaTask") == 0) {
                            taskname = fieldValue;
                        } else if (fieldName.compareTo("newDeadlineTask") == 0) {
                            deadline = fieldValue;
                        } else if (fieldName.compareTo("newAssigneeTask") == 0) {
                            assignee = fieldValue;
                        } else if (fieldName.compareTo("newTagTask") == 0) {
                            tag = fieldValue;
                        } else if (fieldName.compareTo("newCategoryID") == 0) {
                            category = Integer.parseInt(fieldValue);
                        } else if (fieldName.compareTo("newUserID") == 0) {
                            userID = Integer.parseInt(fieldValue);
                        }
                        System.out.println(fieldName + " - " + fieldValue);
                    }
                }
            } catch (FileUploadException ex) {
                Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
            }

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO `task` (`IDTask` ,`IDCategory` ,`TaskName` ,`Status` ,`Deadline`,`Creator`)"
                    + "VALUES (NULL , ?,?,'undone',?,?);");
            ps.setInt(1, category);
            ps.setString(2, taskname);
            ps.setString(3, deadline);
            ps.setInt(4, userID);
            int i = ps.executeUpdate();

            ps = conn.prepareStatement(
                    "SELECT * FROM task;");
            ResultSet rs = ps.executeQuery();
            int idTask = 0;
            while (rs.next()) {
                idTask = Integer.parseInt(rs.getString("IDTask"));
            }

            String[] assigneelist = assignee.split(";");

            ps = conn.prepareStatement(
                    "INSERT INTO assignment (`IDAssignment`, `Username`, `IDTask`)"
                    + "VALUES (NULL,?,?)");
            ps.setInt(1, userID);
            ps.setInt(2, idTask);
            i = ps.executeUpdate();

            for (int x = 0; x < assigneelist.length; x++) {
                ps = conn.prepareStatement(
                        "SELECT * FROM assignment WHERE Username=? and IDTask=?;");
                ps.setString(1, assigneelist[x]);
                ps.setInt(2, idTask);
                rs = ps.executeQuery();
                int count = 0;
                while (rs.next()) {
                    count++;
                }
                if (count == 0) {
                    ps = conn.prepareStatement(
                            "INSERT INTO assignment (`IDAssignment`, `Username`, `IDTask`)"
                            + "VALUES (NULL,?,?)");
                    ps.setString(1, assigneelist[x]);
                    ps.setInt(2, idTask);
                    i = ps.executeUpdate();
                }
            }

            String[] taglist = tag.split(",");
           // System.out.println("panjangnya nih "+taglist.length+taglist[3] );
            for (int x = 0; x < taglist.length; x++) {
                ps = conn.prepareStatement(
                        "SELECT * FROM tag WHERE TagName=?");
                ps.setString(1, taglist[x]);
                rs = ps.executeQuery();
                int count = 0;
                int idTag=0;
                while (rs.next()) {
                    count++;
                    idTag = Integer.parseInt(rs.getString("IDTag"));
                }
                if (count == 0) {
                    ps = conn.prepareStatement(
                            "INSERT INTO `tag` (`IDTag` ,`TagName`)"
                            + "VALUES (NULL,?)");
                    ps.setString(1, taglist[x]);
                    i = ps.executeUpdate();

                    ps = conn.prepareStatement(
                            "SELECT * from tag");
                    rs = ps.executeQuery();
                    int counts = 0;
                    while (rs.next()) {
                        counts++;
                    }
                    idTag = counts;
                }
                ps = conn.prepareStatement(
                        "INSERT INTO `tasktag` (`IDTaskTag` ,`IDTask` ,`IDTag`)"
                        + "VALUES (NULL,?,?)");
                ps.setInt(1, idTask);
                ps.setInt(2, idTag);
                i = ps.executeUpdate();
            }
            
            for(int x=0;x<target.size();x++){
                 ps = conn.prepareStatement(
                        "INSERT INTO `attachment` (`IDAttachment`, `IDTask`, `PathFile`)"
                        + "VALUES (NULL,?,?)");
                ps.setInt(1, idTask);
                ps.setString(2, target.get(x));
                i = ps.executeUpdate();
            }
            response.sendRedirect("RinciTugas.jsp?IDTask="+idTask);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Can not close connection");
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
