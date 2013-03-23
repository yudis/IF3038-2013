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
			<a href="profile.html">PROFILE</a>
			<a href="#" onclick="toggleSearch()">SEARCH</a>
			<a href="index.html">LOGOUT</a>			
		</div>
		<div id="search">
			<input id="searchterm" type="text" size="50%" />
			<select id="searchtype">
				<option value="semua" selected>Semua</option>
				<option value="username">Username saja</option>
				<option value="category">Category saja</option>
				<option value="task">Task saja</option>
			</select>
			<button id="searchbutton" onclick="search()">Search</button>
		</div>
		</div>
		<div class="clearall container">
			<h2>Category&nbsp;&nbsp;<img onclick="popupcat()" src="images/plus.png" id="pluscat"></h2>			
			<?php include('loadcategory.php'); ?>
		</div>
		<div class="clearall container">
			<h2>Task&nbsp;&nbsp;<img onclick="redirAdd()" src="images/plus.png" id="plustask"></h2>			
			<?php include('loadtask.php'); ?>
		</div>		
	</body>
</html>