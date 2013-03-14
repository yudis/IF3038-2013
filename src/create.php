<?php
/* Standard Header */
require 'utilities/db.php';
require 'utilities/model.php';
require 'utilities/view.php';

session_start();
if (!isset($_SESSION["user"]))
{
	// user sudah login, dialihkan ke halaman lain
	header('Location: index.php');
}
else
{
	$view = new View('views/create/default.tpl');
	$view->set('title', 'Hai');
	$view->set('headTags', '<script src="./scripts/formtugas.js" type="application/javascript"></script>');
	echo $view->output();
}