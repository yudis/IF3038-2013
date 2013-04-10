<?php
	include "connect.php";
	$idTask = $_GET['idtask'];
	$user = $_COOKIE['name'];
	$deadline = $_GET['deadline'];
	
	$query = "UPDATE task
				 SET deadline='$deadline' 
				 WHERE idtask = '$idTask'";
				 
	mysql_query($query);
	
	$query = "DELETE FROM tag WHERE idtask = '$idTask'";
	mysql_query($query);
	
	$query = "DELETE FROM do WHERE idtask = '$idTask'";
	mysql_query($query);
	
	$arrayA = explode(",",$_GET['assignee']);
	for($i=0; $i<count($arrayA); $i++){
		$query = "INSERT INTO do (username, idtask)
				 VALUES(
					'".$arrayA[$i]."'
					,'".$idTask."'
				 );";
		mysql_query($query);
	}
	
	$arrayA = explode(",", $_GET['tag']);
	for($i=0; $i<count($arrayA); $i++){
		$query = "INSERT INTO tag (idtask, name)
				 VALUES(
					'".$idTask."'
					,'".$arrayA[$i]."'
				 );";
		mysql_query($query);
	}
?>