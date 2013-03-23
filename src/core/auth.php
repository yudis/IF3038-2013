<?php
	require_once 'config.php';
	$get = $_GET;
	$username = $get['username'];
	$password = $get['password'];
	$hasil = query('SELECT * FROM user WHERE username = :username and password = :password',array('username' => $username, 'password' => $password));
	if ($hasil) {
		$arr['success'] = true;
		$arr['user_id'] = (int)$hasil['user_id'];
		$_SESSION['user_id'] = (int)$hasil['user_id'];
	} else
		$arr['success'] = false;
	echo json_encode($arr);
?>