<%-- 
    Document   : login
    Created on : Apr 9, 2013, 11:33:16 PM
    Author     : Nicholas Rio
--%>

<%@page import="java.security.MessageDigest"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DBOperation.UserOp"%>
<%
    String password = request.getParameter("p").toString();
    String username = request.getParameter("u").toString();
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(password.getBytes());
    byte[] digest = md.digest();
    StringBuffer hexString = new StringBuffer();
    for(int i=0;i<digest.length;i++){
        password = Integer.toHexString(0xFF & digest[i]);
        if(password.length()<2){
            password = "0" + password;
        }
        hexString.append(password);
    }
    password = hexString.toString();
    UserOp UO = new UserOp();
    if(UO.UserAuth(username, password)){
        out.write("1");
        session.setAttribute("username", username);
    }else{
        out.write("0");
    }
%>
