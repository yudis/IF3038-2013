<%-- 
    Document   : connect
    Created on : Apr 9, 2013, 3:10:54 AM
    Author     : Agah
--%>
<%@page import="com.sun.xml.ws.config.management.persistence.JDBCConfigSaver"%>
<%@page import="java.lang.*"%>
<%@page import="java.sql.*" %>
<%
   String url = "jdbc:mysql://localhost:3306/progin_405_13510010";
   Connection con;
   Statement stmt;
   
   con = DriverManager.getConnection(url, "root", "");
   stmt = con.createStatement();
   String query = "";
%>
