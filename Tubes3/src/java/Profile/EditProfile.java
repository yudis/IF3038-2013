/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Profile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Sigit Aji Nugroho
 */
@WebServlet(name = "EditProfile", urlPatterns = {"/EditProfile"})
@MultipartConfig(location="")
public class EditProfile extends HttpServlet {
    private Connection conn;
    private Statement query;
    
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
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510003", "root", "");
            query = conn.createStatement();
            
            String avatar="";
            Part file = request.getPart("na");
            if (file != null) {
                String filename="";
                for (String cd : file.getHeader("content-disposition").split(";")) {
                    if (cd.trim().startsWith("filename")) {
                        filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                        filename = filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
                    }
                }

                if (!filename.equals("")) {
                    InputStream masuk = file.getInputStream();
                    File f = new File(getServletContext().getRealPath(""),"/pict/"+filename);
                    FileOutputStream tulis = new FileOutputStream(f);

                    int sem;
                    while ((sem = masuk.read()) != -1) {
                        tulis.write(sem);
                    }
                    tulis.close();
                    avatar = "pict/"+filename;
                }
            }
            
            //out.println(id_tugas);
            String mau = "UPDATE pengguna SET"
                    + "nama_lengkap=' " + request.getParameter("fn") + "',"
                    + "password='" + request.getParameter("cp") + "',"
                    + "tanggal_lahir='"+request.getParameter("date")+"',"
                    + "avatar='"+avatar+"')";
            
            //out.println(mau);
            int hasil = query.executeUpdate(mau);
            //out.print("HERE");
            response.sendRedirect("profile.jsp");
        } catch (ClassNotFoundException ex) {
            out.println("Failed to create connection");
        } catch (SQLException ex) {
            out.println("Failed to execute SQL query");
            out.println(ex.getLocalizedMessage());
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
