<?php
	session_start();
	//session_destroy();
	ob_start();

	/* Configuring Server & Database */
	$host        =    'localhost';
	$user        =    'root';
	$password    =    '';
	$database    =    'progin_405_13510086';
	$ret		 = 1;

	$con        =    mysql_connect($host,$user,$password) or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');

	$username = $_SESSION['username']; 

	
	if (isset($_POST['edit_profile_submit'])) { //when submit button button is pressed

		//process full name
		if($_POST['fullname'] !== null) {
			//edit user's full name
			$fullname = mysql_real_escape_string($_POST['fullname']);
			$query = "UPDATE user SET full_name='$fullname' WHERE username='$username'";
			if (mysql_query($query)) {
				mysql_query($query);
			} else {
				$ret = 0;
			}
		}

		//process birthdate
		if($_POST['birthdate'] !== null) {
			//edit user's birthdate
			$birthdate = mysql_real_escape_string($_POST['birthdate']);
			$query = "UPDATE user SET birthdate='$birthdate' WHERE username='$username'";
			if (mysql_query($query)) {
				//mysql_query($query);				
			} else {
				$ret = 0;
			}
		}

		/*//process avatar
		if($_POST['avatar'] !== null) {
			//edit user's avatar
			$avatar = mysql_real_escape_string($_POST['avatar']);
			$query = "UPDATE user SET avatar='$avatar' WHERE username='$username'";
			if (mysql_query($query)) {
				mysql_query($query);				
			} else {
				$ret = 0;
			}
		}*/

		if($_POST['password'] !== "") {
			//edit user's password
			//checking password confirmation is not necessary as it's done in javascript
			$password = mysql_real_escape_string($_POST['password']);
			$query = "UPDATE user SET password='$password' WHERE username='$username'";
			if (mysql_query($query)) {
				mysql_query($query);				
			} else {
				$ret = 0;
			}
		}
	
		
			

	}




?>