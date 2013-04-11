
<%@page import="org.json.JSONArray"%>
<%@ page import="java.util.List" %>
<%@ page import="org.progin.todo.Query" %>
<%@ page import="org.progin.todo.JSON" %>
<%@ page import="org.progin.todo.Function" %>
<%
/*	require_once 'core/config.php';
	if ($login_permission && !getUserId())
		header('Location: index.php');
	if (!$login_permission && getUserId())
		header('Location: dashboard.php');*/
        Integer user_id = (session.getAttribute( "user_id" ) != null)?(Integer)session.getAttribute( "user_id" ):0;
        if (login_permission && user_id == 0)
            response.sendRedirect("index.jsp");
        if (!login_permission && user_id != 0)
            response.sendRedirect("dashboard.jsp");
%>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title><%=title%></title>
		<link rel="stylesheet" href="css/style.css">
	</head>

	<body>
		<div class="site-container">
			<header class="site-header">
				<h1><a href="dashboard.jsp">Do</a></h1>
				<p>A to-do list for getting things done.</p>

				<nav>
					<ul class="main-links">
                                                <% if ( user_id != 0) { %>
						<li class="dashboard-link"><a href="dashboard.jsp">Dashboard</a></li>
						<li class="profile-link" id="profileLink"><a href="profile.jsp?user_id=<%=user_id%>" id="userFullName"><img src="avatar/<%=user_id%>.jpg" class="imgProfileLink"><%=Function.getUserName(user_id.toString())%></a></li>
						<li class="profile-link"><a href="logout.jsp">Logout</a></li>
						<% } else { %>
						<li class="dashboard-link"><a href="index.jsp">Home</a></li>
						<% } %>
					</ul>
                                        <% if ( user_id != 0) { %>
					<div class="search-box">
						<form action="search.jsp" method="get" id="searchForm">
							<select name="mode" id="searchMode">
								<option value="0">All</option>
								<option value="1">User</option>
								<option value="2">Kategori</option>
								<option value="3">Task</option>
								<option value="4">Komentar</option>
							</select>
							<input type="search" id="searchBar" name="q" placeholder="Search" list="suggestion">
							<datalist id="suggestion">
							</datalist>
							<button type="submit">Search</button>
						</form>
					</div>
					<% } %>
				</nav>
			</header>
