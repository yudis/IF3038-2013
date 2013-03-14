<?php
	require 'utilities/db.php';
	require 'utilities/model.php';
	require 'utilities/view.php';
	require 'models/kategori.php';
	$kategori = new Kategori();
	$i=1;
	$success[$i] = $kategori->getKategori($i);
	while (!empty($success[$i]))
	{
		$nama=$success[$i]["nama"];
		echo "<button type=\"button\" onclick=\"loadtugas(this.value)\"  value=\"", $nama ,"\")\">";
		echo $success[$i]["nama"];
		echo "</button>";
		echo "<br>";
		$i++;
		$success[$i] = $kategori->getKategori($i);
	}
?>