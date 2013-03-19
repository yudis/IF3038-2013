<?php
	require 'utilities/db.php';
	require 'utilities/model.php';
	require 'utilities/view.php';
	require 'models/tugas.php';
	
	$tugas = new Tugas;
	
	$tugas->set_taskname($_POST["namatask"]);
	$tugas->set_tgl_deadline($_POST["deadline"]);
	$tugas->set_pemilik("edogawa");
	$tugas->set_id_kategori($_POST["namakategori"]);
	
	$tugas->store();
	header ("Location: dashboard.php");
?>