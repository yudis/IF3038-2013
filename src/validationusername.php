<?php
	require_once("database.php");
	$con = connectDatabase();
	
	$user = $_GET['username'];
	
	$result = mysqli_query($con,"SELECT `username` FROM `user`
			WHERE username='$user'");
	$row = mysqli_fetch_array($result);
	if ($row['username'] == $user) {
		echo "Username telah digunakan";
	} else {
		echo "Username OK";
	}
?>