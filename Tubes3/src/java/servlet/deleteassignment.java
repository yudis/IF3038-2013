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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Martha Monica
 */
@WebServlet(name = "deleteassignment", urlPatterns = {"/deleteassignment"})
public class deleteassignment extends HttpServlet {

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
            ResultSet rs = null;
            PrintWriter out = response.getWriter();
            String output = "";

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510020", "root", "");
            HttpSession session = request.getSession(true);
            Statement stmt = conn.createStatement();
            int res = stmt.executeUpdate("DELETE FROM assignment WHERE IDAssignment='" + request.getParameter("IDAssignment") + "'");
            rs = stmt.executeQuery("SELECT * FROM assignment WHERE IDTask=" + request.getParameter("IDTask"));
            while (rs.next()) {
                if (rs.getString("Username").compareToIgnoreCase(session.getAttribute("username").toString()) == 0) {
                    output = output + "<a href=\"profile.php?user='" + rs.getString("Username") + "'\" class=\"asignee\">'"
                            + rs.getString("Username") + " '</a>  <img src=\"img/salah.png\" alt=\"\" onclick=\"deleteassignee(' " + rs.getString("IDAssignement") + " ',' " + rs.getString("IDTask") + "')\" /><br/>";
                }
                output= output + "<br/>";
                out.write(output);
            }
        } catch (SQLException ex) {
            Logger.getLogger(deleteassignment.class.getName()).log(Level.SEVERE, null, ex);
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
