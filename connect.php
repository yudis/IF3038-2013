<?php
	global $connect;
	//$username="progin";
	//$host="localhost";
	//$password="progin";
	$connect=mysql_connect("localhost","progin", "progin", "progin_439_13510002") or die(mysql_error());
	if(!$connect){
		die("Could not reach $host.");
	}
	$db_name="progin_439_13510002";
	$db_select=mysql_select_db($db_name,$connect);
	if(!$db_select){
		die("Could not reach database $db_name.");
	}
	return $connect;
?>