<?php
	require "config.php";
	session_start();
	$username = "monicarembang";
	$fullname = "monica riany aja";
	$dob = "10/11/1992";
	$password = md5("mautauaja");
	$email = "monicarembang@gmail.com";
	move_uploaded_file($_FILES["regfile"]["tmp_name"],"img/".$_FILES["regfile"]["name"]);
	$target = "img/"."EndyDoankavatar.docx"; //session username sbg "target"avatar.docx
	$insert_sql = "INSERT INTO `user`(`username`, `fullname`, `avatar`, `birthday`, `email`, `password`) VALUES ('$username', '$fullname', '$target', '$dob', '$email', '$password')";
	
	mysqli_query($con,$insert_sql);
	header('Location: dashboard.php')
?>