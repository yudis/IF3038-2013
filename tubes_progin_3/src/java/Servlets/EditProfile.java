//Timo, 11 April 2013
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DBOperation.UserOp;
import Model.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
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
 * @author Compaq
 */
public class EditProfile extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
        } finally {            
            out.close();
        }
    }

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
        processRequest(request, response);
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
        File file;
        String fullname = "";
        String date = "";
        String password = "";
        String fileName = "";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        String contextRoot = getServletContext().getRealPath("/");
        String filePath = contextRoot + "..\\..\\web\\uploaded\\";
        System.out.println(filePath);
        factory.setRepository(new File(contextRoot + "..\\..\\web\\temp"));
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            HttpSession session = request.getSession(true);
            String username = session.getAttribute("username").toString();
            UserOp UO = new UserOp();
            User U = UO.SelectUserInfoByUsername((String)session.getAttribute("username"));
            
            List fileItems = upload.parseRequest(request);
            Iterator i = fileItems.iterator();
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    String fieldName = fi.getFieldName();
                    fileName = fi.getName();
                    System.out.println("File name : " + fileName);
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath
                                + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath
                                + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fi.write(file);
                } else {
                    String fieldName = fi.getFieldName();
                    String fieldValue = fi.getString();
                    if ("bioname".equals(fieldName)) {
                        fullname = fieldValue;
                    } else if ("biodate".equals(fieldName)) {
                        date = fieldValue;
                    } else if ("biopassword1".equals(fieldName)) {
                        password = fieldValue;
                        MessageDigest md = MessageDigest.getInstance("MD5");
                        md.update(password.getBytes());
                        byte[] digest = md.digest();
                        StringBuffer hexString = new StringBuffer();
                        for (int j = 0; j < digest.length; j++) {
                            password = Integer.toHexString(0xFF & digest[j]);
                            if (password.length() < 2) {
                                password = "0" + password;
                            }
                            hexString.append(password);
                        }
                        password = hexString.toString();
                    }
                }
            }
            UO.UpdateUser(username,fullname,fileName,date,password);
            if (    U.getFullname().equals(fullname) && 
                    U.getAvatar().equals("uploaded/"+fileName) &&
                    U.getDob().equals(date) &&
                    U.getPassword().equals(password)
                )
            {
                session.setAttribute("message","No changes have been made");
            } else {
                session.setAttribute("message","Successfully Done");
            }
            response.sendRedirect("profile.jsp");
            //request.getRequestDispatcher("profile.jsp").forward(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
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
