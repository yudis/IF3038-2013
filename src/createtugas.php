<?php
/* Standard Header */
include './utilities/db.php';
include './utilities/model.php';
include './utilities/view.php';

session_start();
if (isset($_SESSION["user"]))
{
	$view = new View('views/create/createtugas.tpl');
	$view->set('title', 'Todolist | Create Tugas');
			$view->set('headTags', '<link rel="stylesheet" type="text/css" href="styles/default.css" />
        <link rel="stylesheet" type="text/css" href="styles/mediaqueries.css" />
        <script src="scripts/formtugas.js" type="application/javascript"></script>
        <script src="scripts/tugas.js" type="application/javascript"></script>
        <script src="scripts/createtugas.js" type="application/javascript"></script>');
	
	echo $view->output();
}
else
{
	header('Location: index.php');
}