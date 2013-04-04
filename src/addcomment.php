<?php
	$id = $_GET["id"];
	$user = $_GET["user"];
	$date = $_GET["date"];
	$tugas = $_GET["tugas"];
	$comment = $_GET["comment"];
	
	$con = mysqli_connect('localhost', 'progin', 'progin');
	if (!$con)
	{
		die('Could not connect: ' . mysqli_error());
	}
	
	mysqli_select_db($con, "progin_405_13510029");
	
	$sql = "INSERT INTO comment (comment_id, commented_task, writer, writing_time, comment) VALUES ('".$id."', '".$tugas."', '".$user."', '".$date."', '".$comment."')";
	$result = mysqli_query($con, $sql);
	
	mysqli_close($con);	
?>