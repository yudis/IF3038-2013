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
@WebServlet(name = "register", urlPatterns = {"/register"})
public class register extends HttpServlet {

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
        String username = request.getParameter("regusername");
        String fullname = request.getParameter("regname");
        String password = request.getParameter("regpassword1");
        String email = request.getParameter("regemail");
        String date = request.getParameter("regdate");
        String target = "img/foto_anonim.png";
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
            String filePath = contextRoot + "..\\..\\web\\img\\";
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
                                    + fileName.substring(fileName.lastIndexOf("\\")));
                        } else {
                            file = new File(filePath
                                    + fileName.substring(fileName.lastIndexOf("\\") + 1));
                        }
                        fi.write(file);
                        target = "img/"+fileName;
                        System.out.println("----------------------------yesyss-----------------------------");
                    } else {
                        String fieldName = fi.getFieldName();
                        String fieldValue = fi.getString();
                        if(fieldName.compareTo("regusername")==0){
                            username = fieldValue;
                        }else if(fieldName.compareTo("regname")==0){
                            fullname = fieldValue;
                        }else if(fieldName.compareTo("regpassword1")==0){
                            password = fieldValue;
                        }else if(fieldName.compareTo("regemail")==0){
                            email = fieldValue;
                        }else if(fieldName.compareTo("regdate")==0){
                            date = fieldValue;
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
                    "INSERT INTO user (`Username`, `Fullname`, `Password`, `DateOfBirth`, `Email`, `Avatar`) VALUES (?,?,?,?,?,?)");
            ps.setString(1, username);
            ps.setString(2, fullname);
            ps.setString(3, password);
            ps.setString(4, date);
            ps.setString(5, email);
            ps.setString(6, target);
            
            System.out.println("----------------------------ysesyss-----------------------------");
            int i = ps.executeUpdate();
            if (i != 0) {
                System.out.println("Data has been inserted to Database");
            } else {
                System.out.println("Insert database failed");
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            session.setAttribute("ava", target);
            response.sendRedirect("dashboard.jsp");
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
