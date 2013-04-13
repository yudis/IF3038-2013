<%@page import = "java.sql.ResultSet" %>
<%@include file = "../ConnectDB.jsp" %>
<%
	String taskname = request.getParameter("taskname");
	String category = request.getParameter("category");
	
	String query = "delete from task where taskname = " + taskname + " and category = " + category;
	int result = ConnectDB.mysql_query_updatedata(query);
	ConnectDB.closeDB();
%>