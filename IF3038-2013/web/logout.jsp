<%
    session.removeAttribute("user_id");
    String redirectURL = "index.jsp";
    response.sendRedirect(redirectURL);
%>
