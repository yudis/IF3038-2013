<%-- 
    Document   : header
    Created on : Apr 6, 2013, 8:08:53 AM
    Author     : VAIO
--%>

<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Class.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
	<script type="text/javascript" src="javascript/header.js"></script>
    </head>
    <body onLoad="initialize()">
        <div id="header-logo"><a href="dashboard.jsp"><img src="image/logo.png" width="100px" height="60px"/></a></div>
        <div id="header-title"><a href="dashboard.jsp"><img src="image/title.png" width="250px" height="80px"/></a></div>
        <div id="header-link"><a href="dashboard.jsp"><b>Go To Dashboard</b></a></div>
        <div id="header-right-side">
                <div id="header-right-search">
                        <form action="search_result.jsp" method="post">
                                <div id="user-filtering">
                                        <select name="filtering" id="filtering">
                                                <option value="1">None</option>
                                                <option value="2">Email</option>
                                                <option value="3">Full Name</option>
                                                <option value="4">Birthdate</option>
                                        </select>  
                                </div>
                                <div id="searching-header">
                                        <select name="modesearch" id="modesearch" onChange="filter()">
                                                        <option value="1">All</option>
                                                        <option value="2">User</option>
                                                        <option value="3">Category</option>
                                                        <option value="4">Task</option>
                                                </select>    
                                <input type="text" name="search_text" id="search_text" list="searching-auto" value="" onKeyUp="checkHeaderValidation()" />
                                <input type="submit" value="Search"/>
                                <div id="list-search"></div> 
                                </div>
                        </form>
                </div>
                <div id="header-right-user">
                        <%
                        /*session management*/
                        String userActive = "";
                        if(request.getSession().getAttribute("userlistapp")!=null){
                            userActive = request.getSession().getAttribute("userlistapp").toString();
                        }
                        %>
                        <div id="header-photo"><a href="profile.jsp?username=<% out.print(userActive); %>"><img id="photo" src="avatar/<%Function func = new Function();HashMap<String, String> user = func.GetUser(userActive);if(user != null) out.print(user.get("avatar"));%>
                                                                                                                " width="60" height="60" alt="Avatar Tidak Tersedia, <% if(user != null) out.print(user.get("avatar")); %>"/></a></div>
                        <div id="header-profile">
                                You logged as, <b><% if(user != null) out.print(user.get("username")); %>
                                <ul>
                                        <li><a href="profile.jsp?username=<% if(user != null) out.print(user.get("username")); %>">Go to Profile</a></li>
                                        <li><a href="signout">Sign Out</a></li>
                                </ul>
                        </div>
                </div>
        </div>
    </body>
</html>
