<?php 
	include('config.php');
	session_start(); 
	
	// $mysql_hostname = "localhost";
	// $mysql_user = "root";
	// $mysql_password = "";
	// $mysql_database = "progin";
	
	// $db = mysql_connect($mysql_hostname, $mysql_user, $mysql_password);	
	// if (!$db)
	// {
		// die('Could not connect: ' . mysql_error());
	// }
	// mysql_select_db($mysql_database) or die("Opps some thing went wrong");	
	
	$id = $_GET['username'];
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
	
	$getRegUser_sql = 'SELECT * FROM profil WHERE username="'.$id.'"';
	$getRegUser = mysql_query($getRegUser_sql);
	$getRegUser_result = mysql_fetch_assoc($getRegUser);
	$getRegUser_RecordCount = mysql_num_rows($getRegUser);
	
	$getEmail_sql = 'SELECT * FROM profil WHERE email="'.$i.'"';
	$getEmail = mysql_query($getEmail_sql);
	$getEmail_result = mysql_fetch_assoc($getEmail);
	$getEmail_RecordCount = mysql_num_rows($getEmail);

	$update1 = 'UPDATE profil SET namalengkap="'.$x.'"WHERE username="'.$_SESSION['username'].'"';
	$update2 = 'UPDATE profil SET email="'.$i.'"WHERE username="'.$_SESSION['username'].'"';
	$update3 = 'UPDATE profil SET tanggal="'.$etanggal.'"WHERE username="'.$_SESSION['username'].'"';
	$update4 = 'UPDATE user SET password="'.$k.'"WHERE username="'.$_SESSION['username'].'"';	
	
	if ($getEmail_RecordCount == 1)
	{
		echo '1';
	}
	else
	{	
		mysql_query($update1);
		mysql_query($update2);
		mysql_query($update3);	
		mysql_query($update4);
		echo '2';
	}	
	
	mysql_close($bd);
	
	session_destroy();
	//header("Location:editSession.php?");
?>