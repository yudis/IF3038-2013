<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!-- <meta name="viewport" content="width=device-width; initial-scale=1.0" /> -->
        <title><c:out value="${title}" default="Todolist" /></title>
        <link rel="stylesheet" type="text/css" href="./styles/default.css" />
        <link rel="stylesheet" type="text/css" href="./styles/mediaqueries.css" />
        <script src="./scripts/jsonhelper.js" type="application/javascript"></script>
        <script src="./scripts/helper.js" type="application/javascript"></script>
        <script src="./scripts/ajaxhelper.js" type="application/javascript"></script>
        <script src="./scripts/nicEdit.js" type="application/javascript"></script>
        <script src="./scripts/datetimepicker_css.js" type="application/javascript"></script>
        <c:out value="${headTags}" default="" escapeXml="false" />
    </head>
    <body <c:out value="${bodyAttrs}" default="" escapeXml="false" />>        <div class="page">
            <header class="content">
                <nav>
                    <div class="logo"><a href="dashboard.php"><img alt="Home" src="images/logo.png" /></a></div>
                    <ul>
                        <li><div><a href="dashboard.jsp">Dashboard</a></div></li><li><div><a href="profile.php">Profile</a></div></li><li><div><a href="index.php?logout">Logout</a></div></li>
                    </ul>
                    <div class="search">
                        <div id="searchwrapper">
                            <form method="GET" action="search.php">
                                <input type="text" class="searchbox" name="q" placeholder="Enter task name here.." />
                                <select class="type" name="filter" id="filter">
                                    <option>All</option>
                                    <option>Username</option>
                                    <option>Title</option>
                                    <option>Task</option>
                                </select>
								<input type="text" id="xvar" name="x" value="0" />
								<input type="text" id="nvar" name="n" value="XXXXX" />
                                <button class="btnsearch"><img src="./images/search.png" alt="Search"/></button>
                            </form>
                        </div>
                    </div>
                </nav>
                    <a href="./profile.jsp"><img src="./images/avatars/<?php echo $_SESSION["user"]["avatar"]; ?>" alt="<?php echo $_SESSION["user"]["full_name"]; ?>" width="32" height="32" /></a> Hi <strong><?php echo $_SESSION["user"]["full_name"]; ?></strong> (<a href="./profile.php"><?php echo $_SESSION["user"]["username"]; ?></a>)
            </header>
            <div class ="content">