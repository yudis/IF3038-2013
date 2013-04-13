<%-- 
    Document   : ubahstatus
    Created on : Apr 12, 2013, 1:39:37 AM
    Author     : Arief
--%>

<%@ page language="java" import="database.MyDatabase,java.sql.ResultSet;" errorPage="" %>
<%
    int target = Integer.parseInt(request.getParameter("target").toString());
    String namaTask = request.getParameter("namaTask").toString();
    int idk = Integer.parseInt(request.getParameter("idkomentar").toString());
    out.println(target);
    out.println(namaTask);
    out.println(idk);
    String pQuery = "DELETE FROM `sharedtodolist`.`komentar` WHERE `komentar`.`idKomentar` = "+idk;
    MyDatabase.getSingleton().queryDB(pQuery);
    response.sendRedirect("viewtask.jsp?namaTask="+namaTask+"&target="+target);
%>
