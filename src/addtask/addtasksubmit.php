<?php
	require_once("connectDB.php"); 
	$taskname = $_POST['taskname'];
	$deadline = $_POST['deadline'];
	$assignee = $_POST['assignee'];
	$tags = $_POST['tags'];
	$status = 1;
	$category = "progin";
	$attachment = $_POST['attachment'];
	$type = "file";
	$assignee = "rezamp";
	$query = "INSERT INTO task (taskname, deadline, tags, status, category, attachment, type, assignee) VALUES ('$taskname', '$deadline', '$tags', '$status', '$category', '$attachment', '$type', '$assignee')";
	$result = mysql_query($query);
	/*
	if($result)
	{
		echo "Successful";
	}
	else 
	{
		echo "ERROR";
	}	
	*/
	
	/*
	if (isset($_POST['taskname']) && isset($_POST['deadline']) && isset($_POST['assignee']) && isset($_POST['tags']) && isset($_POST['attachment'])) 
	{
		$taskname = $_POST['taskname'];
		$deadline = $_POST['deadline'];
		$assignee = $_POST['assignee'];
		$tags = $_POST['tags'];
		$attachment = $_POST['attachment'];
		
		$query = "INSERT INTO task (taskname, deadline, tags, assignee) VALUES('$taskname','$deadline','$tags','$assignee')";
		mysql_query($con, $query);
		mysql_close($con);
		
	}
	*/
	//echo "<div><p>keren</p></div>";//$_POST['taskname'];
	//echo $_POST['deadline'];
	//echo $_POST['assignee'];
	//echo $_POST['tag'];
	//echo $_POST['attachment'];
	//echo "berhasil";
?>