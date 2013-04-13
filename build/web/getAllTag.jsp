<%-- 
    Document   : getAllTag
    Created on : Apr 13, 2013, 12:56:37 AM
    Author     : dell
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="DBOperation.TagOp"%>

<%
    String q = request.getParameter("q");

    out.write("<xml>");

    TagOp tagOp = new TagOp();
    ArrayList<String> tags = new ArrayList<String>(tagOp.FetchTagForSearch(q));
    for (int i = 0; i < tags.size(); i++) {
        String temp = tags.get(i);
        out.write("<Data>");
        out.write("<ID>");
        out.write(temp);
        out.write("</ID>");
        out.write("<String>");
        out.write(temp);
        out.write("</String>");
        out.write("</Data>");
    }

    out.write("</xml>");
%>
