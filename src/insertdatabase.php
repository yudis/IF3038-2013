<?php
	require_once("database.php");
	$con = connectDatabase();
	
	$user = $_POST["username"];
	$pass = $_POST["password"];
	$fullname = $_POST["fullname"];
	$email = $_POST["email"];
	$birth = $_POST["birth"];
	$avatar = $_FILES["foto"]["name"];
	
	mysqli_query($con,"INSERT INTO `user`(`username`, `email`, `fullname`, `avatar`, `tanggalLahir`, `password`)
		VALUES ('$user','$email','$fullname','$avatar','$birth','$pass')");
		session_start();
		$_SESSION["login"] = $user;
	header("Location:dashboard.php");
?>