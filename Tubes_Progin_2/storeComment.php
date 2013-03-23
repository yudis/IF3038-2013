<?php
	include "login.php";
	$username = $_SESSION['username'];
	$q = $_GET['q'];
	$tanggal = getdate();
	$current_date = "";
	$current_date = $tanggal[hours]." : ".$tanggal[minutes]." - ".$tanggal[mday]."/".$tanggal[mon];
	
	require "config.php";
	$result = "";
	
	$sql_user = "SELECT pemilik FROM task WHERE id_task='1'";
	$user = mysqli_query($con,$sql_user)or die("Error: ".mysqli_error($con));
	$user_fetched = mysqli_fetched_array($user);
	
	$count_comment = 1;
	$sql_comment = "SELECT * FROM comment";
	$comment = mysqli_query($con,$sql_comment);
	while(($comment != null) && ($comment_fetched = $mysqli_fetch_array($comment))){
		$count_comment++;
	}
	
	//ALL IN DUMMY HERE
	$update_comment = "INSERT INTO `comment`(`id_comment`, `id_task`, `username`, `time`, `content`) VALUES ('$count_comment','1','$user_fetched','$current_date','$q')";
	mysqli_query($con,$update_comment);
	
	echo "";
?>