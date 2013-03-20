<?php
session_start();
if(!isset( $_SESSION['myusername'])){
	header("location:index.php?status=3");
}

include 'database.php';

// Connect to server and select databse.
$con=mysqli_connect($host,$username,$password,$db_name);
if (mysqli_connect_errno()) {
	echo "Failed to connect to MySQL: ".mysqli_connect_error();
}
?>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/php; charset=utf-8" />
		<title>TUBESPROGIN - 
		<?php
		$uri = "$_SERVER[REQUEST_URI]";
		$uri = substr($uri, 9);
		$pos = strpos($uri,"?");
		if ($pos === false) {
			//do nothing
		} else {
			$uri = substr($uri,0,$pos);
		}
		if (strcmp($uri,"src/profil.php") == 0) {
			echo 'HOME';
		} else if (strcmp($uri,"src/dashboard.php") == 0) {
			echo 'DASHBOARD';
		} else if (strcmp($uri,"src/rinciantugas.php") == 0) {
			$id_task= $_GET['id'];
			$result=mysqli_query($con,"SELECT * FROM `tasks` FROM WHERE id=$id_task");
			$task=mysqli_fetch_array($result);
			echo strtoupper($task['name']);
		} else if (strcmp($uri,"src/post.php") == 0) {
			echo 'ADD TASK';
		}
		?>
		</title>
		<link rel="stylesheet" type="text/css" href="style.css" title="style1" />
		<link rel="stylesheet" type="text/css" href="style2.css" title="style2" />
		<script type="text/javascript" src="script.js"></script>
	</head>
	<body 
	<?php
		if (strcmp($uri,"src/profil.php") == 0) {
			echo "id='profilbackground'";
		} else if (strcmp($uri,"src/dashboard.php") == 0) {
			$result=mysqli_query($con,"SELECT DISTINCT `category` FROM `tasks`");
			$cats=mysqli_num_rows($result);
			echo "id='dashboardbackground' onload='javascript:hidetask(".$cats.")'";
		} else if (strcmp($uri,"src/rinciantugas.php") == 0) {
			echo "id='rincianbackground'";
		} else if (strcmp($uri,"src/post.php") == 0) {
			echo "id='postbackground'";
		}
	?>
	>
		<div id="wrapper">
			<div id="header">
				<div id="logo">
					<a href="dashboard.php"><img src="images/logo.png" alt="Logo" width="300" height="100" /></a>
				</div>
				<div id="menu">
					<ul>
						<li id="menuhome2"><a href="dashboard.php">DASHBOARD</a></li>
						<li id="menudkonten"><a href="profil.php">PROFIL</a></li>
						<li id="menuregister2"><a href="logout.php">LOGOUT</a></li>
					</ul>
                            <div class ="searchoption">
                                <input class="searchbox" type="text" value="Input search" onfocus="searchFocus(this)" onblur="searchBox(this)" />
                            </div>
				</div>
			</div>