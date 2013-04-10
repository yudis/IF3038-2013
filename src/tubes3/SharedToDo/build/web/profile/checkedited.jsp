<?php
	require_once("../connectDB.php");
	echo "keren";
	$type = $_POST['type'];
	$value = $_POST['value'];
	
	$query = "SELECT $type FROM login WHERE $type = '$value'";
	$result = mysql_query($query);
	if ($result)
	{
		echo 0;
	}
	else
	{
		echo 1;
	}
	
	mysql_close($con);
?>