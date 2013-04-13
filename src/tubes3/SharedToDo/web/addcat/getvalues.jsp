<%@page import="java.sql.ResultSet"%>
<%@include file="../ConnectDB.jsp" %>
<%
	String type = (request.getParameter("type").equals("users")) ? "username" : request.getParameter("type");
	String value = request.getParameter("value");
        //out.print("<p>" +  type + "</p>");
        //out.print("<p>" +  value + "</p>");
        
	String table = (type.equals("category")) ? type : "login";
	String sqlQuery = "SELECT * FROM " + table + " WHERE " + type + " LIKE '%" + value + "%'";
        //out.print("<p>" +  sqlQuery + "</p>");
        
	ResultSet resultSet = ConnectDB.mysql_query(sqlQuery);

	while (resultSet.next())
	{
            String output = resultSet.getString(type);
            out.print("<p>" +  output + "</p>");
	}

	ConnectDB.closeDB();
        
%>