<%-- 
    Document   : getAllUser
    Created on : Apr 12, 2013, 4:27:52 PM
    Author     : Nicholas Rio
--%>

<%@page import="Model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="DBOperation.UserOp"%>

<%
    String q = request.getParameter("q");
    
    out.write("<xml>");

    UserOp UO = new UserOp();
    ArrayList<User> users = new ArrayList<User>(UO.FetchUserForSearch(q));
    
    String username;
    String fullname;
    
    for (int i = 0; i < users.size(); i++) {
        username = users.get(i).getUsername();
        fullname = users.get(i).getFullname();
        out.write("<Data>");
        out.write("<ID>");
        out.write(username);
        out.write("</ID>");
        out.write("<String>");
        out.write(fullname);
        out.write("</String>");
        out.write("</Data>");
    }

    out.write("</xml>");
%>
