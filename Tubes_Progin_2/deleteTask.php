<?php
	//include "login.php";
	require "config.php";
	$idTask = $_GET['q'];
	
	//AGAIN WITH DUMMY
	$sql_username = "SELECT * FROM task WHERE id_task='$idTask'";
	$username = mysqli_query($con,$sql_username);
	$cur_user = mysqli_fetch_array($username);
	
	if($cur_user[pemilik] == "EndyDoank"){
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
	}else{
		echo "failed";
	}
?>