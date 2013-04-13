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
            int offset = Integer.parseInt(request.getParameter("offset"));
            int limit = (request.getParameter("limit").equals("")) ? 10 : Integer.parseInt(request.getParameter("limit"));
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

                sqlQuery = "select count(*) as count from login where username like '%" + term + "%'";
                resultSet = ConnectDB.mysql_query(sqlQuery);
                pageamount = (int) Math.ceil(Integer.parseInt(resultSet.getString("count")) / 10.0d);

                if (pageamount > 1) {
                    for (int i = 0; i < pageamount; i++) {
                        int start = i * 10;
                        if (i + 1 == pageamount) {
                            limit = Integer.parseInt(resultSet.getString("count")) % 10;
                            out.print("<a href='index.jsp?term=" + term + "&type=" + type + "&offset=" + start + "&limit=" + limit + "'>" + i + "</a>&nbsp;");
                        } else {
                            out.print("<a href='index.jsp?term=" + term + "&type=" + type + "&offset=" + start + "&limit=" + limit + "'>" + i + "</a>&nbsp;");
                        }
                    }
                }
            %> </div>

        <div class="clearall container">
            <h2>Category</h2>			
            <div class="row">
                <div class="cell th">Category</div>
                <div class="cell th centered">Users</div>
            </div>
            <%
                sqlQuery = "select * from category where category like '%" + term + "%' limit " + limit + " offset " + offset;
                resultSet = ConnectDB.mysql_query(sqlQuery);
                while (resultSet.next()) {%>
            <div class="row" id="<%= resultSet.getString("category")%>">
                <div class="cell"><%= resultSet.getString("category")%></div>
                <div class="cell centered"><%= resultSet.getString("users")%></div>
            </div>

            <% }
                out.print("<br>");

                sqlQuery = "select count(*) as count from category where category like '%" + term + "%'";
                resultSet = ConnectDB.mysql_query(sqlQuery);
                pageamount = (int) Math.ceil(Integer.parseInt(resultSet.getString("count")) / 10.0d);

                if (pageamount > 1) {
                    for (int i = 0; i < pageamount; i++) {
                        int start = i * 10;
                        if (i + 1 == pageamount) {
                            limit = Integer.parseInt(resultSet.getString("count")) % 10;
                            out.print("<a href='index.jsp?term=" + term + "&type=" + type + "&offset=" + start + "&limit=" + limit + "'>" + i + "</a>&nbsp;");
                        } else {
                            out.print("<a href='index.jsp?term=" + term + "&type=" + type + "&offset=" + start + "&limit=" + limit + "'>" + i + "</a>&nbsp;");
                        }
                    }
                }
            %> </div>

        <div class="clearall container">
            <h2>Task</h2>			
            <div class="row">
                <div class="cell th">Task Name</div>
                <div class="cell th centered">Deadline</div>
                <div class="cell th centered">Tags</div>			
                <div class="cell th centered">Status</div>
                <div class="cell th centered">Ubah Status</div>
            </div>			
            <%
                sqlQuery = "select * from task where taskname like '%" + term + "%' or tags like '%" + term + "%' limit " + limit + " offset " + offset;
                resultSet = ConnectDB.mysql_query(sqlQuery);
                while (resultSet.next()) {%>
            <div class="row" id="<%= resultSet.getString("taskname") + resultSet.getString("category")%>">
                <div class="cell"><%= resultSet.getString("taskname")%></div>
                <div class="cell centered"><%= resultSet.getString("deadline")%></div>
                <div class="cell centered"><%= resultSet.getString("tags")%></div>
                <div class="cell centered" id="<%= "status" + resultSet.getString("taskname") + resultSet.getString("category")%>"><%= (Integer.parseInt(resultSet.getString("status")) == 1) ? "Selesai" : "Belum Selesai"%></div>
                <div class="cell centered">
                    <input class="<%= resultSet.getString("taskname") + resultSet.getString("category")%>" type="checkbox" id="<%= "../changestatus/?taskname=\''" + resultSet.getString("taskname") + "\'" + "&category=\'" + resultSet.getString("category") + "\'"%>" value="1" <%= (Integer.parseInt(resultSet.getString("status")) == 1) ? "checked" : ""%> onchange="changeStatus(this)" />
                </div>					
            </div>

            <% }
                out.print("<br>");

                sqlQuery = "select count(*) as count from task where taskname like '%" + term + "%' or tags like '%" + term + "%'";
                resultSet = ConnectDB.mysql_query(sqlQuery);
                pageamount = (int) Math.ceil(Integer.parseInt(resultSet.getString("count")) / 10.0d);

                if (pageamount > 1) {
                    for (int i = 0; i < pageamount; i++) {
                        int start = i * 10;
                        if (i + 1 == pageamount) {
                            limit = Integer.parseInt(resultSet.getString("count")) % 10;
                            out.print("<a href='index.jsp?term=" + term + "&type=" + type + "&offset=" + start + "&limit=" + limit + "'>" + i + "</a>&nbsp;");
                        } else {
                            out.print("<a href='index.jsp?term=" + term + "&type=" + type + "&offset=" + start + "&limit=" + limit + "'>" + i + "</a>&nbsp;");
                        }
                    }
                }
            %> </div> <%
            } else if (type.equals("username")) {
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
                <div class="cell"><a href="profile/?u=<%= resultSet.getString("username")%>"><%= resultSet.getString("username")%></a></div>
                <div class="cell centered"><%= resultSet.getString("fullname")%></div>
                <div class="cell centered"><img src="<%= "/images/" + resultSet.getString("photo")%>" ></div>
            </div>
            <% }
                out.print("<br>");

                sqlQuery = "select count(*) as count from login where username like '%" + term + "%'";
                resultSet = ConnectDB.mysql_query(sqlQuery);
                pageamount = (int) Math.ceil(Integer.parseInt(resultSet.getString("count")) / 10.0d);

                if (pageamount > 1) {
                    for (int i = 0; i < pageamount; i++) {
                        int start = i * 10;
                        if (i + 1 == pageamount) {
                            limit = Integer.parseInt(resultSet.getString("count")) % 10;
                            out.print("<a href='index.jsp?term=" + term + "&type=" + type + "&offset=" + start + "&limit=" + limit + "'>" + i + "</a>&nbsp;");
                        } else {
                            out.print("<a href='index.jsp?term=" + term + "&type=" + type + "&offset=" + start + "&limit=" + limit + "'>" + i + "</a>&nbsp;");
                        }
                    }
                }
            %> </div> <%
            } else if (type.equals("category")) {
            %>
        <div class="clearall container">
            <h2>Category</h2>			
            <div class="row">
                <div class="cell th">Category</div>
                <div class="cell th centered">Users</div>
            </div>
            <%
                sqlQuery = "select * from category where category like '%" + term + "%' limit " + limit + " offset " + offset;
                resultSet = ConnectDB.mysql_query(sqlQuery);
                while (resultSet.next()) {%>
            <div class="row" id="<%= resultSet.getString("category")%>">
                <div class="cell"><%= resultSet.getString("category")%></div>
                <div class="cell centered"><%= resultSet.getString("users")%></div>
            </div>

            <% }
                out.print("<br>");

                sqlQuery = "select count(*) as count from category where category like '%" + term + "%'";
                resultSet = ConnectDB.mysql_query(sqlQuery);
                pageamount = (int) Math.ceil(Integer.parseInt(resultSet.getString("count")) / 10.0d);

                if (pageamount > 1) {
                    for (int i = 0; i < pageamount; i++) {
                        int start = i * 10;
                        if (i + 1 == pageamount) {
                            limit = Integer.parseInt(resultSet.getString("count")) % 10;
                            out.print("<a href='index.jsp?term=" + term + "&type=" + type + "&offset=" + start + "&limit=" + limit + "'>" + i + "</a>&nbsp;");
                        } else {
                            out.print("<a href='index.jsp?term=" + term + "&type=" + type + "&offset=" + start + "&limit=" + limit + "'>" + i + "</a>&nbsp;");
                        }
                    }
                }
            %> </div> <%
            } else if (type.equals("task")) {
            %>
        <div class="clearall container">
            <h2>Task</h2>			
            <div class="row">
                <div class="cell th">Task Name</div>
                <div class="cell th centered">Deadline</div>
                <div class="cell th centered">Tags</div>			
                <div class="cell th centered">Status</div>
                <div class="cell th centered">Ubah Status</div>
            </div>			
            <%
                sqlQuery = "select * from task where taskname like '%" + term + "%' or tags like '%" + term + "%' limit " + limit + " offset " + offset;
                resultSet = ConnectDB.mysql_query(sqlQuery);
                while (resultSet.next()) {%>
            <div class="row" id="<%= resultSet.getString("taskname") + resultSet.getString("category")%>">
                <div class="cell"><%= resultSet.getString("taskname")%></div>
                <div class="cell centered"><%= resultSet.getString("deadline")%></div>
                <div class="cell centered"><%= resultSet.getString("tags")%></div>
                <div class="cell centered" id="status<%= resultSet.getString("taskname") + resultSet.getString("category")%>"><%= (Integer.parseInt(resultSet.getString("status")) == 1) ? "Selesai" : "Belum Selesai"%></div>
                <div class="cell centered">
                    <input class="<%= resultSet.getString("taskname") + resultSet.getString("category")%>" type="checkbox" id="<%= "../changestatus/?taskname=\''" + resultSet.getString("taskname") + "\'" + "&category=\'" + resultSet.getString("category") + "\'"%>" value="1" <%= (Integer.parseInt(resultSet.getString("status")) == 1) ? "checked" : ""%> onchange="changeStatus(this)" />
                </div>					
            </div>

            <% }
                out.print("<br>");

                sqlQuery = "select count(*) as count from task where taskname like '%" + term + "%' or tags like '%" + term + "%'";
                resultSet = ConnectDB.mysql_query(sqlQuery);
                pageamount = (int) Math.ceil(Integer.parseInt(resultSet.getString("count")) / 10.0d);

                if (pageamount > 1) {
                    for (int i = 0; i < pageamount; i++) {
                        int start = i * 10;
                        if (i + 1 == pageamount) {
                            limit = Integer.parseInt(resultSet.getString("count")) % 10;
                            out.print("<a href='index.jsp?term=" + term + "&type=" + type + "&offset=" + start + "&limit=" + limit + "'>" + i + "</a>&nbsp;");
                        } else {
                            out.print("<a href='index.jsp?term=" + term + "&type=" + type + "&offset=" + start + "&limit=" + limit + "'>" + i + "</a>&nbsp;");
                        }
                    }
                }
            %> </div> <%
                }
            %>
    </body>
</html>