<?php 
	require('init_function.php');	
	$categoryid = $_GET['id'];
	$con = getConnection();
	$query = "DELETE FROM category WHERE categoryid=$categoryid";
	mysqli_query($con,$query);
	mysqli_close($con);
	header("Location: ../page/dashboard.php");
?>