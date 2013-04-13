<%
if (session.getAttribute("myusername") == null)
{
	response.sendRedirect("index.jsp?status=3");
}
%>