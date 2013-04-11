<?php
	require_once("../connectDB.php");
	
	$category = request.getParameter("category");
	$user = request.getParameter("user");
	$query = "SELECT category, users FROM category WHERE category = '$category'";
	echo $query;
	$result = mysql_query($query);
	
	if ($result)
	{
		$row = mysql_fetch_array($result);
		
		if ($category == $row("category"))
		{
			$users = $row("users");		
			$users = $users.','.$user;
			$query = "UPDATE category SET users = '$users' WHERE category = '$category'";	
		}
		else
		{
			$query = "INSERT INTO category VALUES ('$category', '$user')";		
		}
	}
	
	echo $query;
	$result = mysql_query($query);
	
	mysql_close($con);
?>