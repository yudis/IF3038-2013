<html>
	<head>
		<title>Shared To Do List - Dashboard</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<link rel="shortcut icon" href="favicon.ico">		
		<script type="text/javascript" src="validation.js"></script>
		<script>checkLogged()</script>
	</head>
	<body>
		<div id="navigation">
			<img src="images/logo.gif">
			<a href="">DASHBOARD</a>
			<a href="profile/">PROFILE</a>
			<a href="#" onclick="toggleSearch()">SEARCH</a>
			<a href="index.php" onclick="logout()";>LOGOUT</a>			
		</div>
		<div id="search">
			<input type="text" size="50%">
		</div>
		</div>
		<div class="clearall container">
			<h2>Category&nbsp;&nbsp;<img onclick="popupcat()" src="images/plus.png"id="pluscat"></h2>
			<?php include('loadcategory.php'); ?>		
		</div>
		<div class="clearall container">
			<h2>Task&nbsp;&nbsp;<img onclick="redirAdd()" src="images/plus.png" id="plustask"></h2>
			<?php include('loadtask.php'); ?>					
		</div>		
	</body>
</html>