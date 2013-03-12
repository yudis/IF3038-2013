<?php 
	require('init_function.php');
	$username = $_POST['textUsername'];
	$password = $_POST['textPassword'];
	$fullname = $_POST['textFullName'];
	$birthday = $_POST['textBirthday'];
	
	$email = $_POST['textEmail'];
	
	$con = getConnection();
	 
	// Process Query
	$query = "INSERT INTO user VALUES ('$username', '$password','$fullname', '$birthday','$email')";
	mysqli_query($con,$query);
	mysqli_close($con);
	
	// Process File
	$file = $_FILES['textAvatar'];
	upload_file($file,$username);
	
	// Set Cookies & Session
	session_start();
    $_SESSION["userlistapp"]=$username;
	header("Location: ../page/dashboard.php");
?>