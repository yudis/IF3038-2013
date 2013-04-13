<%-- 
    Document   : logout
    Created on : Apr 11, 2013, 3:16:24 PM
    Author     : Compaq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    request.getSession().removeAttribute("username");
    response.sendRedirect("index.jsp");
%>
