<?php
	$id = $_GET["id"];
	$user = $_GET["user"];
	
	$con = mysqli_connect('localhost', 'progin', 'progin');
	if (!$con)
	{
		die('Could not connect: ' . mysqli_error());
	}
	
	mysqli_select_db("progin_405_13510035", $con);
	
	$sql = "SELECT * FROM comment WHERE commented_task = '".$id."'";
	$result = mysqli_query($sql);
	while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC))
	{
		echo "<b>";
		echo $row['writer'];
		echo "</b> - ";
		echo $row['writing_time'];
		echo "<hr/>";
		echo $row['comment'];
		echo "<br /><br />";
	}
	
	mysqli_close($con);
?>