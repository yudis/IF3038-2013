<%@include file="../ConnectDB.jsp"%>

<%
    String category = request.getParameter("category");
    String user = request.getParameter("user");
    String sqlQuery = "SELECT category, users FROM category WHERE category = '" + category + "'";
    //out.print(sqlQuery);
    ResultSet resultSet = ConnectDB.mysql_query(sqlQuery);

    if (resultSet.next()) 
    {
        out.print("next");
        String users = resultSet.getString("users");
        
        if (!users.contains(user))
        {
            users = users + "," + user;
            sqlQuery = "UPDATE category SET users = '" + users + "' WHERE category = '" + category + "'";
        }
        out.print(users);
    }
    else 
    {
        sqlQuery = "INSERT INTO category VALUES ('" + category + "', '" + user + "')";
    }

    out.print(sqlQuery);
    int result = ConnectDB.mysql_query_updatedata(sqlQuery);
    if (result == 1) 
    {
        //res
    }

    ConnectDB.closeDB();
%>