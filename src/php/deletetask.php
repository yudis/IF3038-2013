<?php 
	require('init_function.php');	
	$taskid = $_GET['taskid'];
	$con = getConnection();
	$query = "SELECT filename FROM attachment WHERE taskid = $taskid";
	$result = mysqli_query($con,$query);
	while($row = mysqli_fetch_array($result)){
		$deletfile = "../attachment/".$row['filename'];
		echo $deletfile;
		unlink($deletfile);
	}
	$query = "DELETE FROM task WHERE taskid=$taskid";
	mysqli_query($con,$query);
	mysqli_close($con);
	header("Location: ../page/dashboard.php");
?>