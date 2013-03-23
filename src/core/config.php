<?php
session_start();

function getUserID() {
	return (isset($_SESSION["user_id"])?$_SESSION["user_id"]:0);
}

function findById($table,$id) {
	return query('select * from '.$table.' where '.$table.'_id = :id',array('id' => $id));
}

function getUserName($user_id) {
	$row = findById('user',$user_id);
	return $row['name'];
}

function getUserUsername($user_id) {
	$row = findById('user',$user_id);
	return $row['username'];
}

function getUserBirthdate($user_id) {
	$row = findById('user',$user_id);
	return $row['birthdate'];
}

function getUserFullname($user_id) {
	$row = findById('user',$user_id);
	return $row['name'];
}

function getUserEmail($user_id) {
	$row = findById('user',$user_id);
	return $row['email'];
}

function getTagName($tag_id) {
	$row = findById('tag',$tag_id);
	return $row['name'];
}

function query($statement,$cond) {
	$conn = Db::handler();
	$stmt = $conn->prepare($statement);
	$stmt->execute($cond);
	return $stmt->fetch();
}

function queryn($statement,$cond) {
	$conn = Db::handler();
	$stmt = $conn->prepare($statement);
	$stmt->execute($cond);
}

function querynid($statement,$cond) {
	$conn = Db::handler();
	$stmt = $conn->prepare($statement);
	$stmt->execute($cond);
	return $conn->lastInsertId();
}

function queryAll($statement,$cond) {
	$conn = Db::handler();
	$stmt = $conn->prepare($statement);
	$stmt->execute($cond);
	return $stmt->fetchAll();
}

function addAssignee($name,$task_id,$category_id) {
	$user_id = query('select user_id from user where name = :name',array('name' => $name));
	if ($user_id) {
		queryn('INSERT into assign (task_id,category_id,user_id) values (:task_id,:category_id,:user_id)',array(
		'task_id' => $task_id,
		'category_id' => $category_id,
		'user_id' => $user_id['user_id']
		));
	}
}

function addTag($task_id,$name) {
	$tag = query('select * from tag where name = :name',array('name' => $name));
	if (!$tag)
		$tag['tag_id'] = querynid('insert into tag (name) values (:name)',array('name' => $name));
	$exist = query('select * from tags where tag_id = :tag_id and task_id = :task_id',array('tag_id' => $tag['tag_id'], 'task_id' => $task_id));
	if (!$exist)
		queryn('insert into tags (tag_id,task_id) values (:tag_id,:task_id)',array('tag_id' => $tag['tag_id'], 'task_id' => $task_id));
}

function findAssignee($name,$task_id,$category_id) {
	$user_id = query('select user_id from user where name = :name',array('name' => $name));
	if ($user_id) {
		$assign = ($task_id)?(query('select * from assign where task_id = :task_id and user_id = :user_id',array(
		'task_id' => $task_id,
		'user_id' => $user_id['user_id']
		))):(query('select * from assign where category_id = :category_id and user_id = :user_id',array(
		'category_id' => $category_id,
		'user_id' => $user_id['user_id']
		)));
		return $assign['id'];
	} else
		return false;
}

function delAssignee($name,$task_id,$category_id) {
	$id = findAssignee($name,$task_id,$category_id);
	if ($id)
		queryn('delete from assign where id = :id',array(
		'id' => $id
		));
}

function delTask($task_id) {
	queryn('DELETE from tags where task_id = :task_id',array(
		'task_id' => $task_id
		));
	queryn('DELETE from comment where task_id = :task_id',array(
		'task_id' => $task_id
		));
	queryn('DELETE from assign where task_id = :task_id',array(
		'task_id' => $task_id
		));
	$attachments = queryAll('select * from attachment where task_id = :task_id',array(
		'task_id' => $task_id
		));
	foreach ($attachments as $attachment) {
		unlink('../attachment/'.$attachment['filename']);
	}
	queryn('DELETE from attachment where task_id = :task_id',array(
		'task_id' => $task_id
		));
	queryn('DELETE from task where task_id = :task_id',array(
		'task_id' => $task_id
		));
}

function delCategory($category_id) {
	$tasks = queryAll('select task_id from task where category_id = :category_id',array(
		'category_id' => $category_id
		));
	foreach ($tasks as $task) {
		delTask($task['task_id']);
	}
	queryn('DELETE from assign where category_id = :category_id',array(
		'category_id' => $category_id
		));
	queryn('DELETE from category where category_id = :category_id',array(
		'category_id' => $category_id
		));
}

class Db
{
	private static $db;
	
	public static function handler()
	{
		if (!self::$db)
		{
			try {
				self::$db = new PDO('mysql:host=localhost;dbname=progin_405_13510001', 'progin', 'progin');
				self::$db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
				self::$db->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);
			} catch (PDOException $e) {
				die('Connection error: ' . $e->getMessage());
			}
		}
		return self::$db;
	}
}

?>