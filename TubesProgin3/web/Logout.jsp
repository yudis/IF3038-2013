<%-- 
    Document   : Logout
    Created on : 12 Apr 13, 3:26:01
    Author     : Devin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ((HttpServletRequest)request).getSession().setAttribute("bananauser", null);
    session.invalidate();
    Cookie[] cookies = ((HttpServletRequest)request).getCookies();
    int i = 0;
    boolean found = false;
    if(cookies != null) {
        while(i < cookies.length && !found) {
            if(cookies[i].getName().equals("bananauser")) {
                cookies[i].setMaxAge(0);
                ((HttpServletResponse)response).addCookie(cookies[i]);
                ((HttpServletResponse)response).sendRedirect("index.jsp");
                found = true;
            }
            else
                i++;
        }
    }
%>