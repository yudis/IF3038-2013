<?php 
	session_start();
	if (!isset($_SESSION["userlistapp"]))
		header("Location: ../index.php");
?>
<!DOCTYPE html>
<html>
	<head>
		<title>Header</title>
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
	<body>
		<div id="header-logo"><a href="dashboard.php"><img src="../image/logo.png" width="100px" height="60px"/></a></div>
		<div id="header-title"><a href="dashboard.php"><img src="../image/title.png" width="250px" height="80px"/></a></div>
		<div id="header-link"><a href="dashboard.php"><b>Go To Dashboard</b></a></div>
		<div id="header-right-side">
			<div><a href="search_result.php"><input type="text" placeholder="Search Tasks"><button>Search</button></a></div>
			<div id="header-right-user">
				You logged as, <b><?php echo $_SESSION["userlistapp"]?></b>
				<ul>
					<li><a href="profile.php?username=<?php echo $_SESSION["userlistapp"]?>">Go to Profile</a></li>
					<li><a href="../php/signout.php">Sign Out</a></li>
				</ul>
			</div>
		</div>
	</body>
</html>