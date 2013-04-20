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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sonny Theo Thumbur
 */
public class CommitStatus extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            boolean isAllowed = false;
            
            Connection con = null;
            String url = "jdbc:mysql://localhost:3306/progin_405_13510027?user=progin&password=progin";
            con = DriverManager.getConnection(url);
            
            //ambil parameter get
            String newStatus = request.getParameter("stat");
            String namaTask = request.getParameter("task");
            
            //hanya assignee dan creator task yang bisa edit task
            HttpSession session = request.getSession();
            String curUser = (String) session.getValue("username");
            
            Statement stmtTask = con.createStatement();
            String sqlTask = "SELECT creatorTaskName FROM task WHERE namaTask='" + namaTask + "'";
            ResultSet rsTask = stmtTask.executeQuery(sqlTask);
            String creator = "";
            while (rsTask.next()) {
                creator = rsTask.getString("creatorTaskName");
            }
            
            if (curUser.equals(creator)) isAllowed = true;
            
            Statement stmtAssignee = con.createStatement();
            String sqlAssignee = "SELECT asigneeName FROM tasktoasignee WHERE namaTask='" + namaTask + "'";
            ResultSet rsAssignee = stmtAssignee.executeQuery(sqlAssignee);
            
            while (rsAssignee.next()) {
                if (curUser.equals(rsAssignee.getString("asigneeName"))) {
                    isAllowed = true;
                    break;
                }
            }
            
            if (isAllowed) {
                //melakukan update database
                String driver = "com.mysql.jdbc.Driver";
                Class.forName(driver).newInstance();

                ResultSet rs = null;
                Statement stmt = null;
                stmt = con.createStatement();
                String sqlUpdateStatus = "UPDATE task SET status='" + newStatus + "' WHERE namaTask='" + namaTask + "'";
                stmt.executeUpdate(sqlUpdateStatus);

                out.println("1");
            } else {
                out.println("0");
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommitStatus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CommitStatus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CommitStatus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommitStatus.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommitStatus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CommitStatus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CommitStatus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommitStatus.class.getName()).log(Level.SEVERE, null, ex);
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
