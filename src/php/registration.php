<?php 
	require('init_function.php');
	$username = $_POST['textUsername'];
	$password = $_POST['textPassword'];
	$fullname = $_POST['textFullName'];
	$birthday = $_POST['textBirthday'];
	$email = $_POST['textEmail'];
	$join = date('Y-m-d');
	$con = getConnection();
	 
	// Process Query
	$query = "INSERT INTO user VALUES ('$username', '$password','$fullname', '$birthday','$email','$join','')";
	mysqli_query($con,$query);
	mysqli_close($con);
	
	// Process File
	$file = $_FILES['textAvatar'];
	upload_avatar($file,$username);
	
	// Set Cookies & Session
	session_start();
    $_SESSION["userlistapp"]=$username;
	header("Location: ../page/dashboard.php");
?>