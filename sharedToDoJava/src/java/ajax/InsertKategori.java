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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sonny Theo Thumbur
 */
public class InsertKategori extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            String namaKategori = request.getParameter("kat");
            String userList = request.getParameter("userList");
            String[] user = userList.split(",");
            
            //koneksi database
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            
            Connection con = null;
            
            String url = "jdbc:mysql://localhost:3306/progin_405_13510027?user=progin&password=progin";
            con = DriverManager.getConnection(url);
            
            //uji validitas nama kategori
            boolean isValidKategoriName = true;
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM kategori WHERE namaKategori='" + namaKategori + "'";
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                if (namaKategori.equals(rs.getString("namaKategori"))) {
                    out.println("Warning. Nama kategori sudah ada.");
                    isValidKategoriName = false;
                    break;
                }
            }
            
            //uji validitas nama user yang boleh edit
            Statement stmtUser = con.createStatement();
            ResultSet rsUser = null;
            String sqlUser = "SELECT username FROM user";
            rsUser = stmtUser.executeQuery(sqlUser);
            
            boolean isValidUser = true;
            ArrayList<String> userCheck = new ArrayList<String>();
            while (rsUser.next()) {
                userCheck.add(rsUser.getString("username"));
            }
            for (int i=0; i<user.length; i++) {
                if (!userCheck.contains(user[i])) {
                    isValidUser = false;
                    out.println("Warning. Nama user tidak terdaftar dalam sistem");
                    break;
                }
            }
            
            if (isValidKategoriName && isValidUser) {
                //insert ke tabel kategori
                HttpSession session = request.getSession();
                String curUser = (String) session.getValue("username");
                String sqlInsertKategori = "INSERT INTO kategori VALUES ('" + namaKategori + "','" + curUser + "')";
                Statement stmtInsertKategori = con.createStatement();
                int checkKategori = stmtInsertKategori.executeUpdate(sqlInsertKategori);
                
                //insert ke tabel editKategori
                int checkEditKategori = 0;
                for (int j=0; j<user.length; j++) {
                    String sqlEditKategori = "INSERT INTO editKategori VALUES ('" + user[j] + "','" + namaKategori + "')";
                    Statement stmtEditKategori = con.createStatement();
                    checkEditKategori = stmtEditKategori.executeUpdate(sqlEditKategori);
                }
                if ((checkKategori!=0) && (checkEditKategori!=0)) out.println("Success. Insert kategori telah berhasil dilakukan.");
            }
            
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
            Logger.getLogger(InsertKategori.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(InsertKategori.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(InsertKategori.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(InsertKategori.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InsertKategori.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(InsertKategori.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(InsertKategori.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(InsertKategori.class.getName()).log(Level.SEVERE, null, ex);
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
