<?php
	// connection variable
	$db_host = '127.0.0.1';
	$db_user = 'root';
	$db_pwd = '';
	$database = 'progin_405_13510010';
	// connection process
	if (!mysql_connect($db_host, $db_user, $db_pwd))
		die("Can't connect to database");

	if (!mysql_select_db($database))
		die("Can't select database");				
?>