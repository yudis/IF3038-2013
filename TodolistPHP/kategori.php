<?php
	require 'utilities/db.php';
	require 'utilities/model.php';
	require 'utilities/view.php';
	require 'models/kategori.php';
	$kategori = new Kategori();
	$i=1;
	$success = $kategori->getAllKategori();
	while (!empty($success[$i]))
	{
		echo "<button type=\"button\" onclick=\"loadtugas(this.value)\"  value=\"", $success[$i]["id"] ,"\")\">";
		echo $success[$i]["nama"];
		echo "</button>";
		echo "<br>";
		$i++;
	}
?>