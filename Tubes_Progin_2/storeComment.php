<?php
	//include "login.php";
	//$username = $_SESSION['username'];
	$q = $_GET['q'];
	$tanggal = getdate();
	$current_date = "";
	$current_date .= $tanggal['hours']." : ".$tanggal['minutes']." - ".$tanggal['mday']."/".$tanggal['mon'];
	
	require "config.php";
	$result = "";
	
	$sql_user = "SELECT pemilik FROM task WHERE id_task='1'";
	$user = mysqli_query($con,$sql_user)or die("Error: ".mysqli_error($con));
	$user_fetched = mysqli_fetch_array($user);
	$pemilik = $user_fetched['pemilik'];
	
	//ALL IN DUMMY HERE
	if($update_comment = "INSERT INTO `comment`(`id_task`, `username`, `time`, `content`) VALUES ('1','$pemilik','$current_date','$q')"){
		mysqli_query($con,$update_comment);
		echo "";
	}else{
		echo "a";
	}
?>