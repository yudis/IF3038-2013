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
	$avatar = $_FILES["avatar"]["name"];
	if ($_FILES['avatar']['error'] > 0)
		{
			echo "Return Code: " . $_FILES['avatar']['error'] . "<br";
		}
			else
		{
		
			echo "Upload: " . $_FILES['avatar']['name'] . "<br>";
			echo "Type: " . $_FILES['avatar']['type'] . "<br>";
			echo "Size: " . ($_FILES['avatar']['size']/1024) . "kb<br>";
			echo "Temp file: " . $_FILES['avatar']['tmp_name'] . "<br>";
			
			if (file_exists("upload/" . $_FILES['avatar']['name']))
				{echo "tes";
					echo $_FILES['avatar']['name'] . "already exists ";
				}
					else
				{
					echo "tes";
					move_uploaded_file($_FILES['avatar']['tmp_name'],"upload/" . $_FILES['avatar']['name']);
					echo "Stored in: " . "upload/" . $_FILES['avatar']['name'];			
				}
		}
	
		$perintah = "insert into accounts(username,password,email,nama_lengkap,tgl_lahir,avatar)  values('$username', '$password_md5','$email','$nama_lengkap','$tgl_lahir','upload/".$avatar."')";
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
	
?>