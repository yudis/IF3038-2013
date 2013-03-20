<?php
	$u = $_GET['u'];
	$p = $_GET['p'];
	
	require 'connectDB.php';
	
	$query = "select username from login where username = '" . $u . "' and password = '" . $p . "'";
	$result = mysql_query($query);
	$row = mysql_fetch_array($result);
	echo $row['username'];
	mysql_close($con);
?>