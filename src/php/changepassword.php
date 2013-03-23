<?php
	require('init_function.php');	
	$newpass = $_GET['newpass'];
	$userid = $_GET['userid'];
	
	$con = getConnection();
	$query = "UPDATE user SET password = '$newpass' WHERE username='$userid'";
	mysqli_query($con,$query);
?>