<?php
/* Standard Header */
require 'utilities/db.php';
require 'utilities/model.php';
require 'utilities/view.php';


session_set_cookie_params(7 * 24 * 60 * 60); 
session_start();
if (isset($_SESSION["user"]))
{
	if (isset($_GET["logout"]))
	{		
		$_SESSION = array();
		session_destroy();
		
		header('Location: index.php');
	}
	else
	{
		// user sudah login, dialihkan ke halaman lain
		header('Location: dashboard.php');
	}
}
else
{
	$view = new View('views/index/index.tpl');
	echo $view->output();
}