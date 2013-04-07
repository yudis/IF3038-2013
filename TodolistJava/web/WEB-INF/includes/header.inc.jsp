<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!-- <meta name="viewport" content="width=device-width; initial-scale=1.0" /> -->
        <title><c:out value="${title}" default="Todolist" /></title>
        <link rel="stylesheet" type="text/css" href="styles/default.css" />
        <link rel="stylesheet" type="text/css" href="styles/mediaqueries.css" />
        <script type="text/javascript" src="scripts/helper/ajaxhelper.js"></script>
        <script type="text/javascript" src="scripts/helper/helper.js"></script>
        <script type="text/javascript" src="scripts/helper/json2.js"></script>
        <script type="application/javascript" src="scripts/helper/nicEdit.js"></script>
        <script type="application/javascript" src="scripts/helper/datetimepicker_css.js"></script>
        <c:out value="${headTags}" escapeXml="false" default="" />
    </head>
    <body <c:out value="${bodyAttrs}" escapeXml="false" default="" />
        <div class="page">
            <header class="content">
                <nav>
                    <div class="logo"><a href="dashboard.php"><img alt="Home" src="images/logo.png" /></a></div>
                    <ul>
                        <li><div><a href="dashboard">Dashboard</a></div></li><li><div><a href="profile.php">Profile</a></div></li><li><div><a href="index.php?logout">Logout</a></div></li>
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
            </header>
            <div class ="content">