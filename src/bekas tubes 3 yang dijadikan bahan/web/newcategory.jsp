<%-- 
    Document   : newcategory
    Created on : Apr 12, 2013, 2:58:28 AM
    Author     : Arief
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="database.MyDatabase,java.sql.ResultSet;" errorPage="" %>
<%
    String tQuery = "INSERT INTO `sharedtodolist`.`kategori` (`namaKategori`,`creatorKategoriName`) VALUES('" + request.getParameter("newcateg") + "','" + session.getAttribute("sUsername") + "');";
    int result = MyDatabase.getSingleton().queryDB(tQuery);
    if (result != -1) {
        tQuery = "INSERT INTO `sharedtodolist`.`usertocategory` (`namaKategori`,`username`) VALUES('" + request.getParameter("newcateg") + "','" + session.getAttribute("sUsername") + "');";
        MyDatabase.getSingleton().queryDB(tQuery);
        String namelist = request.getParameter("userlist");
        String[] name = namelist.split(",");
        for (int i = 0; i < name.length; i++) {
            if (!name[i].equals(session.getAttribute("sUsername").toString())) {
                tQuery = "INSERT INTO `sharedtodolist`.`usertocategory` (`namaKategori`,`username`) VALUES('" + request.getParameter("newcateg") + "','" + name[i] + "');";
                MyDatabase.getSingleton().queryDB(tQuery);
            }
        }
        response.sendRedirect("dashboard.jsp?category=" + request.getParameter("newcateg"));
    }
    else
        response.sendRedirect("dashboard.jsp");
%>
