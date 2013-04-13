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

@WebServlet(name = "listcat", urlPatterns = {"/listcat"})
public class listcat extends HttpServlet {

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
        } finally {
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

            Connection conn = null;
            PrintWriter out = response.getWriter();


            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510020", "root", "");
            HttpSession session = request.getSession(true);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT category.IDCategory,category.CategoryName,category.Creator FROM assignment,task,category"
                    + " WHERE assignment.Username='" + session.getAttribute("username")
                    + "' AND assignment.IDTask=task.IDTask AND task.IDCategory=category.IDCategory "
                    + "UNION DISTINCT SELECT category.IDCategory,category.CategoryName,category.Creator FROM"
                    + " authority,category WHERE authority.Username='" + session.getAttribute("username")
                    + " ' AND authority.IDCategory=category.IDCategory "
                    + "UNION DISTINCT SELECT IDCategory, CategoryName, category.Creator FROM category WHERE Creator ='"
                    + session.getAttribute("username") + "ORDER BY CategoryName");
            while (rs.next()) {
                out.print("<div class = \"kategori\" onclick = \"showTask(" + rs.getString(1) + ");\"><a>"
                        + rs.getString(2) + "</a></div>");
                if (rs.getString(3).compareToIgnoreCase(session.getAttribute("username").toString()) == 0) {
                    out.print("<img class=\"delcategory\" src=\"img/delete.png\" onclick=\"delCate(" + rs.getString(1) + ");\">");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(listcat.class.getName()).log(Level.SEVERE, null, ex);
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
