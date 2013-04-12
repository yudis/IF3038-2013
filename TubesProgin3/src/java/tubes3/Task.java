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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yulianti Oenang
 */
@WebServlet(name = "Task", urlPatterns = {"/Task"})
public class Task extends HttpServlet {
    public String name;
    public String deadline;
    public int status;
    int i=0;
    public List<String> attachment=new ArrayList<String>();
    public List<String> assignee=new ArrayList<String>();
    public String tag;
    public int jumlah;
    public int jumlahA;
    ResultSet rs;
    ResultSet rs2;
    public String IDTask;
    public Task(){}
    public Task(String IDTask) throws SQLException {
        Tubes3Connection tu = new Tubes3Connection();
        Connection connection = tu.getConnection();
        this.IDTask=IDTask;
        
        String queryTask = "SELECT * FROM tugas WHERE IDTask="+ this.IDTask;
        String queryAttachment="SELECT * FROM pelampiran WHERE IDTugas="+this.IDTask;
        String queryAssignee="SELECT username FROM penugasan WHERE IDTask="+this.IDTask;
        String queryJumlahKomentar="SELECT count(*) as jumlah FROM komentar where IDTask="+this.IDTask;
        String queryJumlahAssignee="select count(*) as jumlah from penugasan where IDTask="+this.IDTask;
        rs=tu.coba(connection,queryJumlahAssignee);
        if(rs.next())
        {
            jumlahA=rs.getInt("jumlah");
        }
        rs=tu.coba(connection,queryJumlahKomentar);
        if(rs.next())
        {
            jumlah=rs.getInt("jumlah");
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
    HttpSession session;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // System.out.println("masuk ke sini");
        session=request.getSession();
        
        String user=(String)session.getAttribute("bananauser");
         ResultSet rs,rs2;
         PrintWriter out = response.getWriter();
         
          if (request.getParameter("ID") != null)
              {
                  String sid=request.getParameter("ID");
                  int id = Integer.parseInt(sid);
                  String query="SELECT count(*) as jumlah FROM komentar where IDTask="+id;
                  Tubes3Connection tu = new Tubes3Connection();
                  Connection connection = tu.getConnection();
                  int jum=0;
                   if (request.getParameter("continue").equals("true")) {
                    i++;
                    } else {
                        i = 1;
                        try {
                            rs=tu.coba(connection,query);
                            if(rs.next())
                                {
                                   jum=rs.getInt("jumlah");
                                    out.print("<li>");
                                    out.print("<label id=\"a\"  for=\"komentar\">Komentar("+jum+")</label>");
                                    out.print("</li>");
                                    out.print ("<br>");	
                                    out.print("<div id=\"comment\">");
                                }
                        } catch (SQLException ex) {
                            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                   int awal = 10 * (i - 1);
                   
                    String queryKomentar = "SELECT * FROM komentar WHERE IDTask="+id+" ORDER BY waktu DESC LIMIT " + awal + ", 10;";
                 
                  
                   
                    //String queryUser=;
                    try {
                        
                        rs=tu.coba(connection, queryKomentar);
                          System.out.println(queryKomentar);
                        while (rs.next())
                        {
                            String queryUser="SELECT * FROM pengguna WHERE username='"+rs.getString("username") +"'";
                            rs2=tu.coba(connection,queryUser);
                            if(rs2.next())
                            {
                          out.print ("<div id=\""+rs.getString("IDKomentar") +"\">");
                        out.print ("<div class=\"headerComment\">");
                        out.print ("<div class=avatar style=\"float:left;\">");
                        out.print ("<img src="+rs2.getString("avatar")+" height=\"42\" width=\"42\">");
                        out.print ("</div>");
                        out.print ("<div class=username style=\"float:left;\"><b>"+rs.getString("username"));
                        out.print ("</b></div>");
                        out.print ("<div class=waktu><b>"+rs.getString("waktu"));
                        out.print ("</b></div>");
                        out.print ("<div>");
                        if(!(rs.getString("username").equals(user)))
                        {}
                        else
                        {
                        out.print ("<a class=\"remove\" href=\"\" onClick=\"removeComment("+id+","+rs.getString("IDKomentar")+","+jum+");return false;\" >remove");

                        out.print ("</a>");
                        }
                        out.print ("</div>");
                        out.print ("</div>");
                        out.print ( "<li>"+rs.getString("isi") +"</li>");
                        out.print ("</div>");
                        
                        
                            }
                        }
                    }
                    catch (SQLException ex) {
                        Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
                    }
              }
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
