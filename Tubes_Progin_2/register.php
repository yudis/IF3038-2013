<?php
	require "config.php";
	session_start();
	$username = $_POST['regusername'];
	$fullname = $_POST['regname'];
	$dob = $_POST['regdate'];
	$password = $_POST['regpassword1'];
	$email = $_POST['regemail'];
	$target = "img/".$username.$_FILES["regfile"]["name"];
	move_uploaded_file($_FILES["regfile"]["tmp_name"],$target);
	$insert_sql = "INSERT INTO `user`(`username`, `fullname`, `avatar`, `birthday`, `email`, `password`) VALUES ('$username','$fullname', '$target', '$dob', '$email', '$password')";
	mysqli_query($con,$insert_sql);
	$_SESSION['username'] = $username;
	$_SESSION['fullname'] = $fullname;
	$_SESSION['birthday'] = $dob;
	$_SESSION['password'] = $password;
	$_SESSION['email'] = $email;
	$_SESSION['IsEdit'] = false;
	//SET COOKIES, EXPIRED 30 DAYS
		$expire=time()+60*60*24*30;
		setcookie("username", $result['username'], $expire);
		setcookie("fullname", $result['fullname'], $expire);
		setcookie("birthday", $result['birthday'], $expire);
		setcookie("password", $result['password'], $expire);
		setcookie("email", $result['email'], $expire);
	header('Location: dashboard.php');
?>