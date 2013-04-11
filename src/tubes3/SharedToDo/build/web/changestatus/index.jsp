<%@page import = "java.sql.ResultSet" %>
<%@include file = "../ConnectDB.jsp" %>
<%
	String taskname = request.getParameter("taskname");
	String category = request.getParameter("category");
	String newvalue = request.getParameter("newvalue");
	
	String query = "update task set status = " + newvalue + " where taskname = " + taskname + " and category = " + category;
	ResultSet result = ConnectDB.mysql_query(query);
	ConnectDB.closeDB();
%>