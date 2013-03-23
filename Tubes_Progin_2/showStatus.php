<?php
	include "login.php";
	$id_task = $_GET["q"];
	//$username = $_SESSION['username'];
	
	require "config.php";
	$result = "";
	
	//PLEASE CHANGE EndyDoank with $username!!!!!!!!!!!!!!!!!
	$sql_status = "SELECT * FROM task WHERE id_task='$id_task'";
	$status = mysqli_query($con,$sql_status) or die("Error: ".mysqli_error($con));
	$status_fetched = mysqli_fetch_array($status);
	
	$result .= $status_fetched['id_task'].",".$status_fetched['status'];
	
	echo $result;
?>