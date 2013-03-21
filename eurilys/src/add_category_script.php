<?php
	session_start();
	ob_start();

	/* Configuring Server & Database */
	$host        =    'localhost';
	$user        =    'root';
	$password    =    '';
	$database    =    'progin_405_13510086';

	$con        =    mysql_connect($host,$user,$password) or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');

	if (isset($_SESSION['username'])) {
		$username = $_SESSION['username']; 
		echo $username;
	}
	
	/* Add Category Script */	
	$catName= mysql_real_escape_string($_POST['add_category_name']);
	$catAsigneeName = $_POST['add_category_asignee_name'];
	if (isset($_POST['add_category_button'])) { //when login button is pressed
		$query 	= "INSERT INTO `category`(`cat_name`, `cat_creator`) VALUES ('$catName','$username')";
		$result	= mysql_query($query);
		
		/*
		$row = mysql_fetch_array($res, MYSQL_NUM);
		$name = $row[2];

		if (mysql_num_rows($res) > 0) {
			$_SESSION['username'] = $username;
			$_SESSION['fullname'] = $name;
			header('location:src/dashboard.php'); //redirect to dashboard
		} else {

			echo "Your log in credetial is not valid";
		} */
		//echo "berhasil add";
		header('location:src/dashboard.php');
	}



?>

