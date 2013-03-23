<?php
	include "login.php";
	$username = $_SESSION['username'];
	$id_task = $_GET['q'];
	require "config.php";
	$result = "";
	
	$sql_tags = "SELECT * FROM tag WHERE id_tag in (SELECT id_tag FROM tasktag WHERE id_task = '$id_task')";
	$tags = mysqli_query($con,$sql_tags) or die("Error: ".mysqli_error($con));
	
	while(($tags != null) && ($tags_fetched = mysqli_fetch_array($tags))){
		$result .= $tags_fetched['name'].",";
	}
	
	echo $result;
?>