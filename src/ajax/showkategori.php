<?php
	require '../utilities/db.php';
	require '../utilities/model.php';
	require '../utilities/view.php';
	require '../models/kategori.php';
	$kategori = new Kategori();
	$i=1;
	$success[$i] = $kategori->getKategori($i);
	while (!empty($success[$i]))
	{
		$id_kategori=$success[$i]["id"];
		echo '<option value="',$success[$i]["id"],'">',$success[$i]["nama"],'</option>';
		$i++;
		$success[$i] = $kategori->getKategori($i);
	}
?>