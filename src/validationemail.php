<?php
	require_once("database.php");
	$con = connectDatabase();
	
	$email = $_GET['email'];
	
	$result = mysqli_query($con,"SELECT `email` FROM `user`
			WHERE email='$email'");
	$row = mysqli_fetch_array($result);
	if ($row['email'] == $email) {
		echo "Email telah digunakan";
	} else {
		echo "Email OK";
	}
?>