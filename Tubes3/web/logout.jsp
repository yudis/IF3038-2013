<%
    session.removeAttribute("userLoginSession");
    response.sendRedirect("index.jsp");
%>
