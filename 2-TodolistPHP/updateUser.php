<?php

require './utilities/model.php';
require './utilities/view.php';
require './models/user.php';
require './utilities/db.php';


session_start();

	$_SESSION["user"]["full_name"] = $_POST["fname"];
	$_SESSION["user"]["tgl_lahir"] = $_POST["Bday"];
	
	if ($_POST["pwd_confirm"] != "")
	{
		$_SESSION["user"]["password"] = $_POST["pwd_confirm"];
	}
	
	if (isset($_FILES["ava"]))
	{
		if ($_FILES["ava"]["size"] > 0)
		{
			$avatarfile = $_POST["uname"] . "." . pathinfo($_FILES['ava']['name'], PATHINFO_EXTENSION);	
			$_SESSION["user"]["avatar"] = $avatarfile;
			if (!copy($_FILES["ava"]["tmp_name"], "./images/avatars/" . $avatarfile))
			{		
				die("Failed to upload avatar picture.");
			}
			
		}
		

	}
	
	$user = new User();
	$user->fromArray($_SESSION["user"]);
	$user->Update();
	
	header('Location: ./profile.php');
