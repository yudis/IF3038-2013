<?php
	require_once 'config.php';
	$post = $_POST;
	var_dump($post);
	var_dump($_FILES["avatar"]);
	$user_id = $post['user_id'];
	$mode = $post['mode'];
	$value = $post['value'];
	switch ($mode) {
	case 'name':
		queryn('UPDATE user SET name = :name WHERE user_id = :user_id',array('user_id'=>$user_id, 'name' => $value));
		break;
	case 'tanggal_lahir':
		queryn('UPDATE user SET birthdate = :birthdate WHERE user_id = :user_id',array('user_id'=>$user_id, 'birthdate' => $value));
		break;
	case 'password':
		queryn('UPDATE user SET password = :password WHERE user_id = :user_id',array('user_id'=>$user_id, 'password' => $value));
		break;
	case 'avatar':
		move_uploaded_file($_FILES["avatar"]["tmp_name"],"avatar/" . $user_id . '.jpg');
	}	
?>