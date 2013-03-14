<?php
/* Standard Header */
require 'utilities/db.php';
require 'utilities/model.php';
require 'utilities/view.php';
require 'models/tugas.php';
require 'models/kategori.php';

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
	$view = new View('views/dashboard/dashboard.tpl');
	echo $view->output();
}