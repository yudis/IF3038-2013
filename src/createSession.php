<?php
	session_start();	
	$_SESSION['username']=$_GET['t'];
	
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
	
	$getUserData_sql = 'SELECT * FROM profil WHERE username="'.$_SESSION['username'].'"';
	$getUserData = mysql_query($getUserData_sql);
	$getUserData_result = mysql_fetch_assoc($getUserData);
	
	$_SESSION['mail'] = $getUserData_result['email'];
	$_SESSION['name'] = $getUserData_result['namalengkap'];
	$_SESSION['tanggal'] = $getUserData_result['tanggallahir'];
	
	mysql_close();
	
	header("Location:profil.php");?>