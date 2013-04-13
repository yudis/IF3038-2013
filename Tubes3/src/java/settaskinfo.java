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
@WebServlet(urlPatterns = {"/settaskinfo"})
public class settaskinfo extends HttpServlet {
    private Connection connection;
    private String query = null;
    private Statement statement;
    
    @Override
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
            String id = request.getParameter("id");
            String status = request.getParameter("status");
            String deadline = request.getParameter("deadline");
            String assignee = request.getParameter("assignee");
            String tags = request.getParameter("tags");
            
            try {
                query = "UPDATE task SET status=" + status + " WHERE task_id = '" + id + "'";
                statement = connection.createStatement();
                statement.executeUpdate(query);
                
                query = "UPDATE task SET deadline='" + deadline + "' WHERE task_id = '" + id + "'";
                statement = connection.createStatement();
                statement.executeUpdate(query);
                
                String[] arrAss = assignee.split(",");
                for (int i = 0; i < arrAss.length; i++)
                {
                    query = "SELECT COUNT people_incharge_task FROM task_incharge WHERE task_id = '" + id + "' AND people_incharge_task = '" + arrAss[i] + "'";
                    statement = connection.createStatement();
                    ResultSet rs1 = statement.executeQuery(query);
                    if (rs1.getInt(1) == 0);
                    {
                        query = "INSERT INTO task_incharge (task_id, people_incharge_task) VALUES ('" + id + "', '" + arrAss[i] + "')";
			statement = connection.createStatement();
                        statement.executeUpdate(query);
                    }
                }
                
                String[] arrTag = tags.split(",");
                query = "SELECT * FROM tag";
                statement = connection.createStatement();
                ResultSet rs2 = statement.executeQuery(query);

                int maxtagID = 0;
                while (rs2.next()) {
                    if (Integer.parseInt(rs2.getString("tag_id")) > maxtagID) {
                        maxtagID = Integer.parseInt(rs2.getString("tag_id"));
                    }
                }
                maxtagID++;
                int i = 0;
                while (i < arrTag.length) {
                    query = "INSERT into tag VALUES('"+maxtagID+"','"+arrTag[i]+"')";
                    statement = connection.createStatement();
                    statement.executeUpdate(query);

                    query = "INSERT into tasktag VALUES('"+id+"','"+maxtagID+"')";
                    statement = connection.createStatement();
                    statement.executeUpdate(query);
                    i++;
                    maxtagID++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(settaskinfo.class.getName()).log(Level.SEVERE, null, ex);
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
