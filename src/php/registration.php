<?php 
	require('init_function.php');
	$username = $_POST['textUsername'];
	$password = $_POST['textPassword'];
	$fullname = $_POST['textFullName'];
	$email = $_POST['textEmail'];
	
	$con = getConnection();
	 
	// Process Query
	$query = "INSERT INTO user VALUES ('$username', '$password','$fullname', '1991-02-20','$email')";
	mysqli_query($con,$query);
	mysqli_close($con);
	
	// Process File
	$file = $_FILES['textAvatar'];
	upload_file($file,$username);
	header("Location: ../page/dashboard.php");
?>