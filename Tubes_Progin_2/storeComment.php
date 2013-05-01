<?php
	include "login.php";
	$username = $_SESSION['username'];
	$id_task = $_GET['id'];
	$q = $_GET['q']; //comment
	$tanggal = getdate();
	$current_date = "";
	$current_date .= ($tanggal['hours']-6)." : ".$tanggal['minutes']." - ".$tanggal['mday']."/".$tanggal['mon'];
	
	require "config.php";
	$result = "";
	
	$sql_user = "SELECT pemilik FROM task WHERE id_task='$id_task'";
	$user = mysqli_query($con,$sql_user)or die("Error: ".mysqli_error($con));
	$user_fetched = mysqli_fetch_array($user);
	$pemilik = $user_fetched['pemilik'];
	
	//ALL IN DUMMY HERE
	if($update_comment = "INSERT INTO `comment`(`id_task`, `username`, `time`, `content`) VALUES ('$id_task','$pemilik','$current_date','$q')"){
		mysqli_query($con,$update_comment);
		echo "";
	}else{
		echo "a";
	}
?>