<%-- 
    Document   : rinciantugas
    Created on : Apr 5, 2013, 4:56:10 PM
    Author     : Christianto
--%>

<%@page import="RincianTugas.DataAwal"%>
<%@page import="javax.xml.crypto.Data"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%DataAwal data = new DataAwal(Integer.parseInt(request.getParameter("id_tugas")));%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/css.css">
        <link rel="stylesheet" href="css/buattask.css">        
        <title>Rincian Tugas : <%out.println(data.getNama());%> </title>
    </head>
    <body>
        <%-- Mulai daerah header buatan Jo--%>
        <jsp:include page="header.jsp" flush="true" />
        
        
        <h1>Hello World!</h1>
    </body>
</html>
