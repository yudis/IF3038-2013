<%-- 
    Document   : logoutprocess
    Created on : Apr 12, 2013, 6:11:12 AM
    Author     : Arief
--%>

<%
    session.invalidate();
    response.sendRedirect("index.jsp?error=user logout success");
%>
