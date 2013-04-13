<%-- 
    Document   : search_result
    Created on : Apr 11, 2013, 3:33:01 PM
    Author     : LCF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/search_result.js"></script>
        <link href="styles/header.css" rel="stylesheet" type="text/css" />
        <link href="styles/search_result.css" rel="stylesheet" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body onload="showResult('<%=request.getParameter("key") %>', '<%=request.getParameter("value") %>')">
        <%@include file="header.jsp" %>
        <div id="judul_search">Hasil Pencarian "<%=request.getParameter("value") %>"</div>
        <div id="hasil"></div>
    </body>
</html>
