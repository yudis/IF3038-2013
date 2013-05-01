<%-- 
    Document   : registerprocess
    Created on : Apr 12, 2013, 5:42:03 AM
    Author     : Arief
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="database.MyDatabase,java.sql.ResultSet;" errorPage="" %>
<%
    String username = request.getParameter("daftar_username");
    String password = request.getParameter("daftar_password");
    String namalengkap = request.getParameter("daftar_namalengkap");
    String tanggallahir = request.getParameter("daftar_tanggallahir");
    String mail = request.getParameter("daftar_e-mail");
    String avatar = request.getParameter("daftar_avatar").toString();
    String pQuery = "INSERT INTO `sharedtodolist`.`user` (`username` ,`email` ,`fullname` ,`avatar` ,`tanggalLahir` ,`password`) VALUES('" + username + "','" + mail + "','" + namalengkap + "','" + avatar + "','" + tanggallahir + "','" + password + "')";
    out.println(pQuery);
    int ti = MyDatabase.getSingleton().queryDB(pQuery);
    if (ti != -1) {
        session.setAttribute("isActive", false);
        request.getSession(true);
        session.setMaxInactiveInterval(2592000);
        session.setAttribute("isActive", true);
        session.setAttribute("sUsername", username);
        session.setAttribute("sNama", namalengkap);
        session.setAttribute("sAvatar", avatar);
        response.sendRedirect("dashboard.jsp");
    } else {
        response.sendRedirect("index.jsp?error=registration failed");
    }
%>
