<?php
	require "config.php";
	
	$usr=$_GET["usr"];
	$psw=$_GET["psw"];
	
	$num_row_query = "SELECT * FROM user WHERE username='$usr' AND password='$psw'";
	
	if ($result = mysqli_query($con, $num_row_query)) {

		/* determine number of rows result set */
		$row_cnt = mysqli_num_rows($result);
		/* close result set */
		//$result->close();
	}
	
	
	/*if($row_cnt > 0){
		session_start();
	}*/
	/* close connection */
	//$mysqli->close();
	echo $row_cnt;
?>