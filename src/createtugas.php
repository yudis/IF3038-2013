<?php
/* Standard Header */
include './utilities/db.php';
include './utilities/model.php';
include './utilities/view.php';

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
		$view = new View('views/create/createtugas.tpl');
		echo $view->output();
	}
}
else
{
	$view = new View('views/create/createtugas.tpl');
	echo $view->output();
}