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
@WebServlet(name = "edittask", urlPatterns = {"/edittask"})
public class edittask extends HttpServlet {
public edittask(){}
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
       /* PrintWriter out = response.getWriter();
        try {
            TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet edittask</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet edittask at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             
        } finally {            
            out.close();
        }*/
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
        //processRequest(request, response);
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
       
        boolean flag;
        int IDTask;
        session=request.getSession();
        String sID = request.getParameter("id");
        IDTask= Integer.parseInt(sID);
        response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
        String query="";
        String query2="";
        Tubes3Connection tu = new Tubes3Connection();
        Connection connection = tu.getConnection();
        Statement pst;
        if(!(request.getParameter("deadline").equals("")) && !(request.getParameter("tag").equals("")) && !(request.getParameter("assignee").equals("")))
        {
            query="UPDATE tugas SET deadline='"+request.getParameter("deadline")+"', tag='"+request.getParameter("tag")+"' WHERE IDTask="+IDTask;
            query2="INSERT INTO penugasan(IDTask,username) values("+IDTask+",'"+request.getParameter("assignee")+"')";
             try {
                    pst = connection.createStatement();
                    pst.execute(query);
                    pst.execute(query2);
                    out.print(request.getParameter("deadline")+","+request.getParameter("tag")+","+request.getParameter("assignee"));
                } catch (SQLException ex) {
                    Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
                }
          
        }
        else if(!(request.getParameter("deadline").equals("")) && !(request.getParameter("tag").equals("")))
	{
		query="UPDATE tugas SET deadline='"+request.getParameter("deadline")+"', tag='"+request.getParameter("tag")+"' WHERE IDTask="+IDTask;
                 try {
                    pst = connection.createStatement();
                    pst.execute(query);
                    out.print(request.getParameter("deadline")+","+request.getParameter("tag"));
                } catch (SQLException ex) {
                    Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
                }
	}
        else if(!(request.getParameter("deadline").equals("")) && !(request.getParameter("assignee").equals("")))
        {
            query="UPDATE tugas SET deadline='"+request.getParameter("deadline")+"' WHERE IDTask="+IDTask;
            query2="INSERT INTO penugasan(IDTask,username) values("+IDTask+",'"+request.getParameter("assignee")+"')";
             try {
                    pst = connection.createStatement();
                    pst.execute(query);
                    pst.execute(query2);
                    out.print(request.getParameter("deadline")+","+request.getParameter("assignee"));
                } catch (SQLException ex) {
                    Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
	else if(!(request.getParameter("deadline").equals("")))
	{
		query="UPDATE tugas SET deadline='"+request.getParameter("deadline")+"' WHERE IDTask="+IDTask;
             try {
                    pst = connection.createStatement();
                    pst.execute(query);
                    out.print(request.getParameter("deadline"));
                } catch (SQLException ex) {
                    Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
                }
	}
        
        //response.sendRedirect("taskdetails.jsp");
       
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
