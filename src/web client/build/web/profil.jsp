<%-- 
    Document   : profil
    Created on : Apr 12, 2013, 7:54:12 AM
    Author     : Arief
--%>

<%@page import="org.json.JSONArray"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="database.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="dashboard.css" media="screen">
        <link rel="stylesheet" href="loginpage.css" type="text/css" media="screen">
        <title>S.Y.N. Profile</title>
    </head>
    <jsp:include page="header.jsp"/>
    <body>
        &nbsp
        <br>
        &nbsp
        <br>
        &nbsp
        <br>
        &nbsp
        <br>
        <%
            String username;
            MyDatabase myDBProfile = new MyDatabase();
            if(request.getParameter("username")==null)
                username = session.getAttribute("sUsername").toString();
            else
                username = request.getParameter("username");
            JSONArray resultSet = myDBProfile.selectDBREST("select * from user where username=\""+username+"\"");
            int i = 0;
            while(!resultSet.isNull(i)){
        %>
        <h1>User Profile</h1>
        <table id="Content_Table">
            <tr>
                <td width="300">
                    <img src="<% out.println(resultSet.getJSONObject(i).getString("avatar")); %>">
                </td>
                <td width="700" valign="top" align="left">
                    Username : <%= resultSet.getJSONObject(i).getString("username") %><br>
                    Nama Lengkap : <%= resultSet.getJSONObject(i).getString("fullname") %><br>
                    Tanggal Lahir : <%= resultSet.getJSONObject(i).getString("tanggalLahir") %><br>
                    E-mail : <%= resultSet.getJSONObject(i).getString("email") %><br>
                    Done Task List : <br>
                    <%
                        MyDatabase myDBProfile2 = new MyDatabase();
                        String pQuery = "select * from tasktoasignee JOIN task on tasktoasignee.namaTask=task.namaTask where asigneeName=\""+resultSet.getJSONObject(i).getString("username")+"\" and status=\"done\"";
                        JSONArray resultSet2 = myDBProfile2.selectDBREST(pQuery);
                        int j = 0;
                        while(!resultSet2.isNull(j)){
                            out.println("<a href=\"viewtask.jsp?namaTask="+resultSet2.getJSONObject(j).getString("namaTask")+"\">"+resultSet2.getJSONObject(j).getString("namaTask")+"</a><br>");
                            j++;
                        }
                    %>
                    UnDone Task List : <br>
                    <%
                        pQuery = "select * from tasktoasignee JOIN task on tasktoasignee.namaTask=task.namaTask where asigneeName=\""+resultSet.getJSONObject(i).getString("username")+"\" and status=\"undone\"";
                        resultSet2 = myDBProfile2.selectDBREST(pQuery);
                        j=0;
                        while(!resultSet2.isNull(j)){
                            out.println("<a href=\"viewtask.jsp?namaTask="+resultSet2.getJSONObject(j).getString("namaTask")+"\">"+resultSet2.getJSONObject(j).getString("namaTask")+"</a><br>");
                            j++;
                        }
                    %>
                </td>
            </tr>
        </table>
        <% 
            i++;}
            %>
            <div align="center">
                <%
                    if(request.getParameter("username")==null){
                        %>
                        <form action="editprofile.jsp" method="POST">
                            <input type="hidden" name="username" value="<%= session.getAttribute("sUsername") %>">
                            <input type="submit" value="Edit Profile">
                        </form>
                        <%
                    }
                %>
            </div>
    </body>
</html>
