<%@page import="database.Checker"%>
<%
    if(session.getAttribute("sUsername")==null)
        response.sendRedirect("index.jsp");
%>
<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
    <head>
        <title>S.Y.N. Dashboard</title>
        <link rel="stylesheet" href="header.css" type="text/css" media="screen">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div id="Header">
            <a href="profil.jsp"><img src="<% out.println(Checker.getAvatar(session.getAttribute("sUsername").toString())); %>" width="130" height="130"></a>
            <a href="dashboard.jsp"><img src="images/syn2.jpg"></a>
            <hr>
            <table width="1000">
                <tr>
                    <td width="600">
                        <a href="profil.jsp"><font color="yellow"><% out.println(session.getAttribute("sNama")); %> </font></a> &nbsp;&nbsp;.&nbsp;&nbsp;
                        <a href="dashboard.jsp">Dashboard</a> &nbsp;&nbsp;.&nbsp;&nbsp;
                        <a href="profil.jsp">Profil</a>&nbsp;&nbsp;.&nbsp;&nbsp;
                        <a href="logoutprocess.jsp">Logout</a>
                    </td>
                    <td width="400">
                            <form action="searchresult.jsp" method="GET">
                                <input type="text" name="query">
                                <select name="filter">
                                    <option value="0">All</option>
                                    <option value="1">User</option>
                                    <option value="2">Category</option>
                                    <option value="3">Task</option>
                                </select>
                                <input type="submit" value="search">
                            </form>
                    </td>
                </tr>
            </table>
            <hr>
        </div>
    </body>
</html>