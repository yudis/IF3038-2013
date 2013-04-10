<%-- 
    Document   : dashboard
    Created on : Apr 8, 2013, 1:32:44 PM
    Author     : M Reza MP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="javax.servlet.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard Page</title>
    </head>
    <body>
        <% 
            String username = (String)session.getAttribute("username");
        %>
        <div>
            <a href="Logout">Log Out</a>
        </div>
        <center>
            <div>Hello <a href='profile/'><%=username%></a></div>
        </center>    
    </body>
</html>