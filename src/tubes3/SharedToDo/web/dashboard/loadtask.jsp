<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<div id="task">
    <div class="row">
        <div class="cell th">Task Name</div>
        <div class="cell th centered">Deadline</div>
        <div class="cell th centered">Tags</div>			
        <div class="cell th centered">Status</div>
        <div class="cell th centered">Ubah Status</div>
        <div class="cell th centered">Hapus Status</div>
    </div>			

    <%
        String category = request.getParameter("category");
        String sqlquery;

        if (category == null) {
            sqlquery = "select * from task";
        } else {
            sqlquery = "select * from task where category = '" + category + "'";
        }

        System.out.println(sqlquery);
        Connection connection = null;
        Statement statement = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin", "progin", "progin");
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ResultSet resultset = statement.executeQuery(sqlquery);
        while (resultset.next()) {
    %>
    <div class="row" id="<%
        out.print(resultset.getString("taskname").trim() + resultset.getString("category").trim());
         %>">

        <a href="<%
            out.print("taskdetails/index.jsp?taskname=\'" + resultset.getString("taskname").trim() + "\'&category=\'" + resultset.getString("category").trim() + "\'");
           %>">
            <div class="cell"><%
                out.print(resultset.getString("taskname").trim());
                %></div>
        </a>

        <div class="cell centered"><%
            out.print(resultset.getString("deadline").trim());
            %></div>

        <div class="cell centered"><%
            out.print(resultset.getString("tags").trim());
            %></div>

        <div class="cell centered" id="status<%
            out.print(resultset.getString("taskname").trim() + resultset.getString("category").trim());
             %>"><%
            out.print(resultset.getString("status").trim().equals("1") ? "Selesai" : "Belum Selesai");
            %></div>

        <div class="cell centered">
            <input class="<%
                out.print(resultset.getString("taskname").trim() + resultset.getString("category").trim());
                   %>"type="checkbox" id="<%
                out.print("../changestatus/index.jsp?taskname=\'" + resultset.getString("taskname").trim() + "\'&category=\'" + resultset.getString("category").trim() + "\'");
                   %>" value="1" <%
                out.print(resultset.getString("status").trim().equals("1") ? "checked " : " ");
                   %>onchange="changeStatus(this)" />
        </div>

        <div class="cell centered">
            <button class="<%
                out.print(resultset.getString("taskname").trim() + resultset.getString("category").trim());
                    %>" id="<%
                out.print("../deletetask/index.jsp?taskname=\'" + resultset.getString("taskname").trim() + "\'&category=\'" + resultset.getString("category").trim() + "\'");
                    %>" onclick="deleteTask(this); return false;">Delete</button>
        </div>
    </div>
    <%
        }

        resultset.close();
        statement.close();
        connection.close();
    %>
</div>