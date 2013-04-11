/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DashBoard;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christianto
 */
@WebServlet(name = "getTask", urlPatterns = {"/getTask"})
public class getTask extends HttpServlet {
    private Connection conn;
    private Statement query;
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
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510003", "root", "");
            query = conn.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM tugas");
            
            while (result.next()) {
                out.println("<a href=\"rinciantugas.jsp?id_tugas="+result.getString("id_tugas")+"\">");
                out.println("<div id=\"listtask\">");
                out.println("<div>"+result.getString("nama_tugas")+"</div>");
                out.println("<div>"+result.getString("deadline")+"</div>");
                out.println("<div>tag: "+result.getString("tag")+"</div>");
                if (result.getInt("status") == 1) {
                    out.println("<input type=\"checkbox\" name=\"status\" checked "
                            + "onclick=\"ubahstatus("+result.getString("id_tugas")+");\">");
                } else {
                    out.println("<input type=\"checkbox\" name=\"status\" "
                            + "onclick=\"ubahstatus("+result.getString("id_tugas")+");\">");
                }
                out.println("</div></a>");
            }

        } catch (SQLException ex) {
            out.println("Failure to execute SQL query");
        } catch(ClassNotFoundException ex){
            out.println("Failure to create connection");
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
