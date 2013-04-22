/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sonny Theo Thumbur
 */
public class DeleteKategori extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            String kategoriToDelete = request.getParameter("kat");
            HttpSession session = request.getSession();
            String curUser = (String) session.getValue("username");
            
            //koneksi database
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            Connection con = null;
            
            String url = "jdbc:mysql://localhost:3306/progin_405_13510027?user=progin&password=progin";
            con = DriverManager.getConnection(url);
            
            //uji creator kategori
            Statement stmt = con.createStatement();
            String sql = "SELECT creatorKategoriName FROM kategori WHERE namaKategori='" + kategoriToDelete + "'";
            ResultSet rs = stmt.executeQuery(sql);
            
            boolean isValid = false;
            while (rs.next()) {
                if (rs.getString("creatorKategoriName").equals(curUser)) {
                    //hapus kategori
                    Statement stmtKatDel = con.createStatement();
                    String sqlKatDel = "DELETE FROM editKategori WHERE namaKategori='" + kategoriToDelete + "'";
                    stmtKatDel.executeUpdate(sqlKatDel);
                    
                    sqlKatDel = "DELETE FROM task WHERE namaKategori='" + kategoriToDelete + "'";
                    stmtKatDel.executeUpdate(sqlKatDel);
                    
                    sqlKatDel = "DELETE FROM kategori WHERE namaKategori='" + kategoriToDelete + "'";
                    stmtKatDel.executeUpdate(sqlKatDel);
                    
                    isValid = true;
                    break;
                }
            }
            
            if (isValid) out.println("Success. Penghapusan kategori berhasil dilakukan");
            else out.println("Warning. Anda tidak berhak untuk menghapus kategori ini!");
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeleteKategori.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DeleteKategori.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DeleteKategori.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteKategori.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeleteKategori.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DeleteKategori.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DeleteKategori.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteKategori.class.getName()).log(Level.SEVERE, null, ex);
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
