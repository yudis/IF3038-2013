<?php
	require_once 'config.php';
	$post = $_POST;
	var_dump($post);
	$category_id = $post['category_id'];
	delCategory($category_id);
?>