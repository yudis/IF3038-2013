<?php
/* Standard Header */
require '../utilities/db.php';
require '../utilities/model.php';
require '../models/user.php';

session_set_cookie_params(7 * 24 * 60 * 60); 
session_start();
if (isset($_SESSION["user"]))
{
	// user sudah login, dialihkan ke halaman lain
	header('Location: ../dashboard.php');	
}
else
{
	if (isset($_GET["check"]) && isset($_GET["data"]))
	{
		$return = array("status" => 401);
		$checkresult = false;
		$user = new User();
		if ($_GET["check"] == "username")
		{
			$checkresult = $user->checkAvailabilityUsername($_GET["data"]);
		}
		else if ($_GET["check"] == "email")
		{
			$checkresult = $user->checkAvailabilityEmail($_GET["data"]);
		}
		
		if ($checkresult) 
		{
			$return["status"] = 200;
		}
		
		header('Content-Type:application/json');
		echo json_encode($return);
	}
}