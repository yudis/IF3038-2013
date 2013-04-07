<?php
	session_start();
	//session_destroy();
	ob_start();

	/* Configuring Server & Database */
	$host        =    'localhost';
	$user        =    'root';
	$password    =    '';
	$database    =    'progin_405_13510086';

	$con        =    mysql_connect($host,$user,$password) or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');

	/* Login Script */
	$username = mysql_real_escape_string($_POST['username']);
	$password = mysql_real_escape_string($_POST['password']);
	
	if (isset($_POST['login_submit'])) { //when login button is pressed

		$query = "SELECT * FROM user WHERE username= '$username' AND password='$password'";
		$res = mysql_query($query);
		$row = mysql_fetch_array($res, MYSQL_NUM);
		$name = $row[2];

		if (mysql_num_rows($res) > 0) {
			$_SESSION['username'] = $username;
			$_SESSION['fullname'] = $name;			
			header('location:src/dashboard.php'); //redirect to dashboard
		} else {
			header('location:index.php');			
		}
	}



?>

