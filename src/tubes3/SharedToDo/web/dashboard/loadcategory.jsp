<%@page import = "java.sql.ResultSet"%>
<%@include file="../ConnectDB.jsp" %>

<div class="row">
    <div class="cell th centered">Category Name</div>
</div>

<%
    String query = "select * from category";
    ResultSet result;
    result = ConnectDB.mysql_query(query);
    while (result.next()) {
%>
<div class="row" id="<%
    out.print(result.getString("category").trim());
     %>" onclick="showTasks(this)">
    <div class="cell"><%
        out.print(result.getString("category").trim());
        %></div>
</div>
<% }
    ConnectDB.closeDB();
%>