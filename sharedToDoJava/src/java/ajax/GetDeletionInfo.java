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
import java.util.ArrayList;
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
public class GetDeletionInfo extends HttpServlet {

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
            throws ServletException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            String curUser = (String) session.getValue("username");
            String taskToDelete = request.getParameter("task");
            
            //koneksi database
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            Connection con = null;
            
            String url = "jdbc:mysql://localhost:3306/progin_405_13510027?user=progin&password=progin";
            con = DriverManager.getConnection(url);
            
            //uji creator
            Statement stmt = con.createStatement();
            String sql = "SELECT creatorTaskName FROM task WHERE namaTask='" + taskToDelete + "'";
            ResultSet rs = stmt.executeQuery(sql);
            
            boolean isDeleted = false;
            ArrayList<Integer> retVal = new ArrayList<Integer>();
            while (rs.next()) {
                if (rs.getString("creatorTaskName").equals(curUser)) {
                    //lakukan hard deletion
                    Statement stmtHardDel = con.createStatement();
                    
                    String sqlHardDel = "DELETE FROM tasktoasignee WHERE namaTask='" + taskToDelete + "'";
                    retVal.add(stmtHardDel.executeUpdate(sqlHardDel));
                    
                    sqlHardDel = "DELETE FROM komentar WHERE namaTask='" + taskToDelete + "'";
                    retVal.add(stmtHardDel.executeUpdate(sqlHardDel));
                    
                    sqlHardDel = "DELETE FROM tagging WHERE namaTask='" + taskToDelete + "'";
                    retVal.add(stmtHardDel.executeUpdate(sqlHardDel));
                    
                    sqlHardDel = "DELETE FROM attach WHERE namaTask='" + taskToDelete + "'";
                    retVal.add(stmtHardDel.executeUpdate(sqlHardDel));
                    
                    sqlHardDel = "DELETE FROM task WHERE namaTask='" + taskToDelete + "'";
                    retVal.add(stmtHardDel.executeUpdate(sqlHardDel));
                    
                    out.println("1");
                    isDeleted = true;
                    break;
                }
            }
            
            //uji assignee
            if (!isDeleted) {
                Statement stmtAssignee = con.createStatement();
                String sqlAssignee = "SELECT asigneeName FROM tasktoasignee WHERE namaTask='" + taskToDelete + "'";
                ResultSet rsAssignee = stmtAssignee.executeQuery(sqlAssignee);
                retVal.clear();
                
                while (rsAssignee.next()) {
                    if (rsAssignee.getString("asigneeName").equals(curUser)) {
                        //reference deletion as assignee
                        Statement stmtRefDel = con.createStatement();
                        String sqlRefDel = "DELETE FROM tasktoasignee WHERE namaTask='" + taskToDelete + "' AND asigneeName='" + curUser + "'";
                        retVal.add(stmtRefDel.executeUpdate(sqlRefDel));
                        
                        if (!retVal.contains(0)) {
                            out.println("1");
                        } else {
                            out.println("0");
                        }
                        
                        isDeleted = true;
                        break;
                    }
                }
            }
            
            if (!isDeleted) {
                out.println("2");
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
        } catch (InstantiationException ex) {
            Logger.getLogger(GetDeletionInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GetDeletionInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetDeletionInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetDeletionInfo.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (InstantiationException ex) {
            Logger.getLogger(GetDeletionInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GetDeletionInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetDeletionInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetDeletionInfo.class.getName()).log(Level.SEVERE, null, ex);
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
