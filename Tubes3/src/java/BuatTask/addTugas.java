/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BuatTask;

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
@WebServlet(name = "addTugas", urlPatterns = {"/addTugas"})
public class addTugas extends HttpServlet {
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
            out.println(request.getParameterMap());
            String mau = "INSERT INTO tugas"
                    + "(status,id_kategori,deadline,tag,username,nama_tugas)"
                    + " VALUES(0,"+request.getParameter("id_kategori")+",'"
                    + request.getParameter("date")+" "+request.getParameter("hour")+":"+request.getParameter("minute")
                    + "','"+ request.getParameter("tag")+"','"+request.getParameter("username")
                    + "','"+ request.getParameter("task_name")+"')";
            
            out.println(mau);
            int hasil = query.executeUpdate(mau);
            
            int id_tugas;
            ResultSet result = query.executeQuery("SELECT MAX(id_tugas) from tugas");
            result.first();
            id_tugas = result.getInt(1);
            status = 0;
            
            String[] orang = request.getParameter("Assignee").split("/");
            for (int i=0;i<orang.length;++i) {
                hasil = query.executeUpdate("INSERT INTO mengerjakan('username',id_tugas'','status_tugas')"
                        + " VALUES ('"+orang[i]+"',"+id_tugas+","+status+")");
            }

            response.sendRedirect("rinciantugas.jsp?id_tugas="+id_tugas);
        } catch (ClassNotFoundException ex) {
            out.println("Failed to create connection");
        } catch (SQLException ex) {
            out.println("Failed to execute SQL query");
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
