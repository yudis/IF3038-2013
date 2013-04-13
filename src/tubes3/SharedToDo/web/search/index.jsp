<%@page import="java.sql.ResultSet"%>
<%@include file="../ConnectDB.jsp" %><html>
    <head>
        <title>Shared To Do List - Dashboard</title>
        <link rel="stylesheet" type="text/css" href="../style.css">
        <link rel="shortcut icon" href="favicon.ico">		
        <script type="text/javascript" src="../validation.js"></script>
    </head>
    <body onload="checkLogged();">
        <div id="navsearch">
        </div>
        <div class="clearall container">
            <h2>Search Result</h2>
        </div>
        <%
            String term = request.getParameter("term");
            String type = request.getParameter("type");
            int offset = (request.getParameter("offset") == null) ? 0 : Integer.parseInt(request.getParameter("offset"));
            int limit = (request.getParameter("limit") == null) ? 10 : Integer.parseInt(request.getParameter("limit"));
            String sqlQuery = "";
            ResultSet resultSet = null;
            int pageamount = 0;
            if (type.equals("semua")) {
        %>
        <div class="clearall container">
            <h2>Username</h2>
            <div class="row">
                <div class="cell th">Username</div>
                <div class="cell th centered">Full name</div>
                <div class="cell th centered">Avatar</div>
            </div>
            <%
                sqlQuery = "select * from login where username like '%" + term + "%' limit " + limit + " offset " + offset;
                resultSet = ConnectDB.mysql_query(sqlQuery);
                while (resultSet.next()) {%>
            <div class="row" id="<%= resultSet.getString("username")%>">
                <div class="cell"><%= resultSet.getString("username")%></div>
                <div class="cell centered"><%= resultSet.getString("fullname")%></div>
                <div class="cell centered"><img src="<%= "../images/" + resultSet.getString("photo")%>" ></div>
            </div>
            <% }
                out.print("<br>");

            }
        %>
    </body>
</html>