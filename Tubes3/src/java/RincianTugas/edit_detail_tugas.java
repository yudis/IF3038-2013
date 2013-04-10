/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RincianTugas;

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
@WebServlet(name = "edit_detail_tugas", urlPatterns = {"/edit_detail_tugas"})
public class edit_detail_tugas extends HttpServlet {
    private Connection conn;
    private Statement query;
    int status;
    
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
            ResultSet result = query.executeQuery("SELECT * from tugas WHERE id_tugas="+request.getParameter("id_tugas"));
            if (result.next()) {
                result.close();
                
                if (request.getParameter("status") == null) {
                    status = 1;
                } else {
                    status = 0;
                }
                
                int hasil = query.executeUpdate("UPDATE tugas SET status="+status+" WHERE id_tugas="+request.getParameter("id_tugas"));
                
                hasil = query.executeUpdate("DELETE FROM mengerjakan WHERE id_tugas="+request.getParameter("id_tugas"));
                
                String[] orang = request.getParameter("Assignee").split("/");
                for (int i=0;i<orang.length;++i) {
                    hasil = query.executeUpdate("INSERT INTO mengerjakan('username',id_tugas'','status_tugas')"
                            + " VALUES ('"+orang[i]+"',"+request.getParameter("id_tugas")+","+status+")");
                }
            } else {
                out.println("Failed to update description");
            }
        } catch (ClassNotFoundException ex) {
            out.println("Failed to create connection");
        } catch (SQLException ex) {
            out.println("Failed to execute SQL query");
        } finally {            
            out.close();
            response.sendRedirect("rinciantugas.jsp?id_tugas="+request.getParameter("id_tugas"));
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
