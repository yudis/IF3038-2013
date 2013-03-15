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
			<div>
            <form action="search_result.php" method="post">
            	<input type="text" name="search_text">
	            <select name="modesearch">
							<option value="1">All</option>
							<option value="2">User</option>
							<option value="3">Category</option>
                            <option value="4">Task</option>
						</select>    
                <input type="submit" value="Search">
            </form>

            </div>
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