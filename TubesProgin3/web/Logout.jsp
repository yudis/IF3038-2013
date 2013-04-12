<%-- 
    Document   : Logout
    Created on : 12 Apr 13, 3:26:01
    Author     : Devin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ((HttpServletRequest) request).getSession().setAttribute("bananauser", null);
    session.invalidate();
    ((HttpServletResponse) response).sendRedirect("index.jsp");
%>