<?php
	session_start();
	$u = $_GET['u'];
	$p = $_GET['p'];
	require 'connectDB.php';
	
	$query = "select username from login where username = '" . $u . "' and password = '" . $p . "'";
	$result = mysql_query($query);
	$row = mysql_fetch_array($result);
	if ($row)
	{
		// set the session
		$_SESSION['username'] = $row['username'];
	}
	
	echo $row['username'];
	mysql_close($con);
?>