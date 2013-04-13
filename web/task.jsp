<%-- 
    Document   : task
    Created on : Apr 11, 2013, 10:46:41 AM
    Author     : PANDU
--%>
<%@page import="Servlets.createtask"%>
<%
    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            createtask user = new createtask();
            user.setAssignee(request.getParameter("assignee"));
            user.setAttachment(request.getParameter("attachment"));
            user.setDeadline(request.getParameter("deadline"));
            user.setTag(request.getParameter("tag"));
            user.setTaskname(request.getParameter("taskname"));
            
            String sget = request.getParameter("tujuan");
            if (sget.contains("buat")) {
                String x = user.Create() ;
                response.sendRedirect("lihattask.jsp?tujuan=lihat&id="+x);
            } else
            if (sget.contains("lihat")){
                response.sendRedirect("lihattask.jsp?tujuan=lihat&id="+request.getParameter("id"));
            }else {
                out.print( "<h1>maaf, gagal</h1>");
            }


        %>
        <a></a><br>
    </body>
</html>
