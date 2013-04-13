<%@page import = "java.sql.ResultSet" %>
<%@include file = "../ConnectDB.jsp" %>
<%
    String taskname = request.getParameter("taskname");
    String category = request.getParameter("category");
    String deadline = request.getParameter("deadline");
    String assignee = request.getParameter("assignee");
    String tags = request.getParameter("tags");
    
    String query = "update task set tags = " + tags + ", deadline = " + deadline + ", assignee = " + assignee
            +" where taskname = " + taskname + " and category = " + category;
    int result = ConnectDB.mysql_query_updatedata(query);
%>