<?php
	require('init_function.php');
	session_start();
	$username = $_SESSION['userlistapp'];
	$namecategory = $_POST['newCategory'];
	$listAssignee = $_POST['listAssignee'];
	$datetime = date('Y-m-d H:i:s');
	$nextCategoryId = getNextCategoryId();
	$Assignee = explode(',', $listAssignee);
	
	$con = getConnection();
	// insert categori
	$query = "INSERT INTO category VALUES ('$nextCategoryId', '$namecategory', '$username','$datetime')";
	mysqli_query($con,$query);
	$query = "INSERT INTO responsibility VALUES ('$username','$nextCategoryId')";
	mysqli_query($con,$query);	
	// insert assigne
	for($i = 0; $i < count($Assignee) ; $i++){
			$query = "INSERT INTO responsibility VALUES ('$Assignee[$i]','$nextCategoryId')";
			mysqli_query($con,$query);
	}
	mysqli_close($con);
	header("Location: ../page/dashboard.php");
?>