<%-- 
    Document   : profil
    Created on : Apr 12, 2013, 7:54:12 AM
    Author     : Arief
--%>

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
            ResultSet resultSet = myDBProfile.selectDB("select * from user where username=\""+username+"\"");
            while(resultSet.next()){
        %>
        <h1>User Profile</h1>
        <table id="Content_Table">
            <tr>
                <td width="300">
                    <img src="<% out.println(resultSet.getString("avatar")); %>">
                </td>
                <td width="700" valign="top" align="left">
                    Username : <%= resultSet.getString("username") %><br>
                    Nama Lengkap : <%= resultSet.getString("fullname") %><br>
                    Tanggal Lahir : <%= resultSet.getString("tanggalLahir") %><br>
                    E-mail : <%= resultSet.getString("email") %><br>
                    Done Task List : <br>
                    <%
                        MyDatabase myDBProfile2 = new MyDatabase();
                        String pQuery = "select * from tasktoasignee JOIN task on tasktoasignee.namaTask=task.namaTask where asigneeName=\""+resultSet.getString("username")+"\" and status=\"done\"";
                        ResultSet resultSet2 = myDBProfile2.selectDB(pQuery);
                        while(resultSet2.next()){
                            out.println("<a href=\"viewtask.jsp?namaTask="+resultSet2.getString("namaTask")+"\">"+resultSet2.getString("namaTask")+"</a><br>");
                        }
                    %>
                    UnDone Task List : <br>
                    <%
                        pQuery = "select * from tasktoasignee JOIN task on tasktoasignee.namaTask=task.namaTask where asigneeName=\""+resultSet.getString("username")+"\" and status=\"undone\"";
                        resultSet2 = myDBProfile2.selectDB(pQuery);
                        while(resultSet2.next()){
                            out.println("<a href=\"viewtask.jsp?namaTask="+resultSet2.getString("namaTask")+"\">"+resultSet2.getString("namaTask")+"</a><br>");
                        }
                    %>
                </td>
            </tr>
        </table>
        <% }
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
