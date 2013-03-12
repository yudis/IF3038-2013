<?php
/* Standard Header */
include './utilities/db.php';
include './utilities/model.php';
include './models/user.php';

session_start();
if (isset($_SESSION["user"]))
{
	// user sudah login, dialihkan ke halaman lain
	header('Location: ../dashboard.php');	
}
else
{
	// echo ($_POST["uname"]);
	// echo ($_POST["email"]);
	// echo ($_POST["pwd"]);
	// echo ($_POST["name"]);
	// echo ($_POST["bday"]);
	// echo ($_FILES["ava"]["name"]);

	if (isset($_POST["uname"]) && isset($_POST["email"]) && isset($_POST["pwd"]) && isset($_POST["name"]) && isset($_POST["bday"]) && isset($_FILES["ava"]))
	{
		try
		{
			$user = new User();
			$user->set_username($_POST["uname"]);
			$user->set_email($_POST["email"]);
			$user->set_password($_POST["pwd"]);
			$user->set_full_name($_POST["name"]);
			$user->set_tgl_lahir(strtotime($_POST["bday"]));
			$user->set_avatar($_FILES["ava"]["name"]);

			$user->store();
			
			$avatarfile = $_POST["uname"] . "." . pathinfo($_FILES['ava']['name'], PATHINFO_EXTENSION);	
			if (!copy($_FILES["ava"]["tmp_name"], "./images/avatars/" . $avatarfile))
			{		
				die("Failed to upload avatar picture.");
			}
			
			header('Location: ./dashboard.php');	
		}
		catch(Exception $e)
		{
			die($e->getMessage());
		}
		
	}
	else
	{
		die("Parameters do not complete.");
	}
}