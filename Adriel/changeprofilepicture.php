<?php
	$source = $_GET["source"];
	$dest = $_GET["dest"];
	
	$con = mysqli_connect('localhost', 'progin', 'progin');
	if (!$con)
	{
		die('Could not connect: ' . mysqli_error());
	}
	
	mysqli_select_db($con, "progin_405_13510035");
	
	if (!copy($source, $dest)) {
		echo $dest;
	}
	else
	{
		echo "#";
	}
	
	$result = mysqli_query($con, $sql);
	
	mysqli_close($con);
?>