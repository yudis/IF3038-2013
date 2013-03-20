<?php
	$u = $_GET['u'];
	$p = $_GET['p'];
	
	$host = 'localhost';
	$user = 'progin';
	$pass = 'progin';
	$db = 'progin';
	
	$con = mysql_connect($host, $user, $pass);
	if(!$con) die("Could not connect: " . mysql_error());
	
	mysql_select_db($db, $con);
	$query = "select username from login where username = '" . $u . "' and password = '" . $p . "'";
	$result = mysql_query($query);
	$row = mysql_fetch_array($result);
	echo $row['username'];
	mysql_close($con)
?>