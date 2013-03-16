<?php
/* Standard Header */
require 'utilities/db.php';
require 'utilities/model.php';
require 'utilities/view.php';
require 'models/tugas.php';

session_set_cookie_params(30 * 24 * 60 * 60); 
session_start();
if (!isset($_SESSION["user"]))
{
	// user sudah login, dialihkan ke halaman lain
	header('Location: index.php');
}
else
{
	if (isset($_GET["id"]))
	{
		$tugas = new Tugas();
		$data = $tugas->getTugas($_GET["id"]);
		
		if ($data)
		{			
			$view = new View('views/tugas/default.tpl');
			$view->set('title', 'Todolist | Rincian Tugas');
			$view->set('headTags', '<script src="./scripts/tugas.js" type="application/javascript"></script><script type="text/javascript" src="./scripts/datetimepicker.js"></script><link rel="stylesheet" type="text/css" href="styles/tugas.css" />');
			
			echo json_encode($data);
			// $view->set('namaTugas', $data['nama']);
			// $view->set('deadline', date($data['tgl_deadline']));
			// $view->set('tags', $data['tag']);
			
			// $tagsLen = count($data['tag']);
			// $tagsEdit = '';
			// if ($tagsLen > 0)
			// {
			// 	$tagsEdit .= $data['tag'][0];
			// 	for ($i=1; $i < $tagsLen; $i++)
			// 	{
			// 		$tagsEdit .= ', ' . $data['tag'][$i];
			// 	}
			// }
			// $view->set('tagsEdit', $tagsEdit);
			
			// echo $view->output();
		}
		else
		{
			$view = new View('views/tugas/error.tpl');
			$view->set('title', 'Todolist | Rincian Tugas');
			$view->set('headTags', '');
			echo $view->output();
		}
	}
	else
	{
		header('Location: dashboard.php');
	}
}