<?php
	require_once 'config.php';
	$post = $_POST;
	var_dump($post);
	$comment_id = $post['comment_id'];
	$hasil = queryn('DELETE from comment where comment_id = :comment_id',array(
		'comment_id' => $comment_id
		));
?>