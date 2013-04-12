<%-- 
    Document   : index
    Created on : Apr 12, 2013, 1:56:08 PM
    Author     : Sonny Theo Thumbur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SharedToDoList | index</title>
        <link rel="stylesheet" type="text/css" href="style/style.css"></link>
        
        <%--<%@include file="style/style.css" %>--%>
        
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <!--mengeset username--> 
        <%
            String name = "smanurung";
            session.setAttribute("username",name);

//            redirection
            String URL = "profile.jsp";
            response.sendRedirect(URL);
        %>
    </body>
</html>
