<?php
	require "config.php";
	
	$usr=$_GET["usr"];
	$psw=$_GET["psw"];
	
	if ($result = $con->query("SELECT username, password FROM user WHERE username='$usr' AND password='$psw'")) {

		/* determine number of rows result set */
		$row_cnt = $result->num_rows;

		printf("Result set has %d rows.\n", $row_cnt);

		/* close result set */
		$result->close();
	}
	if($result>=0){
		echo "Success yay";
	}
	/* close connection */
	$mysqli->close();
	
?>