<%-- 
    Document   : login
    Created on : Apr 9, 2013, 3:36:54 AM
    Author     : Agah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="connect.jsp" %>
<% 
   query ="SELECT * FROM user WHERE username=a AND password=bb";
   stmt.execute(query);
   
   con.close();
%>
