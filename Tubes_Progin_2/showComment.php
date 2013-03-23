<?php
	//include "login.php";
	//$username = $_SESSION['username'];
	
	require "config.php";
	$result = "";
	
	//ALL DUMMY HERE!
	$sql_comment = "SELECT * FROM comment WHERE id_task='1'";
	$comment = mysqli_query($con,$sql_comment) or die("Error: ".mysqli_error($con));
	
	while(($comment != null) && ($comment_fetched = mysqli_fetch_array($comment))){
		$cur_username = $comment_fetched['username'];
		
		$sql_avatar = "SELECT * FROM user WHERE username = '$cur_username'";
		$avatar = mysqli_query($con,$sql_avatar);
		$avatar_fetched = mysqli_fetch_array($avatar);
		
		$result .= "<br>".$avatar_fetched['avatar'].",".$comment_fetched['time'].",".$comment_fetched['content'].",".$comment_fetched['username'].",EndyDoank,".$comment_fetched['id_comment'];
	}
	
	echo $result;
?>