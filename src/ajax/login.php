<?php
/* Standard Header */
require '../utilities/db.php';
require '../utilities/model.php';
require '../models/user.php';

session_start();
if (isset($_SESSION["user"]))
{
	// user sudah login, dialihkan ke halaman lain
	header('Location: ../dashboard.php');	
}
else
{
	if (isset($_POST["username"]) && isset($_POST["password"]))
	//if (isset($_GET["username"]) && isset($_GET["password"]))
	{
		$return = array("status" => 401);
		
		$user = new User();
		$success = $user->getUserLogin($_POST["username"], $_POST["password"]);
		//$success = $user->getUserLogin($_GET["username"], $_GET["password"]);
		if ($success)
		{
			$return["status"] = 200;
			$_SESSION["user"] = $success;
		}
		else
		{
			$return["message"] = "Login failed, username/password does not correct.";
		}
		
		header('Content-Type:application/json');
		echo json_encode($return);
	}
	else
	{
		// user belum login, dialihkan ke halaman lain
		header('Location: ../index.php');
	}	
}