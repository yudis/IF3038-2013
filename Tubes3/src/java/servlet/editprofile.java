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
import java.sql.SQLException;
import java.sql.Statement;
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
 * @author Frilla
 */
@WebServlet(name = "editprofile", urlPatterns = {"/editprofile"})
public class editprofile extends HttpServlet {
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
        HttpSession session = request.getSession(true);
        String username = (String)session.getAttribute("username");
        Connection conn = null;
        PrintWriter out = response.getWriter();
        String fullname = request.getParameter("regname");
        String password = request.getParameter("regpassword1");
        String password2 = request.getParameter("regpassword2");
        String date = request.getParameter("regdate");
        String target = request.getParameter("regfile");
        try {
            //make a connection
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Berhasil connect ke Mysql JDBC Driver ... ");
            } catch (ClassNotFoundException ex) {
                System.out.println("Where is your MySQL JDBC Driver?");
            }
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510020", "root", "");
            Statement ps = conn.createStatement();
            
            if (fullname.compareTo("") != 0) {
                ps.executeUpdate("UPDATE user SET Fullname='"+ fullname +"' WHERE Username = '" + username + "'");
                System.out.println("SATU");
            }
            if (password.compareTo("") != 0) {
                if (password.equals(password2)) {
                    ps.executeUpdate("UPDATE user SET Password='"+password+"' WHERE Username = '"+ username +"'");
                } else {
                    out.print ("<script type='text/javascript'>alert('password salah')</script>");
                }
            }
            
            if (date.compareTo("") != 0) {
                ps.executeUpdate("UPDATE user SET DateOfBirth='"+date+"' WHERE Username = '"+username+"'");
            }
            if (target != null) {
                try {
                    File file;
                    DiskFileItemFactory factory = new DiskFileItemFactory(); 
                    String contextRoot = getServletContext().getRealPath("/");
                    String filePath = contextRoot + "..\\..\\web\\img\\";
                    System.out.println(filePath);
                    factory.setRepository(new File(contextRoot + "..\\..\\web\\temp"));
                    ServletFileUpload upload = new ServletFileUpload(factory);
            
                    List fileItems = upload.parseRequest(request);
                    Iterator i = fileItems.iterator();
                    
                    while (i.hasNext()) {
                        System.out.println("eaa");
                        FileItem fi = (FileItem) i.next();
                        if (!fi.isFormField()) {
                            String fieldName = fi.getFieldName();
                            String fileName = fi.getName();
                            System.out.println("File name : " + fileName);
                            if (fileName.lastIndexOf("\\") >= 0) {
                                file = new File(filePath
                                        + fileName.substring(fileName.lastIndexOf("\\")));
                            } else {
                                file = new File(filePath
                                        + fileName.substring(fileName.lastIndexOf("\\") + 1));
                            }
                            fi.write(file);
                            target = "img/"+fileName;
                        } 
                    }
                } catch (FileUploadException ex) {
                    Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                ps.executeUpdate("UPDATE user SET Avatar='"+target+"' WHERE Username = '"+username+"'");
            }
            
            response.sendRedirect("profile.jsp?user="+username);
            System.out.println("HALO");
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
