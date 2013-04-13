/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Adriel
 */
@WebServlet(urlPatterns = {"/getactivity"})
public class getactivity extends HttpServlet {
    private Connection connection;
    private String query = null;
    private Statement statement;
    
    public void init(ServletConfig config) throws ServletException {
 
        String url = "jdbc:mysql://localhost:3306/progin_405_13510029";
        String username = "progin";
        String password = "progin";
        try {
         Class.forName("com.mysql.jdbc.Driver").newInstance();

         connection = DriverManager.getConnection(url, username , password);
        }
        catch (Exception e) {

         e.printStackTrace();
        }

       }
    
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
            String user = request.getParameter("user");
            
            try {
                query = "SELECT task.task_id, task.task_name, task.status, task.deadline, task.task_category FROM task, task_incharge WHERE task.task_id = task_incharge.task_id AND task_incharge.people_incharge_task = '" + user + "' ORDER BY task.task_category ASC";
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                String kategori = "";
                
                while (rs.next())
                {
                        if (kategori.equals(rs.getString("task_category")))
                        {
                                kategori = rs.getString("task_category");
                                String query2 = "SELECT category_name FROM category WHERE category_id = '" + kategori + "'";
                                statement = connection.createStatement();
                                ResultSet rs2 = statement.executeQuery(query2);
                                rs2.next();
                                String namakategori = rs2.getString(1);
                                out.print("<h2>" + namakategori + "</h2>");
                        }
                        out.print("<div class='tugas'>");
                        out.print("<div><a href='tugas.jsp?id=" + rs.getString("task_id") + "'>" + rs.getString("task_name") + "</a></div>");
                        out.print("<div>Submission: <strong>" + rs.getString("deadline") + "</strong></div>");
                        out.print("<div>Status: ");
                        if (rs.getString("status").equals("0"))
                        {
                                out.print("Belum Selesai</div>");
                        }
                        else
                        {
                                out.print("Selesai</div>");
                        }
                        out.print("</div>");
                }
                
                rs.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(getactivity.class.getName()).log(Level.SEVERE, null, ex);
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
