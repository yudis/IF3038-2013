<?php
	$q = $_GET["q"];
	$type = $_GET["type"];
	$val = $_GET["val"];
	
	$con = mysqli_connect('localhost', 'progin', 'progin');
	if (!$con)
	{
		die('Could not connect: ' . mysqli_error());
	}
	
	mysqli_select_db($con, "progin_405_13510035");
	
	if ($type == "fullname")
	{
		$sql="UPDATE user SET fullname='".$val."' WHERE username = '".$q."'";
	}
	else if ($type == "email")
	{
		$sql="UPDATE user SET email='".$val."' WHERE username = '".$q."'";
	}
	else if ($type == "date")
	{
		$sql="UPDATE user SET birthday='".$val."' WHERE username = '".$q."'";
	}
	else if ($type == "password")
	{
		$sql="UPDATE user SET password='".$val."' WHERE username = '".$q."'";
	}
	
	$result = mysqli_query($con, $sql);
	
	mysqli_close($con);
?>