<html>
	<head>
		<title>Shared To Do List - Dashboard</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<link rel="shortcut icon" href="favicon.ico">		
		<script type="text/javascript" src="validation.js"></script>
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
			<div class="row">
				<div class="cell th centered">Category Name</div>
				<div class="cell th centered">Tasks</div>
			</div>
			<div class="row" onclick="toggleSelectProgin()" id="proginrow">
				<div class="cell">Pemrograman Internet</div>
				<div class="cell centered">1</div>
			</div>
			<div class="row even" onclick="toggleSelectKripto()" id="kriptorow">
				<div class="cell">Kriptografi</div>
				<div class="cell centered">1</div>
			</div>			
		</div>
		<div class="clearall container">
			<h2>Task&nbsp;&nbsp;<img onclick="redirAdd()" src="images/plus.png" id="plustask"></h2>
			<div class="row">
				<div class="cell th">Task Name</div>
				<div class="cell th centered">Deadline</div>
				<div class="cell th centered">Tags</div>			
			</div>
			<div class="row" onclick="redirDetails()" id="progin">
				<div class="cell">Tugas Besar 1 IF3038</div>
				<div class="cell centered">31-12-2013</div>
				<div class="cell centered">html, css, javascript, datepicker</div>			
			</div>			
			<div class="row even" id="kripto">
				<div class="cell">Enkripsi Video</div>
				<div class="cell centered">01-03-2013</div>
				<div class="cell centered">video, enkripsi</div>			
			</div>						
		</div>		
	</body>
</html>