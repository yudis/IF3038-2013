/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Sonny Theo Thumbur
 */
public class UploadImage extends HttpServlet {

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
            
            out.println("mulai");
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
                System.out.println("File Not Uploaded");
            }else {
                out.println("mulai1");
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    out.println("mulai5");
                    out.println(upload.parseRequest(request).size());
                    out.println("mulai7");
                    items = upload.parseRequest(request);
                    out.println(items.size());
                    out.println("mulai6");
                    out.println(request);
                }catch (FileUploadException e) {
                    e.printStackTrace();
                }
                out.println("mulai3");
                Iterator itr = items.iterator();
                while(itr.hasNext()){
                    FileItem item = (FileItem) itr.next();
                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        String value = item.getString();
                    } else {
                        try {
                            out.println("mulai2");
                            String itemName = item.getName();
                            Random generator = new Random();
                            int r = Math.abs(generator.nextInt());
                            
                            String reg = "[.*]";
                            String replacingtext = "";
                            Pattern pattern = Pattern.compile(reg);
                            Matcher matcher = pattern.matcher(itemName);
                            StringBuffer buffer = new StringBuffer();
                            
                            while (matcher.find()){
                                matcher.appendReplacement(buffer, replacingtext);
                            }
                            
                            int IndexOf = itemName.indexOf(".");
                            String domainName = itemName.substring(IndexOf);
                            
                            String finalimage = buffer.toString() + "_" + r + domainName;
                            
                            out.println("FFFFFFINAL = " + finalimage);
                            File savedFile = new File("D:/Informatika/Semester 6/Pemrograman Internet/Tubes III/SharedToDoList/web/server" + finalimage);
                            try {
                                item.write(savedFile);
                            } catch (Exception ex) {
                                Logger.getLogger(UploadImage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            out.println("<html>");
                            out.println("<body>");
                            out.println("<table><tr><td>");
                            out.println("<img src=images/"+finalimage+">");
                            out.println("</td></tr></table>");
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
            
            
            
            /*out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UploadImage</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploadImage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
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
