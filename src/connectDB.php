<?php
	$host = 'localhost';
	$user = 'progin';
	$pass = 'progin';
	$db = 'progin';
	
	$con = mysql_connect($host, $user, $pass);
	if(!$con) die("Could not connect: " . mysql_error());
	
	mysql_select_db($db, $con);
?>