<?php
	$username = $_GET["q"];
	require "config.php";
	session_start();
	//dummy here
	$row_cnt = 0;
	$getusername_sql = "SELECT username FROM user WHERE username = '$username'";
	if ($getuser_result = mysqli_query($con, $getusername_sql)) {
		/* determine number of rows result set */
		$row_cnt = mysqli_num_rows($getuser_result);
	}
	echo $row_cnt;
?>