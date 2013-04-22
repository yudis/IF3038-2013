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
 * @author Yulianti Oenang
 */
@WebServlet(name = "editbutton", urlPatterns = {"/editbutton"})
public class editbutton extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editbutton</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editbutton at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */ System.out.println("yuyuyuy");
            
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public HttpSession session;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String IDTask="";
        session = request.getSession();
        IDTask = request.getParameter("id");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        Tubes3Connection tu = new Tubes3Connection();
        Connection connection = tu.getConnection();
        ResultSet rs;
        String queryU = "SELECT username from penugasan where IDTask=" + IDTask;
        try {
        rs = tu.coba(connection, queryU);
        while (rs.next()) {
        if (rs.getString("username").equals(session.getAttribute("bananauser"))) {
        out.print("true");
        break;
        }
        }
        } catch (SQLException ex) {
        Logger.getLogger(edittask.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
