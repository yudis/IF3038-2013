<%@include file="../ConnectDB.jsp" %>
<%@include file="../upload.jsp" %>
<%
    String taskname = request.getParameter("taskname");
    String deadline = request.getParameter("deadline");
    String assignee = request.getParameter("assignee");
    String attachment = request.getParameter("attachment");
    String tags = request.getParameter("tags");
    String type = request.getParameter("filetype");
    int status = 1;
    String category = "progin";

    String sqlQuery = "INSERT INTO task (taskname, deadline, tags, status, category, attachment, type, assignee) VALUES ('" + taskname + "', '" + deadline + "', '" + tags + "', '" + status + "', '" + category + "', '" + attachment + "', '" + type + "', '" + assignee + "')";
    out.print(sqlQuery);
    
    int result = ConnectDB.mysql_query_updatedata(sqlQuery);

    if (result == 1) 
    {
        out.print("Successful");
        //response.sendRedirect("../taskdetails/");
    } 
    else 
    {
        out.print("ERROR");
    }

    ConnectDB.closeDB();
%>