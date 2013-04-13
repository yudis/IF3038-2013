<%@page import="java.sql.ResultSet"%>
<%@include file="../ConnectDB.jsp" %>
<%
    String username = request.getParameter("u");
    String password = request.getParameter("p");
    String sqlQuery = "select username from login where username = '" + username + "' and password = '" + password +  "'";
    ResultSet resultSet = ConnectDB.mysql_query(sqlQuery);
    
    if (resultSet.next()) 
    {
        out.print(resultSet.getString("username").trim());
    }
    
    ConnectDB.closeDB();
%>