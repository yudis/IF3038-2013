<%@include file="../ConnectDB.jsp" %>
<%@include file="../upload.jsp" %>
<%
    String contentType = request.getContentType();
    ArrayList<String> result = new ArrayList<String>();
    String data = "";
    //System.out.println("Content type is :: " + contentType);
    if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) 
    {
        byte[] dataBytes = getDataBytes(request.getInputStream(), request.getContentLength());
        result = upload2(dataBytes, contentType); 

        for(String coba: result)
        {
            out.print(coba);
        }
    }


    String taskname = result.get(0);//request.getParameter("taskname");
    String deadline = result.get(1);//request.getParameter("deadline");
    String tags = result.get(2);//request.getParameter("tags");
    int status = 0;
    String category = request.getParameter("category");
    String attachment = result.get(4);//request.getParameter("attachment");
    String type = result.get(5);//result.get(0);//request.getParameter("filetype");
    String assignee = result.get(3);//request.getParameter("assignee");
    
    String sqlQuery = "INSERT INTO task (taskname, deadline, tags, status, category, attachment, type, assignee) VALUES ('" + taskname + "', '" + deadline + "', '" + tags + "', '" + status + "', '" + category + "', '" + attachment + "', '" + type + "', '" + assignee + "')";
    out.print(sqlQuery);
    
    int resultInt = ConnectDB.mysql_query_updatedata(sqlQuery);

    if (resultInt == 1) 
    {
        out.print("Successful");
        response.sendRedirect("../taskdetails/?taskname='" + taskname + "'&category='" + category + "'");
    } 
    else 
    {
        out.print("ERROR");
    }

    ConnectDB.closeDB();
%>