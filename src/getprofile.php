<?php
	$q = $_GET["q"];
	$type = $_GET["type"];
	
	$con = mysqli_connect('localhost', 'progin', 'progin');
	if (!$con)
	{
		die('Could not connect: ' . mysqli_error());
	}
	
	mysqli_select_db($con, "progin_405_13510029");
	
	if ($type == "fullname")
	{
		$sql="SELECT fullname FROM user WHERE username = '".$q."'";
	}
	else if ($type == "email")
	{
		$sql="SELECT email FROM user WHERE username = '".$q."'";
	}
	else if ($type == "date")
	{
		$sql="SELECT birthday FROM user WHERE username = '".$q."'";
	}
	else if ($type == "password")
	{
		$sql="SELECT password FROM user WHERE username = '".$q."'";
	}
	
	$result = mysqli_query($con, $sql);
	$row = mysqli_fetch_array($result, MYSQLI_NUM);
	echo $row[0];
	
	mysqli_close($con);
?>