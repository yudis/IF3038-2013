<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<html>
<head>
	<title>Successfully Login by JSP</title>
</head>

<body>
	Successfully login by JSP<br />
	Session Value<br />
<%
out.print("UserName :"+session.getAttribute("username")+"<br>");
out.print(session.getAttribute("avatar"));
%>
</body>
</html>