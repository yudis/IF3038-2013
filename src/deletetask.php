<?php
	$taskname = $_GET['taskname'];
	$category = $_GET['category'];
	require 'connectDB.php';
	
	$query = "delete from task where taskname = " . $taskname . " and category = " . $category;
	$result = mysql_query($query);
	mysql_close($con);
?>