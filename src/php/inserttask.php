<?php 
	require('init_function.php');
	session_start();
	$taskid = getNextTaskId();
	$taskname = $_POST['textTaskNameb'];
	$useractive = $_SESSION['userlistapp'];
	$createddate = date('Y-m-d H:i:s');
	$deadlinedate = "9999-12-31 23:59:59";
	$status = "UNCOMPLETE";
	$categoryid = $_GET['categoryid'];
	$listAssignee = $_POST['textAssigneeb'];
	$listTag = $_POST['textTagb'];
	
	echo "Task ID : ".$taskid."<br />";
	echo "User Active : ".$useractive."<br />";
	echo "Create Date : ".$createddate."<br />";
	echo "Deadline Date : ".$deadlinedate."<br />";
	echo "Task Name : ".$taskname."<br />";
	echo "Status : ".$status."<br />";
	echo "Task ID : ".$categoryid."<br />";
	echo "List Assigne : ".$listAssignee."<br />";
	echo "List Tag : ".$listTag."<br />";
	
	$con = getConnection();
	$query = "INSERT INTO `task` 
	(`taskid`, `taskname`, `username`, `createddate`, `deadline`, `status`, `categoryid`) VALUES 
	($taskid, '$taskname', '$useractive', '$createddate', '$deadlinedate', '$status', $categoryid)";
	mysqli_query($con,$query);
	
	$query = "INSERT INTO `assignee` (`username`, `taskid`) VALUES ('$useractive', $taskid)";
	mysqli_query($con,$query);
	$Assignee = explode(',', $listAssignee);
	for($i = 0; $i < count($Assignee) ; $i++){
			$query = "INSERT INTO `assignee` (`username`, `taskid`) VALUES ('$Assignee[$i]', $taskid)";
			mysqli_query($con,$query);
	}
	
	$Tag = explode(',', $listTag);
	for($i = 0; $i < count($Tag) ; $i++){
			$tagid = getTagId($Tag[$i]);
			$query = "INSERT INTO `task_tag` (`taskid`, `tagid`) VALUES ($taskid, ".$tagid.");";
			mysqli_query($con,$query);
	}
	
	if(isset($_FILES["attachment1b"]["size"])){
		$file = $_FILES['attachment1b'];
		upload_attachment($file,$useractive);
		$filename = $file['name'];
		if(strcmp($filename,"") != 0){
			$query = "INSERT INTO `attachment` (`filename`, `taskid`) VALUES ('$useractive-$filename', $taskid)";
			mysqli_query($con,$query);
		}
	}
	if(isset($_FILES["attachment2b"]["size"])){
		$file = $_FILES['attachment2b'];
		upload_attachment($file,$useractive);
		$filename = $file['name'];
		if(strcmp($filename,"") != 0){
			$query = "INSERT INTO `attachment` (`filename`, `taskid`) VALUES ('$useractive-$filename', $taskid)";
			mysqli_query($con,$query);
		}
	}
	if(isset($_FILES["attachment3b"]["size"])){
		$file = $_FILES['attachment3b'];
		upload_attachment($file,$useractive);
		$filename = $file['name'];
		if(strcmp($filename,"") != 0){
			$query = "INSERT INTO `attachment` (`filename`, `taskid`) VALUES ('$useractive-$filename', $taskid)";
			mysqli_query($con,$query);
		}
	}
	if(isset($_FILES["attachment4b"]["size"])){
		$file = $_FILES['attachment4b'];
		upload_attachment($file,$useractive);
		$filename = $file['name'];
		if(strcmp($filename,"") != 0){
			$query = "INSERT INTO `attachment` (`filename`, `taskid`) VALUES ('$useractive-$filename', $taskid)";
			mysqli_query($con,$query);
		}
	}
	if(isset($_FILES["attachment5b"]["size"])){
		$file = $_FILES['attachment5b'];
		upload_attachment($file,$useractive);
		$filename = $file['name'];
		if(strcmp($filename,"") != 0){
			$query = "INSERT INTO `attachment` (`filename`, `taskid`) VALUES ('$useractive-$filename', $taskid)";
			mysqli_query($con,$query);
		}
	}
	header("Location: ../page/dashboard.php");
?>