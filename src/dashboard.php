<?php
/* Standard Header */
require 'utilities/db.php';
require 'utilities/model.php';
require 'utilities/view.php';
require 'models/tugas.php';
require 'models/kategori.php';

session_start();
if (isset($_SESSION["user"]["username"]))
{
	$view = new View('views/dashboard/dashboard.tpl');
	echo $view->output();
}
else
{
	header('Location: index.php');
}