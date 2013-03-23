<?php
	$q = $_GET["q"];
	
	$con = mysqli_connect('localhost', 'progin', 'progin');
	if (!$con)
	{
		die('Could not connect: ' . mysqli_error());
	}
	
	mysqli_select_db($con, "progin_405_13510035");

	$sql="SELECT email FROM user WHERE username = '".$q."'";
	
	$result = mysqli_query($con, $sql);
	$row = mysqli_fetch_array($result, MYSQLI_NUM);
	echo $row[0];
	
	mysqli_close($con);
?>