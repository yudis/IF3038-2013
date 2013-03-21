<?php
	require_once 'config.php';
	$post = $_POST;
	var_dump($post);
	$task_id = $post['task_id'];
	$hasil = queryn('UPDATE task set done = not done WHERE task_id = :task_id',array('task_id' => $task_id));
	echo $task_id;
?>