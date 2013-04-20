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
public class changeKategori extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
//            ambil nama kategori
            String kategori = request.getParameter("k");
//            out.println(kategori);
            
            String host = "jdbc:mysql://localhost:3306/progin_405_13510027";
            String uName = "progin";
            String uPass = "progin";

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Exception : Kelas tidak ditemukan");
            }
            Connection con = DriverManager.getConnection(host, uName, uPass);

            Statement stmt = (Statement) con.createStatement();
            String sqlKategori = "SELECT * FROM task WHERE namaKategori='" + kategori + "'";
            ResultSet rsKategori = stmt.executeQuery(sqlKategori);
            
            while (rsKategori.next()) {
                String namaTask = rsKategori.getString("namaTask");
                String statusTask = rsKategori.getString("status");
                out.println("<div id=" + rsKategori.getString("namaTask") + ">");
                    out.println("<div id='taskTitle1' class='taskElmtLeft' onclick=\"toHalamanRincianTugas( '" + rsKategori.getString("namaTask") + "')\">");
                        out.println("<p></strong>" + rsKategori.getString("namaTask") + "</strong></p>");
                    out.println("</div>");
                    out.println("<div class='taskElmtRight'>");
                    out.println("</div>");
                    out.println("<div class='taskElmtLeft'>");
                        out.println("<p>Deadline :</p>");
                    out.println("</div>");
                    out.println("<div class='taskElmtRight'>");
                        out.println("<p>" + rsKategori.getString("deadline") + "</p>");
                    out.print("</div>");
                    out.println("<div class='taskElmtLeft'>");
                        out.println("<p>Tag :</p>");
                    out.println("</div>");
                    out.println("<div class='taskElmtRight'>");

                        //mengambil semua tag untuk task tertentu
                        String sqlTag = "SELECT tag FROM tagging WHERE namaTask='" + namaTask + "'";
                        Statement stmtTag = con.createStatement();
                        ResultSet resultTag = stmtTag.executeQuery(sqlTag);
                        String tag = "";
                        
                        while (resultTag.next()){
                            tag += resultTag.getString("tag") + " | ";
                        }
//                        $refined = substr($tagString,0,strlen($tagString)-3);
                        out.println("<p>" + tag + "</p>");
                        
                    out.println("</div>");
                    out.println("<div class='taskElmtLeft'>");
                        out.println("<p>Status :</p>");
                    out.println("</div>");
                    String idStatus = namaTask + "status";
                    out.println("<div class='taskElmtRight' id='" + idStatus + "'>");
                      out.println("<p>" + statusTask + "</p>");
                    out.println("</div>");
                    out.println("<div class='taskElmtLeft'>");
                    out.println("</div>");
                    out.println("<div class='taskElmtRight'>");
                        out.println("<button class='ubahStatusTask' onclick=\"changeTaskStatus('" +  namaTask + "','" + idStatus + "')\">Ubah Status</button>");
//                        out.println("<button class='ubahStatusTask' onclick=\"changeTaskStatus('" +  namaTask + "','taskElmtLeft')\">Ubah Status</button>");
                    out.println("</div>");
                    out.println("<div class='taskElmtLeft'>");
                    out.println("</div>");
                    out.println("<div class='taskElmtRight'>");
                        out.println("<button class='ubahStatusTask' onclick=\"deleteTask('" + namaTask + "')\";>Hapus Tugas</button>");
                    out.println("</div>");
                out.println("</div>");
            }
            
            out.println("<button class='addTask' onclick=\"toHalamanPembuatanTugas('" + kategori + "');\">tambah tugas</button>");
            out.println("<button class='addTask' onclick=\"deleteKategori('" + kategori +  "');\">hapus kategori</button>");
            
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
        } catch (SQLException ex) {
            Logger.getLogger(changeKategori.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(changeKategori.class.getName()).log(Level.SEVERE, null, ex);
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
