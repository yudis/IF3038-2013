<?php
	require "config.php";
	//require "index.php";
	
	$usr=$_GET["usr"];
	$psw=$_GET["psw"];
	
	$num_row_query = "SELECT * FROM user WHERE username='$usr' AND password='$psw'";
	
	$data = mysqli_query($con, $num_row_query);
	$result = mysqli_fetch_array($data);
	$row_cnt = mysqli_num_rows($data);
	
	if($row_cnt > 0){
		//SET SESSION
		session_start();
		$_SESSION['username'] = $result['username'];
		$_SESSION['fullname'] = $result['fullname'];
		$_SESSION['birthday'] = $result['birthday'];
		$_SESSION['password'] = $result['password'];
		$_SESSION['email'] = $result['email'];
		$_SESSION['IsEdit'] = false;
		//SET COOKIES, EXPIRED 30 DAYS
		$expire=time()+60*60*24*30;
		setcookie("username", $result['username'], $expire);
		setcookie("fullname", $result['fullname'], $expire);
		setcookie("birthday", $result['birthday'], $expire);
		setcookie("password", $result['password'], $expire);
		setcookie("email", $result['email'], $expire);
	}
	/* close connection */
	//$mysqli->close();
	echo $row_cnt;
?>