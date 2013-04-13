<?php
	session_destroy();
	session_start();
	$_SESSION["infologin"] = "";
	require_once("database.php");
	$con = connectDatabase();
	
	$user = $_POST['userid'];
	$pass = $_POST['pass'];
        session_destroy();
	session_start();
	
	$resultuser = mysqli_query($con,"SELECT * FROM `user` WHERE username='$user'");
	$rowuser = mysqli_fetch_array($resultuser);

	$rowpassword = mysqli_fetch_array($resultpass);

	if(($rowuser['username'] == $user) && ($rowuser['password'] == $pass)){
		$_SESSION['login'] = $user;
		$_SESSION["infologin"] = "";
                ini_set("session.cookie_lifetime","2592000"); 
		header("Location:dashboard.php");
	} else {
		header("Location:index.php");
		$_SESSION["infologin"] = "login gagal, silahkan ulangi";
	}
?>