<?php
	include "login.php";
	$username = $_SESSION['username'];
	$id_task = $_GET['q'];
	
	require "config.php";
	$result = "";
	
	//ALL IN DUMMY HERE
	$sql_assignee = "SELECT * FROM assignee WHERE id_task = '$id_task'";
	$assignee = mysqli_query($con,$sql_assignee) or die("Error: ".mysqli_error($con));
	
	while(($assignee != null) && ($assignee_fetched = mysqli_fetch_array($assignee))){
		$result .= $assignee_fetched['username'].",";
	}
	
	echo $result;
?>