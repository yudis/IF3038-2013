<?php

require '../utilities/model.php';
require '../utilities/view.php';
require '../models/user.php';
require '../utilities/db.php';


session_start();
	if (isset($_POST["fname"]){
		$_SESSION["user"]["full_name"] = $_POST["fname"];
	}
	if (isset($_POST["Bday"]){
		$_SESSION["user"]["tgl_lahir"] = $_POST["Bday"];
	}	

	
	if ((isset($_POST["pwd_confirm"])) || (($_POST["pwd_confirm"]) != ""))
	{
		$_SESSION["user"]["password"] = $_POST["pwd_confirm"];
	}
	
	if (isset($_FILES["ava"]))
	{
		if ($_FILES["ava"]["size"] > 0)
		{
			$_SESSION["user"]["avatar"] = $_FILES["ava"];
		}
	}
	
	$user = new User();
	$user->fromArray($_SESSION["user"]);
	$user->Update();
	
	header('Location: ../profile.php');
