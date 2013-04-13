<?php
	include "connect.php";
	$category = $_GET['category'];
	$user = $_COOKIE['name'];
	
	$query = "DELETE FROM category
				 WHERE idcat = '$category'";
				 
	mysql_query($query);
	
	$query = "DELETE FROM able
				 WHERE idcat = '$category'";
	mysql_query($query);
	
	$query = "DELETE FROM task
				 WHERE idcat = '$category'";
	mysql_query($query);
?>