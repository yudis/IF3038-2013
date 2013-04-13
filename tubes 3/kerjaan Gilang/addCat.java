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
@WebServlet(name = "addCat", urlPatterns={"/addCat"})
public class addCat extends HttpServlet{
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
            PrintWriter out = response.getWriter();
            Connection con = null;
            Statement stmt = null;
            
            String name = request.getParameter("catname");
            String asignee = request.getParameter("user");
            
             String user = "dummy";
            //String user = request.getAttribute("user");
            try{            
            String url = "jdbc:mysql:" + "//localhost:3306/progin_405_13510074";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            stmt=con.createStatement();
            String sqlCommand = "INSERT INTO category VALUES ('"+ user +"','"+ name +"')";
            stmt.executeUpdate(sqlCommand);
            
            }
            catch(Exception e){
                e.printStackTrace();
            }
}
}
