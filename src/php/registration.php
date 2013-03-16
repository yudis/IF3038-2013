<?php 
	require('init_function.php');
	$username = $_POST['textUsername'];
	$password = $_POST['textPassword'];
	$fullname = $_POST['textFullName'];
	$birthday = $_POST['textBirthday'];
	$email = $_POST['textEmail'];
	$join = date('Y-m-d');
	$con = getConnection();
	 
	 // Process File
	$file = $_FILES['textAvatar'];
	$filename = $file['name'];
	$extension = end(explode(".", $filename));
	$avatarname = $username.".".$extension;
	upload_avatar($file,$username);
	
	// Process Query
	$query = "INSERT INTO user VALUES ('$username', '$password','$fullname', '$birthday','$email','$join','telling yourself in here','$avatarname')";
	mysqli_query($con,$query);
	mysqli_close($con);
	
	// Set Cookies & Session
	session_start();
    $_SESSION["userlistapp"]=$username;
	header("Location: ../page/dashboard.php");
?>