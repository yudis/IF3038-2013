<?php
	$con = mysql_connect("localhost","root","");
	if (!$con)
	  {
	  die('Could not connect: ' . mysql_error());
	  }
	mysql_select_db("tubes2", $con);

	$id_task = $_GET['idtask'];
	if ($id_task == "1") $id_task == "0"
	else $id_task == "1";
?>