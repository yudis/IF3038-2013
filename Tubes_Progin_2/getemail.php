<?php
	require "config.php";
	session_start();
	$getemail_sql = "SELECT username FROM user WHERE email = 'nugroho.satrijandi@gmail.co'";
	if ($getemail_result = mysqli_query($con, $getemail_sql)) {
		/* determine number of rows result set */
		$row_cnt = mysqli_num_rows($getemail_result);
	}
	echo $row_cnt;
?>