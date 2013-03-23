<?php
	$username = $_GET['username'];
	$time = $_GET['time'];
	require 'connectDB.php';
	
	$query = "delete from comments where username = " . $username . " and time = " . $time;
	$result = mysql_query($query);
	mysql_close($con);
?>