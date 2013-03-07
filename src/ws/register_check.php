<?php
	$return = array();
	if (($_SERVER['REQUEST_METHOD'] === 'POST') && (ISSET($_POST['username'])) && (ISSET($_POST['email']))
		&& (ISSET($_POST['password'])) && (ISSET($_POST['confirm_password'])) 
		&& (ISSET($_POST['name'])) && (ISSET($_POST['birth_date']))
		//&& (ISSET($_POST['avatar']))
		)
	{
		$return["status"] = "success";
		$return["error"] = array();
		if ($_POST['username'] == "admin")
		{
			$return["status"] = "fail";
			$return["error"]["username"] = "Username sudah digunakan.";
		}
		if ($_POST['email'] == "fernandojordan.92@gmail.com")
		{
			$return["status"] = "fail";
			$return["error"]["email"] = "Email sudah digunakan.";
		}
		if (!preg_match("/^.{5,}$/", $_POST['username']))
		{
			$return["status"] = "fail";
			$return["error"]["username"] = "Username harus minimal 5 karakter.";
		}
		if ((!preg_match("/^.{8,}$/", $_POST['password']))||($_POST['password']!=$_POST['confirm_password'])||($_POST['password']==$_POST['email'])||($_POST['password']==$_POST['username']))
		{
			$return["status"] = "fail";
			$return["error"]["password"] = "Sandi harus minimal 8 karakter, tidak sama dengan email dan username.";
		}
		if (!preg_match("/^.+ .+$/", $_POST['name']))
		{
			$return["status"] = "fail";
			$return["error"]["name"] = "Nama lengkap harus terdiri dari 2 kata dipisah oleh spasi.";
		}
		if ((!preg_match("#^[0-3][0-9]/[0-1][0-9]/[1-9][0-9][0-9][0-9]$#", $_POST['birth_date']))&&(explode("/", $_POST['birth_date'])[sizeof(explode("/", $_POST['birth_date']))-1]>=1955))
		{
			$return["status"] = "fail";
			$return["error"]["birth_date"] = "Format tanggal lahir yang dimasukkan salah.";
		}
		if (!preg_match("/^.+@.+\...+$/", $_POST['email']))
		{
			$return["status"] = "fail";
			$return["error"]["email"] = "Format email yang dimasukkan salah.";
		}
		echo json_encode($return);
	}
	else
	{
		$return["status"] = "fail";
		echo json_encode($return);
	}
?>