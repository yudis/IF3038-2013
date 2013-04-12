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
@WebServlet(name = "maxid", urlPatterns = {"/maxid"})
public class maxid extends HttpServlet {

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
            out.println("<title>Servlet maxid</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet maxid at " + request.getContextPath () + "</h1>");
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
    
    public HttpSession session;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int IDTask;
        session=request.getSession();
        IDTask=(Integer)session.getAttribute("idtugas");
        String query="SELECT MAX(IDKomentar) AS idkomen FROM komentar WHERE IDTask="+IDTask+"";
        String query2="SELECT waktu from komentar where IDKomentar=(SELECT MAX(IDKomentar) FROM komentar WHERE IDTask="+IDTask+")";
        Tubes3Connection tu = new Tubes3Connection();
        Connection connection = tu.getConnection();
        ResultSet rs,rs2;
        response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
        int max;
            try {
                rs=tu.coba(connection,query);
                  if(rs.next())
                    {
                        max=rs.getInt("idkomen");
                        out.print(max+",");
                        
                        rs2=tu.coba(connection, query2);
                        if(rs2.next())
                        {
                            out.print(rs2.getString("waktu"));
                        }
                    }
            } catch (SQLException ex) {
                Logger.getLogger(maxid.class.getName()).log(Level.SEVERE, null, ex);
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
