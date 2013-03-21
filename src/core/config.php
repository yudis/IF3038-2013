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