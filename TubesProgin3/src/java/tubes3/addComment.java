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
@WebServlet(name = "addComment", urlPatterns = {"/addComment"})
public class addComment extends HttpServlet {

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
            out.println("<title>Servlet addComment</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addComment at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query="";
        String queryU="";
        //int IDTask;
        String IDTask =(String)request.getParameter("id");
        System.out.println("idtask="+IDTask);
         response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
        if(!(request.getParameter("comment").equals("")) && !(request.getParameter("usernamecur").equals(""))){
            query="INSERT INTO komentar(IDTask,username,isi) values("+IDTask+",'"+request.getParameter("usernamecur")+"','"+request.getParameter("comment")+"')";
            queryU="SELECT * from pengguna where username='"+request.getParameter("usernamecur")+"'";
        }
        Tubes3Connection tu = new Tubes3Connection();
        Connection connection = tu.getConnection();
        Statement pst;
        ResultSet rs;
        try {
            pst = connection.createStatement();
            pst.executeUpdate(query);
            out.print(IDTask);
            rs=tu.coba(connection,queryU);
            
        } catch (SQLException ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
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
