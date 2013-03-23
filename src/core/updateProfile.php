<?php
	require_once 'config.php';
	$post = $_POST;
	var_dump($post);
	$user_id = $post['user_id'];
	$mode = $post['mode'];
	$value = $post['value'];
	switch ($mode) {
	case 'name':
		queryn('UPDATE user SET name = :name WHERE user_id = :user_id',array('user_id'=>$user_id, 'name' => $value));
		break;
	case 'tanggal_lahir':
		queryn('UPDATE user SET birthdate = :birthdate WHERE user_id = :user_id',array('user_id'=>$user_id, 'tanggal_lahir' => $value));
		break;
	case 'password':
		queryn('UPDATE user SET password = :password WHERE user_id = :user_id',array('user_id'=>$user_id, 'password' => $value));
		break;
	}	
?>