<%@page import="java.util.Enumeration"%>
<%@page import="java.sql.ResultSet"%>
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
        //out.print(result.size());
    }
    
    String username = request.getParameter("u");
    //out.print(username);
    String password = result.get(0);
    String email = result.get(1);
    String fullname = result.get(2);
    String dob = result.get(3);
    String photo = result.get(4);
    ResultSet resultSet = null;
            
    if (photo.equals("")) 
    {
        //out.print("Masuk foto");
        String sqlQuery = "SELECT photo FROM login WHERE username = '" + username + "'";
        //out.print(sqlQuery);
        resultSet = ConnectDB.mysql_query(sqlQuery);
        if (resultSet.next()) {
            photo = resultSet.getString("photo").trim();
        }
    }

    //String sqlQuery = "SELECT photo FROM login WHERE username = '" + username + "'";
        
    String sqlQuery = "UPDATE login SET password = '" + password + "', email = '" + email + "', fullname = '" + fullname + "', dob = '" + dob + "', photo = '" + photo + "' WHERE username = '" + username + "'";
    //out.print(sqlQuery);
    int result2 = ConnectDB.mysql_query_updatedata(sqlQuery);
    //out.print(result);
    if (result2 == 1) 
    {   
        //out.print("berhasil");
        response.sendRedirect("index.jsp?u=" + username + "&e=1");
    }
    else
    {
        out.print("gagal");
    }

    //response.sendRedirect("index.jsp?u=" + username + "&e=1");
    ConnectDB.closeDB();
    
%>