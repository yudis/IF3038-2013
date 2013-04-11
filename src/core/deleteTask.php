<?php
	require_once 'config.php';
	$post = $_POST;
	var_dump($post);
	$task_id = $post['task_id'];
	delTask($task_id);
?>