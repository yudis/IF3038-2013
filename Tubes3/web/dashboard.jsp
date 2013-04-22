<%-- 
    Document   : dashboard
    Created on : Apr 10, 2013, 6:36:03 PM
    Author     : sthobis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Todolist</title>
        <link rel="stylesheet" type="text/css" href="default.css" />
        <link rel="stylesheet" type="text/css" href="mediaqueries.css" />
        <script src="scripts/helper.js" type="application/javascript"></script>
        <script src="scripts/popup.js" type="application/javascript"></script>
		<script src="scripts/search.js" type="application/javascript"></script>
                <script src="scripts/dashboard.js" type="application/javascript"></script>
    </head>
    <body onload="initializedashboard();">
        <div id="blanket"></div>
        <div id="popUpDiv">
            <h1>Create new category</h1>
            <div class="padding12px">
				<label for="txtNewKategori">Category Name</label>:<br />
                <input id="txtNewKategori" class="inputbox" type="text" placeholder="eg: IF40XX" />
				<br />
				<label for="assignee">Assignee</label>:<br />
				<input id="assignee" name="assignee" class="inputbox" type="text" placeholder="Type username here.." pattern="[A-Za-z0-9 ]{1,}"  list="listuser" />&nbsp<button onclick="addAssignee()">Add User</button>
				<div id="datalistuser">
				</div>
			</div>
            
            <div class="padding12px">
                <ul class="tag" id="assigneelist">
                </ul>
            </div>
            <br />
            <div class="rightalign padding12px"><button onclick="popup('popUpDiv','blanket',300,600); NewKategori()">OK</button> <button onclick="popup('popUpDiv','blanket',300,600)">Cancel</button></div>
            <br />
        </div>
        <div class="page">
            <header class="content">
                <%@include file="header.jsp" %>
            </header>
            <div class="content">
				<div id="leftcolumn">
					<div class="sidebar" id="sidebar"></div>
				</div>
                <div id="listTugas" class="main"></div>
            </div>
            <footer class="content">
                This website is created solely for the purpose of fulfilling our college task.<br />
                IF3094 - Pemrograman Internet.
            </footer>
        </div>
    </body>
</html>