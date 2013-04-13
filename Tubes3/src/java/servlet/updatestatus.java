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
@WebServlet(name = "updatestatus", urlPatterns = {"/updatestatus"})
public class updatestatus extends HttpServlet {

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
            Connection conn = null;
            ResultSet rs = null;
            PrintWriter out = response.getWriter();


            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510020", "root", "");
            HttpSession session = request.getSession(true);
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM task WHERE IDTask="+request.getParameter("IDTask"));
            
            boolean check;
            int i = stmt.executeUpdate("UPDATE  `task` SET  `Status` ='done' WHERE IDTask=" + request.getParameter("IDTask"));
            
            if (rs.getString(3).compareToIgnoreCase("done")==0){
                if (request.getParameter("status").compareToIgnoreCase("true")==0){
                    check=true;
                }
                else
                {
                    check=false;
                    i = stmt.executeUpdate("UPDATE  `task` SET  `Status` ='undone' WHERE IDTask="+request.getParameter("IDTask"));
                }
            }
            else{
                if (request.getParameter("status").compareToIgnoreCase("true")==0){
                    check=true;
                    i = stmt.executeUpdate("UPDATE  `task` SET  `Status` ='done' WHERE IDTask="+request.getParameter("IDTask"));
                }
                else
                {
                    check=false;
                    
                }
            }
            out.print("uyeee;");
            if (check){
                out.print(" DONE <input type=\"checkbox\" id=\"checkboxstatus\" checked onclick=\"changestatus(' "+request.getParameter("IDTask")+" ')\" >");
            }
            else{
                out.print(" DONE <input type=\"checkbox\" id=\"checkboxstatus\" onclick=\"changestatus(' "+request.getParameter("IDTask")+" ')\" >"); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(deletetask.class.getName()).log(Level.SEVERE, null, ex);
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
