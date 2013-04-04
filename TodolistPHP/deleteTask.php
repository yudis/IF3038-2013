<?php
	require 'utilities/db.php';
	require 'utilities/model.php';
	require 'utilities/view.php';
	require 'models/tugas.php';
	$tugas = new Tugas();
	$q= $_GET["q"];
	$tugas->deleteTask($q);
	
	header('Location: dashboard.php');
?>