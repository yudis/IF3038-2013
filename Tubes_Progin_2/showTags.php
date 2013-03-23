<?php
	//include "login.php";
	//$username = $_SESSION['username'];
	
	require "config.php";
	$result = "";
	
	//ALL IN DUMMY HERE
	$sql_tags = "SELECT * FROM tag WHERE id_tag in (SELECT id_tag FROM tasktag WHERE id_task = '1')";
	$tags = mysqli_query($con,$sql_tags) or die("Error: ".mysqli_error($con));
	
	while(($tags != null) && ($tags_fetched = mysqli_fetch_array($tags))){
		$result .= $tags_fetched['name'].",";
	}
	
	echo $result;
?>