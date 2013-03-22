<?php
	$id = $_GET["id"];
	$type = $_GET["type"];
	
	$con = mysqli_connect('localhost', 'progin', 'progin');
	if (!$con)
	{
		die('Could not connect: ' . mysqli_error());
	}
	
	mysqli_select_db("progin_405_13510035", $con);
	
	if ($type == "taskname")
	{
		$sql="SELECT task_name FROM task WHERE task_id = '".$id."'";
		$result = mysqli_query($sql);
		$row = mysqli_fetch_array($result, MYSQLI_ASSOC);
		echo $row['task_name'];
	}
	else if ($type == "status")
	{
		$sql="SELECT status FROM task WHERE task_id = '".$id."'";
		$result = mysqli_query($sql);
		$row = mysqli_fetch_array($result, MYSQLI_ASSOC);
		echo $row['status'];
	}
	else if ($type == "attach")
	{
		$sql="SELECT link FROM attachment WHERE task_id = '".$id."'";
		$result = mysqli_query($sql);
		$returnvalue = "";
		while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC))
		{
			$returnvalue = $returnvalue.$row['link']." ";
		}
		echo $returnvalue;
	}
	else if ($type == "deadline")
	{
		$sql="SELECT deadline FROM task WHERE task_id = '".$id."'";
		$result = mysqli_query($sql);
		$row = mysqli_fetch_array($result, MYSQLI_ASSOC);
		echo $row['deadline'];
	}
	else if ($type == "assignee")
	{
		$sql="SELECT people_incharge_task FROM task_incharge WHERE task_id = '".$id."'";
		$result = mysqli_query($sql);
		$returnvalue = "";
		while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC))
		{
			$returnvalue = $returnvalue.$row['people_incharge_task'].",";
		}
		echo $returnvalue;
	}
	else if ($type == "tag")
	{
		$sql="SELECT tag_name FROM tag, tasktag WHERE tasktag.task_id = '".$id."' AND tag.tag_id = tasktag.tag_id";
		$result = mysqli_query($sql);
		$returnvalue = "";
		while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC))
		{
			$returnvalue = $returnvalue.$row['tag_name'].",";
		}
		echo $returnvalue;
	}
	
	mysqli_close($con);
?>