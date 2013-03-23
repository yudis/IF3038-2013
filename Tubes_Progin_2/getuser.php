<?php
	require "config.php";
	session_start();
	//dummy here
	$getusername_sql = "SELECT username FROM user WHERE username = 'apaaja'";
	if ($getuser_result = mysqli_query($con, $getusername_sql)) {
		/* determine number of rows result set */
		$row_cnt = mysqli_num_rows($getuser_result);
	}
	echo $row_cnt;
?>