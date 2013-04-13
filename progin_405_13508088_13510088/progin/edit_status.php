<?php
	$con = mysql_connect("localhost","root","");
	if (!$con)
	  {
	  die('Could not connect: ' . mysql_error());
	  }
	mysql_select_db("tubes2", $con);

	$id_task = $_GET['idtask'];
	$resultstatus = mysql_query('SELECT status FROM tugas WHERE id = '. $id_task);
	$row = mysql_fetch_array($resultstatus);
	$status_this = $row['status'];
	echo "$status_this";
	if ($status_this == "1") {
		mysql_query($con,"UPDATE tugas SET status = 0 WHERE id = ". $id_task);
	} else {
		mysql_query($con,"UPDATE tugas SET status = 1 WHERE id = ". $id_task);
	}
	echo "$status_this";
	$sql = 'UPDATE tugas SET status = 0 WHERE id = '. $id_task;
	$retval = mysql_query( $sql, $con );


	//header('Location: http://localhost/progin/dashboard.php');
?>