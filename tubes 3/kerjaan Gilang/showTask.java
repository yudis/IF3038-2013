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
@WebServlet(name="showTask", urlPatterns={"/showTask"})
public class showTask extends HttpServlet{
   
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
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
        try{
            	out.print("<div id=\"newtask\"><a href=\"newtask.jsp\"> <img id=\"newtaskbutton\" src=\"img/plus.png\" alt=\"plusbutton\" width=\"32\" height=\"32\" ></img>  </a><p id=\"newtasktext\">NEW TASK</p></div>");
            String user = "dummy";
            //String user = request.getAttribute("user");
            String param = request.getParameter("catname");
            request.setAttribute("catname", param);
            
            String url = "jdbc:mysql:" + "//localhost:3306/progin_405_13510074";
                        Class.forName("com.mysql.jdbc.Driver");
                        con = DriverManager.getConnection(url, "root", "");
                        stmt=con.createStatement();
                        String sqlCommand = "SELECT *  FROM tugas WHERE username='" + user + "' AND kategori='" + param +"'";
                                rs = stmt.executeQuery(sqlCommand);
                                String namatugas="";
                                String deadline="";
                                String status="";
                                String setToButton="";
                                String delButton="";
                                String kategori="";
                                
                                while(rs.next()){
                                    namatugas = rs.getString(2);
                                    deadline = rs.getString(3);
                                    kategori=rs.getString(4);
                                    delButton = "<button onclick=\"delTask('"+namatugas+"','"+kategori+"')\">delete</button>";
                                    if (rs.getInt(5)== 0){
                                        status="Not Done";
                                        setToButton="<br><button onclick=\"setToDone('"+namatugas+"')\">Done!</button>";
                                    } else {status = "Done";
                                        setToButton="<br><button onclick=\"setToUndone('"+namatugas+"')\">Set to Undone!</button>";
                                    }
                                    out.print("<div id ='taskbox'><div id='taskimg'><img src='img/note1.png' alt='Logo'> </br></div> <div id='taskdesc'><div id='"+namatugas+"'><p><a href=\"toRincianTugas?value1=" + namatugas +"\">" + namatugas + "</a></p><p>Deadline:"+ deadline +"</p><p>Status: "+ status + setToButton + delButton +"</p></div></div></div>\n");
                                }
            
        }
        catch(Exception e){        
        }
        
}
}