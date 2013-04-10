/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes3;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Raymond
 */
@WebServlet(name = "ChangeTaskStatus", urlPatterns = {"/ChangeTaskStatus"})
public class ChangeTaskStatus extends HttpServlet {

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
        String getid = request.getParameter("id");
        ResultSet rs;
        if ((getid != null) && (!getid.equals(""))) {
            response.setContentType("text/html;charset=UTF-8");

            PrintWriter out = response.getWriter();
            Tubes3Connection db = new Tubes3Connection();
            Connection connection = db.getConnection();
            Statement st;
            String queryGetStatus = "SELECT stat FROM tugas WHERE IDTask = " + getid + ";";
            String queryChange;
            try {

                rs = db.coba(connection, queryGetStatus);
                rs.first();
                String status = rs.getString(1);
                if (status.equals("0")) {
                    queryChange = "UPDATE `tugas` SET  `stat` =  1 WHERE  `IDTask` = " + getid + ";";
                } else {
                    queryChange = "UPDATE `tugas` SET  `stat` =  0 WHERE  `IDTask` = " + getid + ";";
                }
                st = connection.createStatement();
                st.executeUpdate(queryChange);
                out.print(status);
            } catch (SQLException ex) {
                System.out.println("Exception is ;" + ex);
            } finally {
                out.close();
            }
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
