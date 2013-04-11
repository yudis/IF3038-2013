<%@page import = "java.sql.ResultSet"%>
<%@include file="../connect.jsp" %>

<div class="row">
    <div class="cell th centered">Category Name</div>
</div>

<%
    String query = "select * from category";
    ResultSet result;
    result = mysql_query(query);
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
%>