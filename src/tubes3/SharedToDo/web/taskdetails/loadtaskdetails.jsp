<%@page import = "java.sql.ResultSet" %>
<%@include file = "../ConnectDB.jsp" %>
<%
    String taskname = request.getParameter("taskname");
    String category = request.getParameter("category");
    String username = request.getParameter("username");

    String query = "select * from task where taskname = " + taskname + " and category = " + category;
    ResultSet result = ConnectDB.mysql_query(query);
    result.next();
%>
<h2><% out.print(result.getString("taskname").trim());%></h2>
<div class="row">
    <div class="cell">Status</div>
    <div class="cell" id="status<% out.print(result.getString("taskname").trim() + result.getString("category").trim());%>"><% out.print(result.getString("status").trim().equals("1") ? "Selesai" : "Belum Selesai");%></div>
</div>	
<div class="row">
    <div class="cell">Change status</div>
    <div class="cell">
        <input class="<% out.print(result.getString("taskname").trim() + result.getString("category").trim());%>" type="checkbox" id="<% out.print("../changestatus/index.jsp?taskname=\'" + result.getString("taskname") + "\'&category=\'" + result.getString("category") + "\'");%>" value="1" <% out.print(result.getString("status").trim().equals("1") ? "checked " : "");%> onchange="changeStatus(this)" />
    </div>
</div>	
<div class="row">
    <div class="cell">Deadline</div>
    <div class="cell"><% out.print(result.getString("deadline").trim());%></div>
</div>
<div class="row">
    <div class="cell">Assignee</div>
    <div class="cell"><% out.print(result.getString("assignee").trim());%></div>
</div>
<div class="row">
    <div class="cell">Tags</div>
    <div class="cell"><% out.print(result.getString("tags").trim());%></div>
</div>
<button class="<% out.print(result.getString("taskname").trim() + result.getString("category").trim());%>" id="<% out.print("../deletetask/index.jsp?taskname=\'" + result.getString("taskname").trim() + "\'&category=\'" + result.getString("category").trim() + "\'");%>" onclick="deleteTask(this);
                return false;">Delete Task</button>
<%
    query = "select count(*) as count from comments where taskname = " + taskname + " and category = " + category;
    result = ConnectDB.mysql_query(query);
    result.next();
%>

<h3><% out.print(result.getString("count").trim());%> Comments</h3>

<%
    query = "select * from comments where taskname = " + taskname + " and category = " + category + "order by time asc";
    result = ConnectDB.mysql_query(query);
    while (result.next()) {%>
<div class="row" disabled id="<% out.print(result.getString("username").trim() + result.getString("time").trim());%>">
    <div class="cell"><% out.print(result.getString("username").trim());%></div>
    <div class="cell"><% out.print(result.getString("time").trim());%></div>
    <div class="cell"><% out.print(result.getString("comment").trim());%></div>			
    <div class="cell">
        <% if (result.getString("username").trim().equals(username)) {%>
        <button class="<% out.print(result.getString("username").trim() + result.getString("time").trim());%>" id="<% out.print("../deletecomment/index.jsp?username=\'" + result.getString("username").trim() + "\'&time=\'" + result.getString("time").trim() + "\'");%>" onclick="deleteComment(this);
                return false;">Delete</button>
        <% }%>
    </div>
</div>
<%
    }

    ConnectDB.closeDB();
%>
