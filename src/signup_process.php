<?php

//konek ke database
include "connectdb.php";

//Ambil data dari form
	$username = $_POST['username'];
	$password = $_POST['password'];
	$password_md5 = md5($password);
	$email = $_POST['email'];
	$nama_lengkap = $_POST['namaleng'];
	$tgl_lahir = $_POST['tanggal'];
	//$avatar = $_POST['avatar'];
	
//cek sudah diisi semua atau belum
	if($username=='' || $password=='' || $email=='' || $nama_lengkap=='' || $tgl_lahir=='')
		{
			echo "Data tidak lengkap<br--><a href=index.php>Back</a>";
		}
			else //kalau data lengkap
		{
			$perintah = "insert into account values('$username', '$password_md5')";
			$perintah_di_query = mysql_query($perintah);
			
			// Jika input berhasil
			if ($perintah_di_query) 
				{
					echo "Daftar berhasil, silakan <a href='index.php'>login</a>";
				}
					else
				{
					echo "Daftar gagal atau username telah terdaftar silakan <a href='signup.php'>Ulangi</a> atau <a href='login.php'>Login</a>";
				}
		}
	
?>