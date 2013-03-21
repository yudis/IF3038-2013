<?php
	require_once 'config.php';
	$post = $_POST;
	var_dump($post);
	$task_id = $post['task_id'];
	$hasil = queryn('DELETE from task where task_id = :task_id',array(
		'task_id' => $task_id
		));
?>