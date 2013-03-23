<?php
	$con = mysql_connect("localhost","root","");
	if (!$con)
	  {
	  die('Could not connect: ' . mysql_error());
	  }
	mysql_select_db("tubes2", $con);

	$id_task = $_GET['no_task'];
	$hapus_data = mysql_query("DELETE FROM tugas WHERE idtask='$id_task'");
?>