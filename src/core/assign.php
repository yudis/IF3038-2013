<?php
	require_once 'config.php';
	$post = $_POST;
	var_dump($post);
	$task_id = isset($post['task_id'])?$post['task_id']:null;
	$category_id = isset($post['category_id'])?$post['category_id']:null;
	$name = $post['name'];
	switch($post['type']) {
	case 'delete':
		delAssignee($name,$task_id,$category_id);
		break;
	case 'add':
		addAssignee($name,$task_id,$category_id);
		break;
	}
?>