<!DOCTYPE html>
<html>
	<head>
		<title>Profile - meckyr</title>
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
	<body>
		<div id="main-body-general">
			<!--Header-->
			<div id="header">
				<div id="header-logo"><a href="dashboard.php"><img src="logo.png" width="100px" height="60px"/></a></div>
				<div id="header-title"><a href="dashboard.php"><img src="title.png" width="250px" height="80px"/></a></div>
				<div id="header-link"><a href="dashboard.php"><b>Go To Dashboard</b></a></div>
				<div id="header-right-side">
					<div><input type="text" placeholder="Search Tasks"><button>Search</button></div>
					<div id="header-right-user">
						You logged as, <b>meckyr</b>
						<ul>
							<li><a href="profile.php">Go to Profile</a></li>
							<li><a href="index.php">Sign Out</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div><hr id="border"></div>
			<!--Body-->
		<div id="profile-page-body">
			<div id="profile-header">
				<div id="left-profile-header">
					<img src="ecky.jpg" align="middle"/>
				</div>
				<div id="right-profile-header">
					<h2>MeckyR</h2>
					<br>
					<p>Joined on January 15th, 2013</p>
					<div>
						<div id="left-main-body"><p>About Me :</p></div>
						<div id="right-main-body"><a href="#"><u><p>edit</p></u></a></div>
					</div>
					<div id="about">
						<p>
						Seorang jenius informatika yang berkuliah di ITB. Selama ini berjuang untuk menyelesaikan seluruh tugas besar-tugas besar yang diberikan ITB hingga lulus. Hidup memang berat kawan...
						</p>
					</div>
				</div>
			</div>
			<div><hr id="border"></div>
			<div id="biodata">
				<div>
					<div id="left-profile-body"><p>Full Name : Muhammad Ecky Rabani</p></div>
					<div id="right-profile-body"><a href="#"><u><p>edit</p></u></a></div>
				</div>
				<div>
					<div id="left-profile-body"><p>Birth Date : July 15th, 1992</p></div>
					<div id="right-profile-body"><a href="#"><u><p>edit</p></u></a></div>
				</div>
				<div>
					<div id="left-profile-body"><p>Email : <i>ecky_dozha@yahoo.co.id</i></p></div>
					<div id="right-profile-body"><a href="#"><u><p>edit</p></u></a></div>
				</div>
			</div>
			
			<div id="unfinished-task">
				<div>
					<div id="left-profile-body"><h3>Unfinished Task</h3></div>
					<div id="right-profile-body"><p>Sort by :
						<select name="Sort by">
							<option value="Auto">Auto</option>
							<option value="Name">Name</option>
							<option value="Date">Date</option>
						</select></p>
						<br>
						<br>
						<br>
						<br>
					</div>	
				</div>
				
				<div>
					<ul>
						<li><a href = "task_page.php">Scheduler Iboy</a></li>
						<li><a href = "task_page.php">Knowledge Based System</a></li>
						<li><a href = "task_page.php">Sistem Ajar Flash</a></li>
						<li><a href = "task_page.php">Analisis User Experience</a></li>
						<li><a href = "task_page.php">Video Perkenalan Flash</a></li>
					</ul>
				</div>
			</div>
		
			<div id="finished-task">
				<div>
					<div id="left-profile-body"><h3>Finished Task</h3></div>
					
					<div id="right-profile-body"><p>Sort by :
						<select name="Sort by">
							<option value="Auto">Auto</option>
							<option value="Name">Name</option>
							<option value="Date">Date</option>
						</select></p>
						<br>
						<br>
						<br>
						<br>
					</div>	
				</div>
				
				<div>
					<ul>
						<li>Aplikasi BFS dan DFS</li>
						<li>Makalah Strategi Algoritma</li>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>