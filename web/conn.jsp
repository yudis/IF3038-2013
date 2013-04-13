<%-- 
    Document   : conn
    Created on : Apr 11, 2013, 4:12:25 PM
    Author     : Agnes
--%>

<%@ page language="java" import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%
    String username=request.getParameter("username");
    String password=request.getParameter("password");
    
    Connection con = null;
    ResultSet rst = null;
    Statement stmt = null;
    
    String driver = "com.mysql.jdbc.Driver";
    Class.forName(driver).newInstance();
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510100", "root", "");
    
    session.setAttribute("username", username);
    
    stmt = con.createStatement();
    rst = stmt.executeQuery(" SELECT * FROM user WHERE username='"+username+"' ");
 
    if (rst.next())
    {
        if (rst.getString("password").equals(password))
        {
            session.setAttribute("iduser",rst.getString("iduser"));
            session.setAttribute("avatar",rst.getString("avatar"));
            out.print("Success");
        }
        else
        {
            out.print("Failed");
        }
    }
    
%>