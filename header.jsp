<%@page import="java.sql.*"%>
<%@include file="/session.jsp"%>
<%@include file="/database.jsp"%>
<%
try
{
	// Connect to server and select database.
	String url = "jdbc:mysql://"+host+":3306/"+db_name;
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection(url, username, password);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>TUGASKU - 
		<%
		int cats;
		String uri = request.getRequestURL().toString();
		uri = uri.substring(uri.lastIndexOf('/') + 1);
		if (uri.equals("profil.jsp"))
		{
			out.print("PROFIL");
		}
		else if (uri.equals("dashboard.jsp"))
		{
			out.print("DASHBOARD");
		}
		else if (uri.equals("rinciantugas.jsp"))
		{
			String id_task = request.getParameter("id");
			PreparedStatement pst = con.prepareStatement("SELECT * FROM `tasks` WHERE id="+id_task);
    		ResultSet rs = pst.executeQuery();
    		rs.next();
    		out.print(rs.getString(2));
    		rs.close();
    		pst.close();
		}
		else if (uri.equals("post.jsp"))
		{
			out.print("ADD TASK");
		}
		else if (uri.equals("search.jsp"))
		{
			out.print("SEARCH RESULT");
		}
		%>
		</title>
		<link rel="stylesheet" type="text/css" href="style.css" title="style1" />
		<link rel="stylesheet" type="text/css" href="style2.css" title="style2" />
		<script type="text/javascript" src="script.js"></script>
	</head>
	<body 
	<%
	if (uri.equals("profil.jsp"))
	{
		out.print("id=\"profilbackground\" onLoad=\"done_task()\"");
	}
	else if (uri.equals("dashboard.jsp") || uri.equals("search.jsp"))
	{
		PreparedStatement pst = con.prepareStatement("SELECT DISTINCT `category` FROM `tasks`");
		ResultSet rs = pst.executeQuery();
		cats = 0;
		while (rs.next())
		{
			cats++;
		}
		rs.close();
		pst.close();
		out.print("id=\"dashboardbackground\"");
	}
	else if (uri.equals("rinciantugas.jsp"))
	{
		out.print("id=\"rincianbackground\" onLoad=\"daone_task()\"");
	}
	else if (uri.equals("post.jsp"))
	{
		out.print("id=\"postbackground\"");
	}
	%>
	>
		<div id="wrapper">
			<div id="header">
				<div id="logo">
					<a href="dashboard.jsp"><img src="images/logo.png" alt="Logo" width="300" height="100" /></a>
				</div>
				<div id="menu">
					<ul>
						<li id="menuhome2"><a href="dashboard.jsp">DASHBOARD</a></li>
						<li id="menudkonten">MY PROFILE: <a href="profil.jsp"><% out.print(session.getAttribute("myusername")); %> &nbsp;<img src='<% out.print(session.getAttribute("avatar")); %>' height="24" /></a></li>
						<li id="menuregister2"><a href="logout.jsp">LOGOUT</a></li>
					</ul>
					
					<br />
                    <div class ="searchoption">
                        <form action="search.jsp" method="get">
                            <input class="searchbox" type="text" name="q" value="Input search" onfocus="searchFocus(this)" onblur="searchBox(this)" />
                            <input name="submit" type="submit" value="Go" /><br />
                            <input type="radio" name="o" value="All" checked/>All
                            <input type="radio" name="o" value="User" />User
                            <input type="radio" name="o" value="Category" />Category
                            <input type="radio" name="o" value="Content" />Content
                        </form>
                    </div>
				</div>
			</div>