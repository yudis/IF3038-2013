<?php
	include "login.php";
	require "config.php";
	$nameCat = $_GET['q'];
	$sql = "SELECT id_cat FROM category WHERE name = '$nameCat'";
	$user = mysqli_query($con,$sql);
	$data = mysqli_fetch_array($user);
	$idCat = $data['id_cat'];
	
	$sql2 = "SELECT id_task FROM task WHERE id_cat = '$idCat'";
	$user2 = mysqli_query($con,$sql2);
	while(($user2 != null) && ($data2 = mysqli_fetch_array($user2)))
	{
		$idTask = $data2['id_task'];
		$sqla = "DELETE FROM assignee WHERE id_task='$idTask'";
		$sqlb = "DELETE FROM comment WHERE id_task='$idTask'";
		$sqlc = "DELETE FROM task WHERE id_task='$idTask'";
		$sqld = "DELETE FROM taskattachment WHERE id_task='$idTask'";
		$sqle = "DELETE FROM tasktag WHERE id_task='$idTask'";

		mysqli_query($con,$sqla);
		mysqli_query($con,$sqlb);
		mysqli_query($con,$sqlc);
		mysqli_query($con,$sqld);
		mysqli_query($con,$sqle);
	}
	
	$sql3 = "DELETE FROM category WHERE id_cat = '$idCat'";
	$sql4 = "DELETE FROM joincategory WHERE id_cat = '$idCat'";
	$sql5 = "DELETE FROM categorycreator WHERE id_cat = '$idCat'";
	mysqli_query($con,$sql3);
	mysqli_query($con,$sql4);
	mysqli_query($con,$sql5);
	
	echo $nameCat;
?>