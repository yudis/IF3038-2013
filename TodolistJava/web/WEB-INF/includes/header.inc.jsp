<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="id.ac.itb.todolist.model.User" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!-- <meta name="viewport" content="width=device-width; initial-scale=1.0" /> -->
        <title><c:out value="${title}" default="Todolist" /></title>

        <link rel="stylesheet" type="text/css" href="./styles/default.css" />
        <link rel="stylesheet" type="text/css" href="./styles/mediaqueries.css" />
        <script type="application/javascript" src="./scripts/helper/jsonhelper.js"></script>
        <script type="application/javascript" src="./scripts/helper/helper.js"></script>
        <script type="application/javascript" src="./scripts/helper/ajaxhelper.js"></script>
        <script type="application/javascript" src="./scripts/helper/nicEdit.js"></script>
        <script type="application/javascript" src="./scripts/helper/datetimepicker_css.js"></script>
        <script type="application/javascript" src="./scripts/header.js"></script>        
        <c:out value="${headTags}" default="" escapeXml="false" />
    </head>

    <body <c:out value="${bodyAttrs}" default="" escapeXml="false" />>        <div class="page">
            <header class="content">
                <nav>

                    <div class="logo"><a href="dashboard.jsp"><img alt="Home" src="images/logo.png" /></a></div>
                    <ul>

                        <li><div><a href="dashboard.jsp">Dashboard</a></div></li><li><div><a href="profile.jsp">Profile</a></div></li><li><div><a href="index.jsp?logout">Logout</a></div></li>
                    </ul>
                    <div class="search">
                        <div id="searchwrapper">
                            <form method="GET" action="search.jsp">
                                <input type="text" list="autoC" class="searchbox" name="q" id="q" onkeyup="searchAutoComplete();" placeholder="Search here.." />
                                <datalist id="autoC">
                                </datalist>                                
                                <select class="type" name="filter" id="filter">
                                    <option>All</option>
                                    <option>Username</option>
                                    <option>Title</option>
                                    <option>Task</option>
                                </select>
                                <input type="text" id="xvar" name="x" value="0" />
                                <input type="text" id="nvar" name="n" value="username" />
                                <button class="btnsearch"><img src="./images/search.png" alt="Search"/></button>
                            </form>
                        </div>
                    </div>
                </nav>
                <div class="welcomebar">
                    <% User _currentUser = (User) session.getAttribute("user"); %>
                    <a href="./profile.jsp"><img src="./images/avatars/<%= _currentUser.getAvatar() %>" alt="<%= _currentUser.getFullName() %>" width="32" height="32" /></a> Hi <strong><%= _currentUser.getFullName() %></strong> (<a href="./profile.jsp"><%= _currentUser.getUsername() %></a>)
                    </div>
            </header>
            <div class ="content">