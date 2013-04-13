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
public class UserDataUpdate extends HttpServlet {

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
            //ambil session
            HttpSession session = request.getSession(true);
            String curUser = (String) session.getValue("username");
            
            String newFullName = request.getParameter("newFullName");
            String newBirthdate = request.getParameter("newBirthdate");
            String newPassword = request.getParameter("newPassword");
            
            //koneksi database
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            
            Connection con = null;
            ResultSet rs = null;
            Statement stmt = null;
            
            String url = "jdbc:mysql://localhost:3306/progin_405_13510027?user=progin&password=progin";
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String sqlCheck = "SELECT * FROM user WHERE username='" + curUser + "'";
            rs = stmt.executeQuery(sqlCheck);
            
            while (rs.next()){
                //mengecek adanya pengisian kosong
                if (newFullName.equals("-111")) {
                    newFullName = rs.getString("username");
                }
                if (newBirthdate.equals("-222")){
                    newBirthdate = rs.getString("tanggalLahir");
                }
                if (newPassword.equals("-333")) {
                    newPassword = rs.getString("password");
                }
            }
            
            //update database
            String sqlUpdate = "UPDATE user SET fullname='" + newFullName + "', tanggalLahir='" + newBirthdate + "', password='" + newPassword + "' WHERE username='" + curUser + "'";
            stmt.executeUpdate(sqlUpdate);
            
            //mengembalikan respon
            out.println(newFullName + "*" + newBirthdate);
//            out.println("hina");
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
            Logger.getLogger(UserDataUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(UserDataUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UserDataUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDataUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserDataUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(UserDataUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UserDataUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDataUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
