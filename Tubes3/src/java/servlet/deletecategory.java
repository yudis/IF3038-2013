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
@WebServlet(name = "deletecategory", urlPatterns = {"/deletecategory"})
public class deletecategory extends HttpServlet {

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
        try {

            Connection conn = null;
            ResultSet rs = null;
            PrintWriter out = response.getWriter();


            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510020", "root", "");
            HttpSession session = request.getSession(true);
            Statement stmt = conn.createStatement();
            if (request.getParameter("IDCategory") != null) {
                //delete categorynya sendiri
                rs = stmt.executeQuery("DELETE FROM category WHERE IDCategory='" + request.getParameter("IDCategory") + "'");

                //delete authority
                rs = stmt.executeQuery("DELETE FROM authority WHERE IDCategory='" + request.getParameter("IDCategory") + "'");

                //delete semua yang terkait sama task
                rs = stmt.executeQuery("SELECT IDTask FROM task WHERE IDCategory='" + request.getParameter("IDCategory") + "'");


                Statement stmts = conn.createStatement();
                ResultSet rss = null;
                while (rs.next()) {
                    //delete tasknya sendiri
                    rss = stmt.executeQuery("DELETE FROM task WHERE IDTask='" + rs.getString(1) + "'");

                    //delete tasktag
                    rss = stmt.executeQuery("DELETE FROM tasktag WHERE IDTask='" + rs.getString(1) + "'");

                    //delete comment
                    rss = stmt.executeQuery("DELETE FROM comment WHERE IDTask='" + rs.getString(1) + "'");

                    //delete attachment
                    rss = stmt.executeQuery("DELETE FROM attachment WHERE IDTask='" + rs.getString(1) + "'");

                    //delete assignment
                    rss = stmt.executeQuery("DELETE FROM assignment WHERE IDTask='" + rs.getString(1) + "'");

                }
                
                 //delete task
                rs = stmt.executeQuery("DELETE FROM task WHERE IDCategory='" + request.getParameter("IDCategory") + "'");
            }


        } catch (SQLException ex) {
            Logger.getLogger(changeStatus.class.getName()).log(Level.SEVERE, null, ex);
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
