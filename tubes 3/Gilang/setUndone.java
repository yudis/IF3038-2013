/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import javax.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gilang
 */
@WebServlet(name="setUndone", urlPatterns={"/setUndone"})
public class setUndone extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws Exception{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet taskdetail</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet taskdetail at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
            PrintWriter out = response.getWriter();
            String namatugas = request.getParameter("t");
            String user = "dummy";
            
            Connection con = null;
            Statement stmt = null;
            
            try{
                String url = "jdbc:mysql:" + "//localhost:3306/progin_405_13510074";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            stmt=con.createStatement();
            String sql = "UPDATE tugas SET status=0 WHERE username='"+user+"' AND namatugas='"+namatugas+"'";
            stmt.execute(sql);
            String sqlRtr = "SELECT * FROM tugas WHERE username='"+user+"' AND namatugas='"+namatugas+"'";
             ResultSet rs = stmt.executeQuery(sqlRtr);
             while(rs.next()){
                                String deadline="";
                                String status="";
                                String setToButton="";
                                String delButton="";
                                String kategori="";
                                
                                    namatugas = rs.getString(2);
                                    deadline = rs.getString(3);
                                    kategori=rs.getString(4);
                                    delButton = "<button onclick=\"delTask('"+namatugas+"','"+kategori+"')\">delete</button>";
                                    if (rs.getInt(5)== 0){
                                        status="Not Done";
                                        setToButton="<br><button onclick=\"setToDone('"+namatugas+"')\">Done!</button>";
                                    } else {status = "Done";
                                        setToButton="<br><button onclick\"setToUndone('"+namatugas+"')\">Set to Undone!</button>";
                                    }
                                    
                                    out.print("<p><a href=\"toRincianTugas?value1=" + namatugas +"\">" + namatugas + "</a></p><p>Deadline:"+ deadline +"</p><p>Status: "+ status + setToButton + delButton +"</p>\n");}
                                }         
            catch(Exception e){
                out.print("masuk excep");
            }
            }
}
