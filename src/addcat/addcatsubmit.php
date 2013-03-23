<?php
	require_once("../connectDB.php");
	
	$category = $_POST['category'];
	$user = $_POST['user'];
	$query = "SELECT * FROM category WHERE category = '$category'";
	$result = mysql_query($query);
	
	if ($result)
	{
		$row = mysql_fetch_array($result);
		$users = $row['users'];		
		$users = $users.','.$user;
		$query = "UPDATE category SET users = '$users' WHERE category = '$category'";
	}
	else
	{
		$query = "INSERT INTO category VALUES ('$category', '$user')";		
	}
	
	$result = mysql_query($query);
	mysql_close($con);
?>