<?php
	include "connect.php";
	$output="";
	$id ="";
	$result = mysql_query("SELECT * FROM user WHERE username ='".$_COOKIE['name']."'");
	$row = mysql_fetch_array($result);
	$output = $row['name']."|".$row['avatar']."|".$row['email']."|".$row['birthdate']."|".$row['username'];	
	echo $output;
?>