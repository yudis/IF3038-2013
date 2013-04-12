<%
    if (session.getAttribute("userid")==null){
        response.sendRedirect("index.jsp");
    }
%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    
<head>
    <title>Dashboard | So La Si Do</title>
	
    <link href="css/dashboard.css" rel="stylesheet" type="text/css" />
    <link href="css/search.css" rel="stylesheet" type="text/css" />
    <link href="css/mediaqueries.css" rel="stylesheet" type ="text/css" />
    <!--<link href='http://fonts.googleapis.com/css?family=Merienda' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Skranji' rel='stylesheet' type='text/css'>-->

</head>

<body>
    <script type="text/javascript" language="javascript" src="js/dashboard.js"></script>
    <script type="text/javascript" language="javascript" src="js/search.js"></script>
	
<div class="header">
    <a href="dashboard.html"><img align="left" src="images/logo.png" width="150" height="50" />
	<h6>Dashboard</h6></a> <p>|</p> <a href="profile.html">Profile</a> <p>|</p> <a href="index.html">Logout</a>
        <form id="searchform" onsubmit="return searchByFilter()" method="post" >
   | Search:<input type="search" id="searchquery" name="searchquery" /> 
   Filter: <select id="filtertype" name="filtertype">
        <option value="All" selected>All</option>
        <option value="Username">Username</option>
        <option value="Category">Category</option>
        <option value="Task">Task</option>
   </select>
   <input type="submit" value="GO" />
        </form>
	</div>
    <br>
    <center>Pilih Kategori	:
		<form>
		<select name="users" onChange="taketask(this.value)">
                    <option></option>
                    <%
                    java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin","progin","progin"); 
                    Statement state = con.createStatement();
                    ResultSet rs = state.executeQuery("select * from category");
                    while(rs.next()){
                       %>
                       <option value="
                            <%=
                            rs.getString(1)
                           %>   
                               ">
                           <%=
                            rs.getString(1)
                           %>
                       </option>
                    <%   
                    }
                    state.close();
                    con.close();
                    %>
		</select>
		</form>
		<button onclick="addkategori()">Tambah Kategori</input>
		<button onclick="hapuskategori()">Hapus Kategori</input>
	</center>
	<div class="kategori">
	<center><h2 class="judul">Daftar Tugas</h2>
	<table>
	<tr><td><div class="judulkat"
        onmouseover="document.getElementById('div1').style.display = 'block'; document.getElementById('addtask').style.display = 'block';"
		onmouseout="document.getElementById('div1').style.display = 'block'; document.getElementById('addtask').style.display = 'block';">
		
   <div id="div1" style="display: block;">
	
	</div>
	</div>
   </td>
   
   <td><div class="judulkat"
        onmouseover="document.getElementById('div1').style.display = 'none'; document.getElementById('addtask3').style.display = 'block';"
		onmouseout="document.getElementById('div1').style.display = 'block'; document.getElementById('addtask3').style.display = 'none';"><p id="cats"></p>
		<div id="addtask3" style="display: none;">
		<a href="tambah.html"><img src="images/newtask.png"></a>
	</div>
   </td>
   
   </tr></table>  
   
 
	
</center>
</body>
</html>