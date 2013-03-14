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
		echo "<li><a href=\"#\" onclick=\"loadtugas('",$id_kategori,"'); return false;\" >";
		echo $success[$i]["nama"];
		echo "</a></li>";
		$i++;
		$success[$i] = $kategori->getKategori($i);
	}
?>