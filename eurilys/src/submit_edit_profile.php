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

	$username = $_SESSION['username']; 

	
	if (isset($_POST['edit_profile_submit'])) { //when submit button button is pressed

		

		$fullname = mysql_real_escape_string($_POST['fullname']);
		$birthdate = mysql_real_escape_string($_POST['birthdate']);
		$avatar = mysql_real_escape_string($_POST['avatar']);
		$password = mysql_real_escape_string($_POST['password']);	
		$passwordconf = mysql_real_escape_string($_POST['password_confirm']);	


		if ($password == $passwordconf) {

		}
		
		
		$query = "UPDATE user SET column1=value, column2=value2,... WHERE username='$username'"

		}

	}



?>