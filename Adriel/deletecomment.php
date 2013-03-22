<?php
	$id = $_GET["id"];
	
	$con = mysqli_connect('localhost', 'progin', 'progin');
	if (!$con)
	{
		die('Could not connect: ' . mysqli_error());
	}
	
	mysqli_select_db("progin_405_13510035", $con);
	
	$sql = "DELETE FROM comment WHERE comment_id = '".$id."')";
	mysqli_query($sql);
	
	mysqli_close($con);
?>