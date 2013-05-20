<%-- 
    Document   : deletetask
    Created on : Apr 12, 2013, 2:13:51 AM
    Author     : Arief
--%>
<%@page import="database.Checker"%>
<%@ page language="java" import="database.MyDatabase,java.sql.ResultSet;" errorPage="" %>
<%
    String namaTask = request.getParameter("namaTask");
    if (Checker.isCreator(session.getAttribute("sUsername").toString(), namaTask)) {
        String tQuery;
        tQuery = "DELETE FROM `sharedtodolist`.`komentar` WHERE `komentar`.`namaTask`= \"" + namaTask + "\"";
        MyDatabase.getSingleton().queryDB(tQuery);
        tQuery = "DELETE FROM `sharedtodolist`.`tagging` WHERE `tagging`.`namaTask` = \"" + namaTask + "\"";
        MyDatabase.getSingleton().queryDB(tQuery);
        tQuery = "DELETE FROM `sharedtodolist`.`tasktoasignee` WHERE `tasktoasignee`.`namaTask` = \"" + namaTask + "\"";
        MyDatabase.getSingleton().queryDB(tQuery);
        tQuery = "DELETE FROM `sharedtodolist`.`attach` WHERE `attach`.`namaTask` = \"" + namaTask + "\"";
        MyDatabase.getSingleton().queryDB(tQuery);
        tQuery = "DELETE FROM `sharedtodolist`.`task` WHERE `task`.`namaTask` = \"" + namaTask + "\"";
        MyDatabase.getSingleton().queryDB(tQuery);
    }
    response.sendRedirect("dashboard.jsp");
%>