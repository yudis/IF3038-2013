<?php
	$id = $_GET["id"];
	$type = $_GET["type"];
	
	$con = mysqli_connect('localhost', 'progin', 'progin');
	if (!$con)
	{
		die('Could not connect: ' . mysqli_error());
	}
	
	mysqli_select_db($con, "progin_405_13510035");
	
	if ($type == 1)
	{
		$sql = "SELECT comment_id FROM comment WHERE commented_task = '".$id."'";
		$result = mysqli_query($con, $sql);
		$row = mysqli_num_rows($result);
		echo $row;
	}
	
	mysqli_close($con);
?>