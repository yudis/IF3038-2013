<?php
	$server = "localhost";
	$sql_id = "progin";
	$sql_pass = "progin";
	$sql_database = "progin";
		
	$sql = mysql_connect($server, $sql_id, $sql_pass);
	
	mysql_select_db($sql_database, $sql);
?>