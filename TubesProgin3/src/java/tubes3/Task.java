package tubes3;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tubes3.Tubes3Connection;

/**
 *
 * @author Yulianti Oenang
 */
@WebServlet(name = "Task", urlPatterns = {"/Task"})
public class Task extends HttpServlet {
    public String name;
    public String deadline;
    public int status;
    public List<Komentar> comment=new ArrayList<Komentar>();
    public List<String> attachment=new ArrayList<String>();
    public List<String> assignee=new ArrayList<String>();
    public String tag;
    public int jumlah;
    public int jumlahA;
    ResultSet rs;
    ResultSet rs2;
    public int IDTask;
    public Task() throws SQLException {
        Tubes3Connection tu = new Tubes3Connection();
        Connection connection = tu.getConnection();
        int IDtask = 1;
        IDTask=IDtask;
        
        String queryTask = "SELECT * FROM tugas WHERE IDTask="+ IDTask;
        String queryAttachment="SELECT * FROM pelampiran WHERE IDTugas="+IDTask;
        String queryComment="SELECT * FROM komentar WHERE IDTask="+IDTask+" order by waktu DESC";
        String queryAssignee="SELECT username FROM penugasan WHERE IDTask="+IDTask;
        String queryJumlahKomentar="SELECT count(*) as jumlah FROM komentar where IDTask="+IDTask;
        String queryJumlahAssignee="select count(*) as jumlah from penugasan where IDTask="+IDTask;
        rs=tu.coba(connection,queryJumlahAssignee);
        if(rs.next())
        {
            jumlahA=rs.getInt("jumlah");
        }
        rs=tu.coba(connection,queryTask);
        if (rs.next())
        {
            name=rs.getString("name");
            
            deadline=rs.getString("deadline");
            status=rs.getInt("stat");
            tag=rs.getString("tag");
            rs=tu.coba(connection,queryAssignee);
            
            while(rs.next())
            {
                assignee.add(rs.getString("username"));
            }
            
            rs=tu.coba(connection,queryAttachment);
            while(rs.next())
            {
                attachment.add(rs.getString("lampiran"));
            }
            
            rs=tu.coba(connection,queryComment);
            while(rs.next())
            {
                String queryAvatar="SELECT avatar FROM pengguna WHERE username='"+rs.getString("username")+"'";
                rs2=tu.coba(connection,queryAvatar);
                if(rs2.next())
                {
                    Komentar comment2=new Komentar(rs.getString("isi"),rs.getString("username"),rs.getString("waktu"),rs2.getString("avatar"),rs.getInt("IDKomentar"));
                    comment.add(comment2);
                }
                else
                {
                    System.out.println("username ngaco shg avatar tidak ditemukan");
                }
            }
            /*
            rs=tu.coba(connection, queryJumlahKomentar);
            jumlah=rs.getInt("jumlah");*/
        }
        
    }

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
            out.println("<title>Servlet Task</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Task at " + request.getContextPath () + "</h1>");
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
        String query;
	if(status==1)
	{
            status=0;
            query="UPDATE tugas SET stat='0' WHERE IDTask="+IDTask;
	}
	else
	{
            status=1;
            query="UPDATE tugas SET stat='1' WHERE IDTask="+IDTask;
	}
        Tubes3Connection tu = new Tubes3Connection();
        Connection connection = tu.getConnection();
        Statement pst;
        try {
            pst = connection.createStatement();
            pst.executeUpdate(query);
            
        } catch (SQLException ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
	response.sendRedirect("taskdetails.jsp");
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
