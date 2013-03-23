<?php
	//konek ke database
	include "connectdb.php";
	
	session_start();
	$user=$_SESSION['username'];
	$nama=$_POST['namaleng'];
	$pass=$_POST['password'];
	$passmd5 = md5($pass);
	$tanggal=$_POST['tanggal'];
	$avatar=$_FILES["avatar"]["name"];
	
	$perintah = "SELECT * FROM accounts WHERE username='$user'";
	$result = mysql_query($perintah);
	$row = mysql_fetch_array($result);
	
	if ($row==NULL)
		{
			echo'false';
		}
			else
		{
			if ($nama==null || $nama==$row['nama_lengkap'])
				{
					$a=0;
				}
					else
				{
					$perintah2 = "update accounts set nama_lengkap='".$nama."'WHERE username='".$user."'";
					mysql_query($perintah2);
					$a=1;
				}
				
			if ($pass==null || $pass==$row['password'])
				{
					$b=$passmd5;
				}
					else
				{
					$perintah3 = "update accounts set password='".$passmd5."' WHERE username='".$user."'";
					mysql_query($perintah3);
					$b=1;
				}
				
			if($tanggal==null || $tanggal==$row['tgl_lahir'])
				{
					$c=0;
				}
					else
				{
					$perintah4 = "update accounts set tgl_lahir='".$tanggal."'WHERE username='".$user."'";
					mysql_query($perintah4);
					$c=1;
				}
				
			if($avatar==null || $avatar==$row['avatar'])
				{
					$d=0;
				}
					else
				{
					if ($_FILES['avatar']['error'] > 0)
						{
							echo "Return Code: " . $_FILES['avatar']['error'] . "<br";
						}
							else
						{
							if (file_exists("upload/" . $_FILES['avatar']['name']))
								{
									//echo $_FILES['avatar']['name'] . "already exists ";
								}
									else
								{
									move_uploaded_file($_FILES['avatar']['tmp_name'],"upload/" . $_FILES['avatar']['name']);
									//echo "Stored in: " . "upload/" . $_FILES['avatar']['name'];			
								}
						}
					
						$perintah5 = "update accounts SET avatar='upload/".$avatar."'WHERE username='".$user."'";
						mysql_query($perintah5);
					
					$d=1;
				}
			header("Location: profile.php?a=".$a."&b=".$b."&c=".$c."&d=".$d);
			
			
		}

?>	