<%@include file="../ConnectDB.jsp" %>
<%@include file="../upload.jsp" %>
<%
    String directory = "images";
    out.print(directory);
    
    String contentType = request.getContentType();
    ArrayList<String> result = new ArrayList<String>();
    String data = "";
    //System.out.println("Content type is :: " + contentType);
    if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) 
    {
        byte[] dataBytes = getDataBytes(request.getInputStream(), request.getContentLength());
        result = upload(dataBytes, contentType, directory); 

        for(String coba: result)
        {
            out.print(coba);
        }
    }


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