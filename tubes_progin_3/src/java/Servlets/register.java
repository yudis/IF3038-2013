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
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import java.security.MessageDigest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nicholas Rio
 */
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
        String username = "";
        String fullname = "";
        String date = "";
        String password = "";
        String email = "";
        String fileName = "";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        String contextRoot = getServletContext().getRealPath("/");
        String filePath = contextRoot + "..\\..\\web\\uploaded\\";
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
                    if ("regusername".equals(fieldName)) {
                        username = fieldValue;
                    } else if ("regname".equals(fieldName)) {
                        fullname = fieldValue;
                    } else if ("regdate".equals(fieldName)) {
                        date = fieldValue;
                    } else if ("regpassword1".equals(fieldName)) {
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
                    } else if ("regemail".equals(fieldName)) {
                        email = fieldValue;
                    }
                }
            }
            UserOp UO = new UserOp();
            UO.InsertUser(username,fullname,fileName,date,email,password);
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            response.sendRedirect("dashboard.jsp");
            //request.getRequestDispatcher("dashboard.jsp").forward(request, response);
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
