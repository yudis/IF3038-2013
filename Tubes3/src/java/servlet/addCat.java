/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

@WebServlet(name = "addCat", urlPatterns = {"/addCat"})
public class addCat extends HttpServlet {

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
        try {
            Connection conn = null;
            PrintWriter out = response.getWriter();
            String catname = request.getParameter("newcat");
            String catuser = request.getParameter("newcatuser");
            HttpSession session = request.getSession(true);
           
            String IDCat ="";
            DiskFileItemFactory factory = new DiskFileItemFactory(); 
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List fileItems = upload.parseRequest(request);
                Iterator i = fileItems.iterator();
                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (fi.isFormField()){
                        String fieldName = fi.getFieldName();
                        String fieldValue = fi.getString();
                        if(fieldName.compareToIgnoreCase("newcat")==0){
                            catname=fieldValue;
                        }
                        if(fieldName.compareToIgnoreCase("newcatuser")==0){
                            catuser=fieldValue;
                        }
                    }
                }
            } catch (FileUploadException ex) {
                Logger.getLogger(addCat.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510020", "root", "");
            Statement statement = conn.createStatement();

            int rs = statement.executeUpdate("INSERT INTO `category` (`IDCategory` ,`CategoryName` ,`Creator`) "+
                    "VALUES (NULL , '" + catname + "', '" + session.getAttribute("username")+ "')");
            
            ResultSet rss = statement.executeQuery("SELECT IDCategory from category WHERE CategoryName ='"+catname+"'");
            
            while (rss.next()){
                IDCat = rss.getString("IDCategory");
            }
            String[] authlist = catuser.split(";");
            for (int x=0;x<authlist.length;x++){
                rss = statement.executeQuery("SELECT * FROM authority WHERE Username='" +authlist[x]+ "' AND IDCategory='" +
                        IDCat +"'");
                int count = 0;
                while (rss.next()){
                    count++;
                }
                if (count==0){
                    rs = statement.executeUpdate("INSERT INTO `authority` (`IDAuthority`, `IDCategory`, `Username`)"+ 
                "VALUES (NULL, '"+IDCat+ "', '" +authlist[x]+ "')");
                }
            }
            response.sendRedirect("dashboard.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(addCat.class.getName()).log(Level.SEVERE, null, ex);
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
