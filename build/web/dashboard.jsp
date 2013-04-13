<%-- 
    Document   : dashboard
    Created on : Apr 12, 2013, 2:48:20 PM
    Author     : Yusuf Ardi
--%>

<%@page session="true"%>
<%
if(session.getAttribute("username")==null) {
    
    response.sendRedirect("index.jsp");
}
%>
<!-- Isi detail dashboar.jsp disini -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Success</title>
</head>
<body>
<center>
<h1>You are successfully logged in</h1>
<h3><a href="index.jsp">Logout</a></h3>
</center>
</body>
</html>