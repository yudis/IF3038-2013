<?php
	require '../utilities/db.php';
	require '../utilities/model.php';
	require '../utilities/view.php';
	require '../models/kategori.php';
	$q=$_GET["q"];
	$Arr=explode(',', $_GET["Arr"]);
	session_start();
	$kategori = new Kategori();
	$kategori->NewKategori($q,$_SESSION['user']);
	$i=0;
	while(!empty($Arr[$i]))
	{
		if($Arr[$i]!=$_SESSION['user'])
		{
			$kategori->addNewestCoordinator($Arr[$i]);
		}
		$i++;
	}
	header('Location: dashboard.php');
?>