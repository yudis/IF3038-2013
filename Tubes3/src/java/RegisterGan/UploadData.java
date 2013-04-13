/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RegisterGan;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author user
 */
@WebServlet(name = "UploadData", urlPatterns = {"/UploadData"})
@MultipartConfig(location = "")
public class UploadData extends HttpServlet {
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
            out.println("kkk");
            Part file = request.getPart("avatar");
            if (file != null) {
                out.println("Sedang");
                String filename="";
                for (String cd : file.getHeader("content-disposition").split(";")) {
                    if (cd.trim().startsWith("filename")) {
                        filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                        filename = filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
                    }
                }

                out.println("OK");
                
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
            
            out.println("JPO");
            String mau = "INSERT INTO `pengguna`"
                    + "(`username`,`password`,`nama_lengkap`,`tanggal_lahir`,`email`,`avatar`)"
                    + " VALUES(' "+request.getParameter("username")+"','"
                    + request.getParameter("password")
                    + "','"+ request.getParameter("namaleng")
                    + "','"+request.getParameter("tanggal")
                    + "','"+ request.getParameter("email")
                    + "','"+avatar+"')";
            
            out.println(mau);
            int hasil = query.executeUpdate(mau);

            response.sendRedirect("dashboard.jsp");
        } catch (ClassNotFoundException ex) {
            out.println("Failed to create connection");
        } catch (SQLException ex) {
            out.println("Failed to execute SQL query");
            out.println(ex.getLocalizedMessage());
        } finally {            
            out.close();
        }    }

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
