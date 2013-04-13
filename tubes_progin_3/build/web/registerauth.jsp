<%-- 
    Document   : registerauth
    Created on : Apr 10, 2013, 3:26:12 PM
    Author     : Nicholas Rio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DBOperation.UserOp"%>
<%
    UserOp UO = new UserOp();
    if (request.getParameter("e") != null) {
        String email = request.getParameter("e");
        if(!UO.CheckEmailExist(email)){
            out.write("1");
        }
    } else if (request.getParameter("u") != null) {
        String username = request.getParameter("u");
        if(!UO.CheckUserExist(username)){
            out.write("1");
        }
    }
%>
