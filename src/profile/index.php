<html>
	<head>
		<title>Shared To Do List - Profile</title>
		<link rel="stylesheet" type="text/css" href="../style.css">
		<link rel="shortcut icon" href="favicon.ico">		
		<script type="text/javascript" src="../ajax.js"></script>
		<script type="text/javascript" src="../mpquery.js"></script>
		<script type="text/javascript" src="../validation.js"></script>
		<script type="text/javascript" src="profilecontroller.js"></script>
	</head>
	<body onload="loadProfile();">
		<div id="navigation">
			<img />
			<a href="../dashboard.php">DASHBOARD</a>
			<a href="">PROFILE</a>
			<a href="#" onclick="toggleSearch()">SEARCH</a>
			<a href="../" onclick="logout();">LOGOUT</a>			
		</div>
		<div id="search">
			<input type="text" size="50%">
		</div>
		</div>
		<div class="clearall container topcontainer" id="profile container">
			<div class="clearall container" id="initprofile">
				<h2>Profile</h2>
				<h4>Now Loading...</h4>
			</div>
			<div class="clearall container">
				<div class="box">
					<h2>Current Tasks</h2>
				</div>
				<div class = "clearall taskcontainer" id="taskheaders">
					<div class="tasktable header">
						<h4>Task Name</h4>
					</div>	
					<div class="tasktable header">
						<h4>Deadline</h4>
					</div>
					<div class="tasktable header">
						<h4>Tag</h4>
					</div>
				</div>
				<div class = "clearall taskcontainer odd" id="taskcontents">
					<div class="tasktable">
						<h4>Laporan Praktikum Titrasi</h4>
					</div>
					<div class="tasktable">
						<h4>25-09-2013</h4>
					</div>
					<div class="tasktable">
						<h4>titrasi, kimia</h4>
					</div>
				</div>
				<div class = "clearall taskcontainer even" id="taskcontents">
					<div class="tasktable">
						<h4>ADT Point</h4>
					</div>
					<div class="tasktable">
						<h4>26-09-2013</h4>
					</div>
					<div class="tasktable">
						<h4>adt, c++</h4>
					</div>
				</div>
				<div class = "clearall taskcontainer odd" id="taskcontents">
					<div class="tasktable">
						<h4>Tugas Besar 1 IF3038</h4>
					</div>
					<div class="tasktable">
						<h4>31-12-2013</h4>
					</div>
					<div class="tasktable">
						<h4>html, css, javascript, datepicker</h4>
					</div>
				</div>				
			</div>
			<div class="clearall container">
				<div class = "box">
					<h2>Finished Tasks</h2>
				</div>
				<div class = "clearall taskcontainer" id="taskheaders">
					<div class="tasktable header">
						<h4>Task Name</h4>
					</div>	
					<div class="tasktable header">
						<h4>Deadline</h4>
					</div>
					<div class="tasktable header">
						<h4>Tag</h4>
					</div>				
				</div>
				<div class = "clearall taskcontainer odd" id="taskcontents">
					<div class="tasktable">
						<h4>Tugas Pertidaksamaan</h4>
					</div>
					<div class="tasktable">
						<h4>19-09-2013</h4>
					</div>
					<div class="tasktable">
						<h4>pertidaksamaan, kalkulus</h4>
					</div>
				</div>
				<div class = "clearall taskcontainer even" id="taskcontents">
					<div class="tasktable">
						<h4>Laporan Praktikum Pengukuran</h4>
					</div>
					<div class="tasktable">
						<h4>23-09-2013</h4>
					</div>
					<div class="tasktable">
						<h4>pengukuran, fisika</h4>
					</div>
				</div>				
			</div>
		</div>
			<div class="clearall box">
		</div>
	</body>
</html>