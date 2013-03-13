<?php 
	session_destroy();
	session_start();
	ob_start();
	
	/* Configuring Server & Database */
	$host        =    'localhost';
	$user        =    'root';
	$password    =    '';
	$database    =    'progin_405_13510086';

	$con        =    mysql_connect($host,$user,$password) or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');
	
	/* Registration Script */
	$username	= mysql_real_escape_string($_POST['signup_username']);
	$password    	= mysql_real_escape_string($_POST['signup_password']);
	//$password    =    md5($password);  // encrypt password
	$name		= mysql_real_escape_string($_POST['signup_long_name']);
	$email    	= $_POST['signup_email'];
	$birthdate    	= $_POST['signup_birth_date'];
	$avatar		= $_POST['signup_avatar_upload'];
	
	if (isset($_POST['signup_submit'])) { //when button signup_submit is pressed
		$query	=    
		"INSERT INTO user (`username`, `password`, `full_name`, `birthdate`, `avatar`, `email`) 
		VALUES ('$username','$password', '$name', '$birthdate', '$avatar', '$email')";
		
		$res	=    mysql_query($query);
		
		$_SESSION['username'] = $username;
		$_SESSION['fullname'] = $name;
		
		header('location:src/dashboard.php'); //Redirect To Success Page
	}
?>