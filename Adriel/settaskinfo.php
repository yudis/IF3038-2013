<?php
	$id = $_GET["id"];
	$status = $_GET["status"];
	$deadline = $_GET["deadline"];
	$assignee = $_GET["assignee"];
	$tags = $_GET["tags"];
	
	$con = mysqli_connect('localhost', 'progin', 'progin');
	if (!$con)
	{
		die('Could not connect: ' . mysqli_error());
	}
	
	mysqli_select_db("progin_405_13510035", $con);
	
	$sql = "UPDATE task SET status=".$status." WHERE task_id = '".$id."'";
	mysqli_query($sql);
	
	$sql = "UPDATE task SET deadline='".$deadline."' WHERE task_id = '".$id."'";
	mysqli_query($sql);
	
	$arrAss = split("\,", $assignee);
	for ($i = 0; $i < count($arrAss); $i++)
	{
		$sql = "SELECT people_incharge_task FROM task_incharge WHERE task_id = '".$id."' AND people_incharge_task = '".$arrAss[$i]."'";
		$result = mysqli_query($sql);
		if (mysqli_num_rows($result) == 0)
		{
			$sql = "INSERT INTO task_incharge (task_id, people_incharge_task) VALUES ('".$id."', '".$arrAss[$i]."')";
			mysqli_query($sql);
		}
	}
	
	$arrTag = split("\,", $tags);
	for ($i = 0; $i < count($arrAss); $i++)
	{
		$sql = "SELECT tag_name FROM tasktag, tag WHERE task_id = '".$id."' AND tasktag.tag_id = tag.tag_id AND tag_name = '".$arrTag[$i]."'";
		$result = mysqli_query($sql);
		if (mysqli_num_rows($result) == 0)
		{
			$sql = "INSERT INTO tag (tag_id, tag_name) VALUES ('".$id.$i."', '".$arrTag[$i]."')";
			mysqli_query($sql);
			$sql = "INSERT INTO tasktag (task_id, tag_id) VALUES ('".$id."', '".$id.$i."')";
			mysqli_query($sql);
		}
	}
	
	mysqli_close($con);
?>