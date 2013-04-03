<%-- 
    Document   : index
    Created on : Apr 3, 2013, 6:48:43 PM
    Author     : VAIO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World! <%= new java.util.Date() %></h1>
        <form action="UserServlet" method="POST">
            <input type="text" name="username">
            <input type="submit" value="login">
        </form>
    </body>
</html>
