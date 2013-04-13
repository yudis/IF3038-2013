<?php
	include "connect.php";
	$idtask = $_GET['idtask'];
	$user = $_COOKIE['name'];
	
	$query = "DELETE FROM task
				 WHERE idtask = '$idtask'";
				 
	mysql_query($query);
	
	$query = "DELETE FROM do
				 WHERE idtask = '$idtask'";
				 
	mysql_query($query);
	
	$query = "DELETE FROM tag
				 WHERE idtask = '$idtask'";
				 
	mysql_query($query);
	
	$query = "DELETE FROM comment
				 WHERE idtask = '$idtask'";
				 
	mysql_query($query);
?>