/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ConnectDB.ConnectDB;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.InputStream;


import java.util.List;
import java.sql.*;
import java.util.Iterator;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileUploadBase;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "InsertNew", urlPatterns = {"/InsertNew"})
public class InsertNew extends HttpServlet {

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
        try{    
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String namaleng = request.getParameter("namaleng");
            String tanggal = request.getParameter("tanggal");
            String email = request.getParameter("email");
            
//            if (ServletFileUpload.isMultipartContent(request)){
//                out.print(ServletFileUpload.isMultipartContent(request));
//            }
            //Processing file upload //////////////////////////////////////////
            
            Part avaPart = request.getPart("avatar");
            String fileName = getFileName(avaPart);
            String dir = "avatar/" + fileName;
            
            byte buf[] = new byte[1024 * 4];
        
            if (!fileName.isEmpty()) {
            FileOutputStream output = new FileOutputStream(getServletContext().getRealPath("/") + "avatar/" + fileName);
            try {
                InputStream input = avaPart.getInputStream();
                try {
                    while (true) {
                        int count = input.read(buf);
                        if (count == -1){
                            break;}
                        output.write(buf, 0, count);
                    }
                } finally {
                    input.close();
                }
            } finally {
                output.close();
            }}
            
            String avatar = dir;
//            
//            FileItemFactory  factory = new DiskFileItemFactory();
//            ServletFileUpload fileUpload = new ServletFileUpload(factory);
//            List<FileItem> items = fileUpload.parseRequest(request);
//            out.println("Number of item fields: " + "items.size()");
//
//            Iterator<FileItem> iterate = items.iterator();
//            if (!iterate.hasNext()){
//                out.println("No field of item found ...");
//                return;
//            } 
//            while (iterate.hasNext()){
//                FileItem fileItem = iterate.next();
//                boolean isFormField = fileItem.isFormField();
//                if (isFormField){
//                    out.print("<br />" + fileItem.getFieldName());
//                    if (fileItem.getFieldName().toString().equals("username")){
//                        username = fileItem.getString();
//                    } else if (fileItem.getFieldName().toString().equals("password")){
//                        password = fileItem.getString();
//                    } else if (fileItem.getFieldName().toString().equals("namaleng")){
//                        namaleng = fileItem.getString();
//                    } else if (fileItem.getFieldName().toString().equals("tanggal")){
//                        tanggal = fileItem.getString();
//                    } else if (fileItem.getFieldName().toString().equals("email")){
//                        email = fileItem.getString();
//                    } else if (fileItem.getFieldName().toString().equals("avatar")){
//                        avatar = fileItem.getString();
//                    } 
//                } else {
//                    String fileName = fileItem.getName().toString();
//                    int index = fileName.lastIndexOf('.') + 1 ;
//                    String extension = fileName.substring(index);
//                    avatar = username + "_" + extension.toLowerCase();
//
//                    byte [] arByte = fileItem.get();
//                    String dir = request.getRealPath("avatar");
//                    FileOutputStream fileOutStream = new FileOutputStream(dir+"/"+avatar);
//                    fileOutStream.write(arByte);
//                    fileOutStream.close();
//                }
//            }
            out.println("<br />" + username);
            out.println("<br />" + password);
            out.println("<br />" + namaleng);
            out.println("<br />" + tanggal);
            out.println("<br />" + email);
            out.println("<br />" + avatar);
            
            System.out.println(username);
            
            getConnection newConn = new getConnection();
            Connection conn = newConn.getConnectionn();
            //INSERT INTO `progin_405_13510100`.`user` (`iduser`, `username`, `password`, `name`, `dob`, `email`, `avatar`, `jmltask`) VALUES (NULL, 'lalolalo', 'lolalola', 'lala lolo', '2013-04-01', 'lolo@lala.com', 'avatar/lalolalo.jpg', '');
            String query = "INSERT INTO user VALUES ('" + username + "','" + password + "','" + namaleng + "','" + tanggal + "','" + email + "','" + avatar + "','" + 0 + "')" ;
            Statement state = conn.createStatement();
            state.executeQuery(query);

            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(30*24*60*60);

            response.sendRedirect("dashboard.jsp");
        } catch (Exception exc){
            out.print("Error Registration : ");
            out.print(exc.toString());
        } finally {
            out.close();
        }
    }
    private static String getFileName(Part part){
        System.out.println("check file name getter");
        for (String cd : part.getHeader("content-disposition").split(";")){
            if (cd.trim().startsWith("filename")){
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
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
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
