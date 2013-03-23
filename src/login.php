<?php
	session_start();

	$mysql_hostname = "localhost";
	$mysql_user = "root";
	$mysql_password = "";
	$mysql_database = "progin";
	
	$db = mysql_connect($mysql_hostname, $mysql_user, $mysql_password);	
	if (!$db)
	{
		die('Could not connect: ' . mysql_error());
	}
	mysql_select_db($mysql_database) or die("Opps some thing went wrong");
	
	$username = $_GET['idlogin'];
	$password = $_GET['passlogin'];
	
	$getUser_sql = 'SELECT * FROM user WHERE username="'.$username.'"AND password="'.$password.'"';
	$getUser = mysql_query($getUser_sql);
	$getUser_result = mysql_fetch_assoc($getUser);
	$getUser_RecordCount = mysql_num_rows($getUser);
		
	if ($getUser_RecordCount < 1)
	{
		echo '0';
	}
	else
	{
		echo $getUser_result['password'];
	}
	
	mysql_close($db);
?>