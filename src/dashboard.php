<?php
/* Standard Header */
require 'utilities/db.php';
require 'utilities/model.php';
require 'utilities/view.php';
require 'models/tugas.php';
require 'models/kategori.php';

session_start();
$_SESSION["user"]='edwardsp';
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
		$view = new View('views/dashboard/dashboard.tpl');
		echo $view->output();
	}
}
else
{
	$view = new View('views/dashboard/dashboard.tpl');
	echo $view->output();
}