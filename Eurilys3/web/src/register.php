<?php
	include "connect.php";
	$file_name = $_FILES['avatar_upload']['name'];
	$file_name = stripslashes($file_name);
	$file_name = str_replace("'","",$file_name);
	$copy = copy
	(
		$_FILES['avatar_upload']['tmp_name'],"img/avatar/".$file_name
	);
	
	
	$query = "INSERT INTO user (username,password,name,email,birthdate,avatar)
            VALUES(
                '".$_POST['username']."'
                ,'".$_POST['password']."'
                ,'".$_POST['long_name']."'
                ,'".$_POST['email']."'
				,'".$_POST['birth_date']."'
				,'img/avatar/".$file_name."'
                );";
	mysql_query($query);
	
	$expire=time()+60*60;
	setcookie("name", $_POST['username'], $expire);
	setcookie("pass", $_POST['password'], $expire);
	header("Location: index.php");
?>