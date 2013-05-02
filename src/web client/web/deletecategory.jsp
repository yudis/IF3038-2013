<%-- 
    Document   : deletecategory
    Created on : Apr 12, 2013, 5:10:46 AM
    Author     : Arief
--%>

<%@page import="database.Checker"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="database.MyDatabase,java.sql.ResultSet;" errorPage="" %>
<%
    String namaKategori = request.getParameter("namaKategori");
    if (Checker.getCategoryCreator(namaKategori).equals(session.getAttribute("sUsername").toString())) {
        String pQuery = "select * from task where namaKategori=\"" + namaKategori + "\"";
        ResultSet tResult = MyDatabase.getSingleton().selectDB(pQuery);
        while (tResult.next()) {
            String namaTask = tResult.getString("namaTask");
            String tQuery;
            MyDatabase mydb1 = new MyDatabase();
            tQuery = "DELETE FROM `sharedtodolist`.`komentar` WHERE `komentar`.`namaTask`= \"" + namaTask + "\"";
            mydb1.queryDB(tQuery);
            tQuery = "DELETE FROM `sharedtodolist`.`tagging` WHERE `tagging`.`namaTask` = \"" + namaTask + "\"";
            mydb1.queryDB(tQuery);
            tQuery = "DELETE FROM `sharedtodolist`.`tasktoasignee` WHERE `tasktoasignee`.`namaTask` = \"" + namaTask + "\"";
            mydb1.queryDB(tQuery);
            tQuery = "DELETE FROM `sharedtodolist`.`attach` WHERE `attach`.`namaTask` = \"" + namaTask + "\"";
            mydb1.queryDB(tQuery);
            tQuery = "DELETE FROM `sharedtodolist`.`task` WHERE `task`.`namaTask` = \"" + namaTask + "\"";
            mydb1.queryDB(tQuery);
        }
        pQuery = "DELETE FROM `sharedtodolist`.`usertocategory` WHERE `usertocategory`.`namaKategori` = \"" + request.getParameter("namaKategori") + "\"";
        MyDatabase.getSingleton().queryDB(pQuery);
        pQuery = "DELETE FROM `sharedtodolist`.`kategori` WHERE `kategori`.`namaKategori`=\"" + request.getParameter("namaKategori") +"\"";
        MyDatabase.getSingleton().queryDB(pQuery);
        out.println(pQuery);
    }
    response.sendRedirect("dashboard.jsp");
%>