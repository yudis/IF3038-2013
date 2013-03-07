<?php
	$return = array();
	if (($_SERVER['REQUEST_METHOD'] === 'POST') && (ISSET($_POST['username'])) && (ISSET($_POST['password'])))
	{
		if (($_POST['username'] == "admin") && ($_POST['password'] == "admin123"))
		{
			session_start();
			// TODO add database
			$_SESSION['user_id'] = 1;
			$return["status"] = "success";
			echo json_encode($return);
		}
		else
		{
			$return["status"] = "fail";
			echo json_encode($return);
		}
	}
	else
	{
		$return["status"] = "fail";
		echo json_encode($return);
	}
?>