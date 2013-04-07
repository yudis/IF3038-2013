/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Raymond
 */
@WebServlet(name = "AddFile", urlPatterns = {"/AddFile"})
@MultipartConfig
public class AddFile extends HttpServlet {

    private Tubes3Connection db;
    private Connection connection;

    public AddFile() {
        super();
        
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
            throws ServletException, IOException  {

        for (Part part : request.getParts()) {
            //System.out.println("PART:" + part.getHeaderNames());
            InputStream is = request.getPart(part.getName()).getInputStream();
            int i = is.available();
            byte[] b = new byte[i];
            is.read(b);
            String fileName = getFileName(part);
            System.out.println("FILE:" + fileName);
            if (fileName != null && !fileName.equals("")) {
                System.out.println("MASUk");
                FileOutputStream os = new FileOutputStream(getServletContext().getRealPath("/") + "uploadedFile/" + fileName);
                //System.out.println(getServletContext().getRealPath("/") + fileName);
                os.write(b);
                os.close();
            }
            is.close();
        }
        String username = request.getParameter("user");
        String nama = request.getParameter("tugas");
        String asignee = request.getParameter("asignee");
        String tag = request.getParameter("tag");
        String deadline = request.getParameter("deadline");
        String kategori = request.getParameter("kategori");
        // System.out.println(username + nama+asignee+tag+deadline+kategori);
        db = new Tubes3Connection();
        connection = db.getConnection();
        String insertTugas = "INSERT INTO tugas (`IDKategori`, `deadline`, `name`, `tag`, `username`) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement insTugas;
        int rs;
        try {
            insTugas = connection.prepareStatement(insertTugas);
            insTugas.setString(1, kategori);
            insTugas.setString(2, deadline);
            insTugas.setString(3, nama);
            insTugas.setString(4, tag);
            insTugas.setString(5, username);
            rs = insTugas.executeUpdate( );
           String maxIDTask = "SELECT max(IDTask) FROM tugas";
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        
        }
    }

    private String getFileName(Part part) {
        //System.out.println("partgetContentType:" + part.getContentType());
        //System.out.println("partgetHeaderNames:" + part.getHeaderNames());


        // String partHeader = part.getHeader("content-disposition");
        //if (part.getHeader("content-type") != null) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            //System.out.println("CD:" + cd);
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        //}
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
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
