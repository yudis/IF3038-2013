<?php
	$conn = mysqli_connect('localhost', 'progin','progin','progin_405_13510108');
	if (mysqli_connect_errno($conn))
	{
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
?>