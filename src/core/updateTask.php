<?php
	require_once 'config.php';
	$post = $_POST;
	var_dump($post);
	$task_id = $post['task_id'];
	$mode = $post['mode'];
	$value = $post['value'];
	switch ($mode) {
	case 'deadline':
		queryn('UPDATE task set deadline = :deadline WHERE task_id = :task_id',array('task_id' => $task_id, 'deadline' => $value));
		break;
	case 'addassignee':
		addAssignee($value,$task_id,null);
		break;
	case 'addtag':
		$tag = query('select * from tag where name = :name',array('name' => $value));
		if (!$tag)
			$tag['tag_id'] = querynid('insert into tag (name) values (:name)',array('name' => $value));
		var_dump($tag);
		$exist = query('select * from tags where tag_id = :tag_id',array('tag_id' => $tag['tag_id']));
		var_dump($exist);
		if (!$exist)
			queryn('insert into tags (tag_id,task_id) values (:tag_id,:task_id)',array('tag_id' => $tag['tag_id'], 'task_id' => $task_id));
		break;
	case 'delassignee':
		queryn('delete from assign where id = :id',array('id' => $value));
		break;
	case 'deltag':
		queryn('delete from tags where id = :id',array('id' => $value));
		break;
	}
?>