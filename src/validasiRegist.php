<?php
	
	//konek ke database
	include "connectdb.php";
	
	$user=$_GET["username"];
	$email=$_GET["email"];
	
	$perintah = "select* from accounts WHERE username='$user' OR email='$email'";
	$perintah_di_query = mysql_query($perintah);
	$row = mysql_fetch_array($perintah_di_query);
	
	if ($row == NULL)
	{
		echo 'true';
	}
	else
	{
		echo 'false';
	}

?>