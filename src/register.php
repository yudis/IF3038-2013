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
	
	$regusername = $_GET['username'];
	$regemail = $_GET['email'];
	$regpassword = $_GET['copassword'];
	$regnama = $_GET['namalengkap'];
	$regimage = $_GET['inputfile'];
	
	$regtahun = $_GET['tahun'];
	$regbulan = $_GET['bulan'];
	$regtanggal = $_GET['tanggal'];
	$tanggal = $regtahun."-".$regbulan."-".$regtanggal;
	$tanggal = mysql_real_escape_string($tanggal);
	$tanggal = strtotime($tanggal);
	$tanggal = date('Y-m-d',$tanggal);
	
	$getRegUser_sql = 'SELECT * FROM profil WHERE username="'.$regusername.'"';
	$getRegUser = mysql_query($getRegUser_sql);
	$getRegUser_result = mysql_fetch_assoc($getRegUser);
	$getRegUser_RecordCount = mysql_num_rows($getRegUser);
	
	$getEmail_sql = 'SELECT * FROM profil WHERE email="'.$regemail.'"';
	$getEmail = mysql_query($getEmail_sql);
	$getEmail_result = mysql_fetch_assoc($getEmail);
	$getEmail_RecordCount = mysql_num_rows($getEmail);
		
	if ($getRegUser_RecordCount == 1)
	{
		echo '0';
	}
	else if ($getEmail_RecordCount == 1)
	{
		echo '1';
	}
	else
	{	
		$reg1 = "INSERT INTO user (username, password)
		VALUES
		('$regusername', '$regpassword')";
			
		$reg2 = "INSERT INTO profil (username, namalengkap, tanggallahir, email, avatar)
		VALUES
		('$regusername', '$regnama', '$tanggal', '$regemail', '$regimage')";
		
		mysql_query($reg1);
		mysql_query($reg2);
		echo '2';
	}
	
	mysql_close();
?>