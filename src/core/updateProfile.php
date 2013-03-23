<?php
	require_once 'config.php';
	$post = $_POST;
	var_dump($post);
	var_dump($_FILES["avatar"]);
	$user_id = getUserId();
	$name = (isset($post['name'])?$get['name']:0);
	if ($name) {
		$hasil = queryn('UPDATE user SET name = :name WHERE user_id = :user_id',array('name'=>$name ,'user_id'=>$user_id));
	}
	$birthdate = (isset($post['birthdate'])?$get['birthdate']:0);
	if ($birthdate) {
		$hasil = queryn('UPDATE user SET birthdate = :birthdate WHERE user_id = :user_id',array('birthdate'=>$birthdate ,'user_id'=>$user_id));
	}
	$password = (isset($post['password'])?$get['password']:0);
	if ($password) {
		$hasil = queryn('UPDATE user SET password = :password WHERE user_id = :user_id',array('password'=>$password ,'user_id'=>$user_id));
	}
	if (isset($_FILES["avatar"])) {
		move_uploaded_file($_FILES["avatar"]["tmp_name"],"../avatar/" . $user_id . '.jpg');
	}
?>