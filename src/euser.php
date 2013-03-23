<?php 
	
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
	
	$x = $_GET['edname'];
	$i = $_GET['edmail'];
	$j = $_GET['edpass'];
	$k = $_GET['edcpass'];
	$y = $_GET['tahun'];
	$m = $_GET['bulan'];
	$d = $_GET['tanggal'];
	
	$etanggal = $y."-".$m."-".$d;
	$etanggal = mysql_real_escape_string($etanggal);
	$etanggal = strtotime($etanggal);
	$etanggal = date('Y-m-d',$etanggal);
	
	$getRegUser_sql = 'SELECT * FROM profil WHERE username="'.$x.'"';
	$getRegUser = mysql_query($getRegUser_sql);
	$getRegUser_result = mysql_fetch_assoc($getRegUser);
	$getRegUser_RecordCount = mysql_num_rows($getRegUser);
	
	$getEmail_sql = 'SELECT * FROM profil WHERE email="'.$i.'"';
	$getEmail = mysql_query($getEmail_sql);
	$getEmail_result = mysql_fetch_assoc($getEmail);
	$getEmail_RecordCount = mysql_num_rows($getEmail);
	
	if ($getEmail_RecordCount == 1)
	{
		echo '1';
	}
	else
	{	
		
		echo '2';
	}	
	
	mysql_close();
?>