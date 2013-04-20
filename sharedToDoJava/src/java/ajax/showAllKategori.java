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

/**
 *
 * @author Sonny Theo Thumbur
 */
public class showAllKategori extends HttpServlet {

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
            //melakukan update database
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            
            Connection con = null;
            ResultSet rs = null;
            Statement stmt = null;
            
            String url = "jdbc:mysql://localhost:3306/progin_405_13510027?user=progin&password=progin";
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String sql = "SELECT * FROM task";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String namaTask = rs.getString("namaTask");
                String deadline = rs.getString("deadline");
                String status = rs.getString("status");
                String idStatus = namaTask + "status";
                
                out.println("<div id=" + namaTask + "space>");
                    out.println("<div id='taskTitle1' class='taskElmtLeft' onclick=\"toHalamanRincianTugas('$nama')\";>");
                        out.println("<p></strong>" + namaTask + "</strong></p>");
                    out.println("</div>");
                    out.println("<div class='taskElmtRight'>");
                    out.println("</div>");
                    out.println("<div class='taskElmtLeft'>");
                        out.println("<p>Deadline :</p>");
                    out.println("</div>");
                    out.println("<div class='taskElmtRight'>");
                        out.println("<p>" + deadline + "</p>");
                    out.println("</div>");
                    out.println("<div class='taskElmtLeft'>");
                        out.println("<p> Tag : </p>");
                    out.println("</div>");
                    out.println("<div class='taskElmtRight'>");
                    
                    //mengambil tag untuk task tertentu
                    String sqlTag = "SELECT tag FROM tagging WHERE namaTask='" + namaTask + "'";
                    Statement stmt1 = con.createStatement();
                    ResultSet rsTag = stmt1.executeQuery(sqlTag);
                    
                    String tagString = "";
                    while (rsTag.next()) {
                        tagString += rsTag.getString("tag") + " | ";
                    }
                        out.println("<p>" + tagString + "</p>");
                    out.println("</div>");
                    out.println("<div class='taskElmtLeft'>");
                        out.println("<p>Status : </p>");
                    out.println("</div>");
                    out.println("<div class='taskElmtRight' id='" + idStatus + "'>");
                        out.println("<p>" + status + "</p>");
                    out.println("</div>");
                    out.println("<div class='taskElmtLeft'>");
                    out.println("</div>");
                    out.println("<div class='taskElmtRight'>");
                        out.println("<button class='ubahStatusTask' onclick=\"changeTaskStatus('" + namaTask + "','" + idStatus + "')\";>Ubah Status</button>");
                    out.println("</div>");
                    out.println("<div class='taskElmtLeft'>");
                    out.println("</div>");
                    out.println("<div class='taskElmtRight'>");
                        out.println("<button class='ubahStatusTask' onclick=\"deleteTask('" + namaTask + "')\";>Hapus Tugas</button>");
                    out.println("</div>");
                out.println("</div>");
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
            Logger.getLogger(showAllKategori.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(showAllKategori.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(showAllKategori.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(showAllKategori.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(showAllKategori.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(showAllKategori.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(showAllKategori.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(showAllKategori.class.getName()).log(Level.SEVERE, null, ex);
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
