<?php 
	//session_destroy();
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
	//$avatar		= $_FILE['signup_avatar_upload'];
	if(is_uploaded_file($_FILES["signup_avatar_upload"]["tmp_name"])) {
		$avatar = "../img/".$username.$_FILES["signup_avatar_upload"]["name"];
		$target = "img/".$username.$_FILES["signup_avatar_upload"]["name"];
		move_uploaded_file($_FILES["signup_avatar_upload"]["tmp_name"],$target);
	}
	
	if (isset($_POST['signup_submit'])) { //when button signup_submit is pressed
		$query1 = "SELECT * from user WHEN username='$username' OR email='$email'";
		$result1 = mysql_query($query1);
		
		if (mysql_num_rows($result1) > 0) {
			//username & email sudah ada
			header('location:src/index.php');
		}
		else {
			$query	=    
			"INSERT INTO user (`username`, `password`, `full_name`, `birthdate`, `avatar`, `email`) 
			VALUES ('$username','$password', '$name', '$birthdate', '$avatar', '$email')";
			
			$res	=    mysql_query($query);
			
			$_SESSION['username'] = $username;
			$_SESSION['fullname'] = $name;
			
			header('location:src/dashboard.php'); //Redirect To Success Page
		}
	}
?>