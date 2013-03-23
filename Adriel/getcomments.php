<?php
	$id = $_GET["id"];
	$user = $_GET["user"];
	
	$con = mysqli_connect('localhost', 'progin', 'progin');
	if (!$con)
	{
		die('Could not connect: ' . mysqli_error());
	}
	
	mysqli_select_db("progin_405_13510035", $con);
	
	$sql = "SELECT * FROM comment WHERE commented_task = '".$id."' ORDER BY writing_time";
	$result = mysqli_query($sql);
	while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC))
	{
		echo "<img src='avatar/".$user.".jpg' width='50' height='50' alt='Image not found'/>";
		echo "<b>";
		echo $row['writer'];
		echo "</b> - ";
		echo $row['writing_time'];
		echo " <button onclick='deletekomentar(\"".$row['comment_id']."\")'>Delete Comment</button>";
		echo "<hr/>";
		echo $row['comment'];
		echo "<br /><br />";
	}
	
	mysqli_close($con);
?>