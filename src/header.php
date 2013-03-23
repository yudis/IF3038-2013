<?php
	session_start();
	$_SESSION['username'] ="dummy";
	if(!isset($_SESSION['username'])) {
		header("Location:index.php");
	}
?>

<html>
<head>
	<meta charset="utf-8">
	<title>TUBES PROGIN</title>
	<link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
	
	<header>
		<div id="header">
			<div id="topbar">
				<div id="topbar_logo">
					<a href="dashboard.html"><img id="logo" src="img/logo.png"></a>
				</div>
				<div id="topbar_logo2">
					<img id="logo2" src="img/namalogo.png">
				</div>
				<div id="topbar_border"></div>
				<div id="topbar_dashboard">
					<nav>
						<ul>
							<li> <a class="active" href="#"> Dashboard </a> </li>
							<li> <a href="profil.html">Profil</a> </li>
							<li> <a href="logout.php">Logout</a> </li>
						</ul>
					</nav>
				</div>
				<div id="topbar_search">  
					<form action="search.php" method="get">
					Coba search<input type="search" results="5" placeholder="search" name="searchedWord">
					<br/>
					<select name="filter">
						<option value="all">All</option>
						<option value="username">Username</option>
						<option value="kategori">Kategori</option>
						<option value="task">Task</option>
					</select>
					<input type="submit" value="Search">					
					</form>
					<form>

				</div>
				
				<div id="avatar">
					<?php						
						mysql_connect("localhost","root","") or die ("Cannot connect to MySQL");
						mysql_select_db("progin") or die ("Cannot connect to progin database");
						$username=$_SESSION['username'];
						$result = mysql_query("SELECT avatar FROM profil where username=$username");
						$row = mysql_fetch_array($result);
						echo "<a href='profil.html'>";
						echo $_SESSION['username'];
						echo "</a>";
				?>
				</div>
			</div>
		</div>
	</header>
</body>
</html>