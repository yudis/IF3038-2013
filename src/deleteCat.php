<?php
	require 'utilities/db.php';
	require 'utilities/model.php';
	require 'utilities/view.php';
	require 'models/kategori.php';
	$kategori = new Kategori();
	$q= $_GET["q"];
	$kategori->deleteKategori($q);
	
	header('Location: dashboard.php');
?>