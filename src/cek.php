<?php

//memulai session
session_start();

//konek ke database
include "connectdb.php";

//Ambil data dari form
	$username = $_POST['username'];
	$password = $_POST['password'];
	
//enkripsi password (ga tau dipake atau ga)
	$password_md5 = md5($password);
	
//cek username dan password yang ada di database
	$perintah = "select* from accounts where username='$username'&&password='$password_md5'";
	$perintah_di_query = mysql_query($perintah);
	$ketersediaan = mysql_num_rows($perintah_di_query);
	
//cek adanya username dan password di database + buat session
	if ($ketersediaan >= 1) {
		$_SESSION['username'] = $username;
		echo 'true';
		//header("Location: dashboard.php");
	}
		else
	{
		echo 'false';
		//header("Location: salah.php");
	}


?>
