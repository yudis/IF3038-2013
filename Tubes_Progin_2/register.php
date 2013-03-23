<?php
	require "config.php";
	require "index.php";
	session_start();
	$username = $_POST['regusername'];
	$fullname = $_POST['regname'];
	$dob = $_POST['regdate'];
	$password = md5($_POST['regpassword1']);
	$email = $_POST['regemail'];
	$target = "img/".$_FILES["regfile"]["name"];
	if(!empty($_FILES['taskatt']["name"])){
		move_uploaded_file($_FILES["regfile"]["tmp_name"],"img/".$_FILES["regfile"]["name"]);
	}	
	$insert_sql = "INSERT INTO `user`(`username`, `fullname`, `avatar`, `birthday`, `email`, `password`) VALUES ('$username','$fullname', '$target', '$dob', '$email', '$password')";
	mysqli_query($con,$insert_sql);
	$_SESSION['username'] = $username;
	$_SESSION['fullname'] = $fullname;
	$_SESSION['birthday'] = $dob;
	$_SESSION['password'] = $password;
	$_SESSION['email'] = $email;
	header('Location: dashboard.php');
?>