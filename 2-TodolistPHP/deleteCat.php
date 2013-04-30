<?php
	require 'utilities/db.php';
	require 'utilities/model.php';
	require 'utilities/view.php';
	require 'models/kategori.php';
	session_start();
	$kategori = new Kategori();
	$q= $_GET["q"];
	$users=$kategori->getUser($q);
	$n=0;
	while(!empty($users[$n]))
	{
		if($users[$n]["user"]==$_SESSION["user"]["username"])
		{
			$kategori->deleteKategori($q);
			break;
		}
		$n++;
	}
	
	header('Location: dashboard.php');
?>