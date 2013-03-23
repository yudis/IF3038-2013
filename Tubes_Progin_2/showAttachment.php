<?php
	include "login.php";
	$username = $_SESSION['username'];
	$id_task = $_GET['q'];
	
	require "config.php";
	$result = "";
	
	//ALL IN DUMMY HERE
	$sql_attachment = "SELECT * FROM attachment WHERE id_attachment in (SELECT id_attachment FROM taskattachment WHERE id_task = '$id_task')";
	$attachment = mysqli_query($con,$sql_attachment) or die("Error: ".mysqli_error($con));
	
	while(($attachment != null) && ($attachment_fetched = mysqli_fetch_array($attachment))){
		$result .= $attachment_fetched['path'].",";
	}
	
	echo $result;
?>