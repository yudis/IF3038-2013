<?php
	$tugas = $_GET["tugas"];
	
	$con = mysqli_connect("localhost","root","","progin_405_13510003");
	
	if (mysqli_connect_errno($con)){
		echo "error";	
	}

	$query = "SELECT * tugas WHERE nama_tugas = '$tugas'";
	$result = mysqli_query($con,$query);
	
	$row= mysqli_fetch_array($result);
	

?>