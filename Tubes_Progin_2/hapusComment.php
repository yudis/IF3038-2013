<?php
	//include "login.php";
	require "config.php";
	
	$id_comment = $_GET['q'];
	
	$sql_del = "DELETE FROM comment WHERE id_comment='$id_comment'";
	mysqli_query($con,$sql_del);
	
	echo "deleted";
?>