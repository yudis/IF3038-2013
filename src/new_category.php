<?php
	require_once 'core/config.php';
	$post = $_POST;
	$name = $post['name'];
	$user_id = $_SESSION['user_id'];
	$assignee = $post['assignee'];
	var_dump($post);
	$category_id = querynid('INSERT into category (name,user_id) values (:name,:user_id)',array(
		'name' => $name,
		'user_id' => $user_id
		));
	foreach($assignee as $assign) {
		addAssignee($assign,null,$category_id);
	}
	var_dump($category_id);
	header('Location: dashboard.php');
?>