<?php
	include "login.php";
	require "config.php";
	$idTask = $_GET['q'];
	
	$sql1 = "DELETE FROM assignee WHERE id_task='$idTask'";
	$sql2 = "DELETE FROM comment WHERE id_task='$idTask'";
	$sql3 = "DELETE FROM task WHERE id_task='$idTask'";
	$sql4 = "DELETE FROM taskattachment WHERE id_task='$idTask'";
	$sql5 = "DELETE FROM tasktag WHERE id_task='$idTask'";
	
	mysqli_query($con,$sql1);
	mysqli_query($con,$sql2);
	mysqli_query($con,$sql3);
	mysqli_query($con,$sql4);
	mysqli_query($con,$sql5);
	
	echo "deleted";
?>