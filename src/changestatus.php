<?php
	$taskname = $_GET['taskname'];
	$category = $_GET['category'];
	$newvalue = $_GET['newvalue'];
	require 'connectDB.php';
	
	$query = "update task set status = " . $newvalue . " where taskname = " . $taskname . " and category = " . $category;
	$result = mysql_query($query);
	mysql_close($con);
?>