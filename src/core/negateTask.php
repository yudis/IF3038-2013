<?php
	require_once 'config.php';
	$post = $_POST;
	$task_id = $post['task_id'];
	queryn('UPDATE task set done = not done WHERE task_id = :task_id',array('task_id' => $task_id));
?>