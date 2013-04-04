<?php
/* Standard Header */
require 'utilities/db.php';
require 'utilities/model.php';
require 'utilities/view.php';
require 'models/tugas.php';
require 'models/kategori.php';
require 'models/user.php';

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

		$view = new View('views/search/search.tpl');

		$view->set('headTags', '<script src="./scripts/search.js" type="application/javascript"></script>');
		$view->set('bodyAttrs', 'onload="saatload(\''.$_GET['q'].'\',\''.$_GET['filter'].'\',\''.$_GET['x'].'\',\''.$_GET['n'].'\');"');
		echo $view->output();
	}
}
else
{
	$view = new View('views/search/search.tpl');
	echo $view->output();
}
?>