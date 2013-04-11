<?php
	require_once 'config.php';
	$get = $_GET;
	$task_id = $get['task_id'];
	$hasil = query('select count(*) from comment WHERE task_id = :task_id',array('task_id' => $task_id));
	echo $hasil['count(*)'];
?>