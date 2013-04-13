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
    
    
    
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510100", "root", "");
    
    session.setAttribute("username", username);
    
    Statement stmt = con.createStatement();
    ResultSet rst = stmt.executeQuery(" SELECT * FROM user WHERE username='"+username+"'");
 
    if (rst.next())
    {
        if (rst.getString("password").equals(password))
        {
			session.setAttribute("iduser",rst.getString("iduser"));
            session.setAttribute("avatar",rst.getString("avatar"));
			out.print("success");
        }
        else
        {
            out.print("No user or password matched");
        }
    }
    
%>
