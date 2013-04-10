<?php
	include "connect.php";
	$idtask = $_GET['idtask'];
	$user = $_COOKIE['name'];
	
	$query = "UPDATE task
				 SET status='1'
				 WHERE idtask = '$idtask'";
				 
	mysql_query($query);
?>