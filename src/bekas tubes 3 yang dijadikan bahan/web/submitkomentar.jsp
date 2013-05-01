<%-- 
    Document   : submitkomentar
    Created on : Apr 12, 2013, 7:26:27 PM
    Author     : Arief
--%>

<%@ page language="java" import="database.MyDatabase,java.sql.ResultSet,java.sql.Timestamp,java.util.Date;" errorPage="" %>
<%
    String nama = request.getParameter("namaTask").toString();
    String username = session.getAttribute("sUsername").toString();
    int target = Integer.parseInt(request.getParameter("target").toString());
    String isikomentar = request.getParameter("isiKomentar").toString();
    java.util.Date date = new java.util.Date();
    String pQuery = "INSERT INTO `sharedtodolist`.`komentar` (`idKomentar` ,`komentator` ,`isikomentar` ,`namaTask` ,`timestamp`) VALUES (NULL,'"+username+"','"+isikomentar+"','"+nama+"','"+new Timestamp(date.getTime())+"')";
    //out.println(pQuery);
    int ti = MyDatabase.getSingleton().queryDB(pQuery);
    if(ti!=-1){
        response.sendRedirect("viewtask.jsp?namaTask="+nama+"&page="+target);
    }
%>