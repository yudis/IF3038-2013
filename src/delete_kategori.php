<?php
	require_once("connectdb.php");
	$query1 = "DELETE FROM accounts_has_tugas WHERE tugas_idtugas in (SELECT idtugas FROM tugas WHERE kategori_idkategori = ".$_GET["idkategori"].")";
	$query2 = "DELETE FROM tugas_has_tag WHERE tugas_idtugas in (SELECT idtugas FROM tugas WHERE kategori_idkategori =".$_GET["idkategori"].")";
	$query3 = "DELETE FROM tugas WHERE kategori_idkategori = ".$_GET["idkategori"];
	$query4 = "DELETE FROM assignee_has_kategori WHERE kategori_idkategori = ".$_GET["idkategori"];
	$query5 = "DELETE FROM kategori WHERE idkategori = ".$_GET["idkategori"];
	
	mysql_query($query1, $sql) or die(mysql_error());
	mysql_query($query2, $sql) or die(mysql_error());
	mysql_query($query3, $sql) or die(mysql_error());
	mysql_query($query4, $sql) or die(mysql_error());
	mysql_query($query5, $sql) or die(mysql_error());
	mysql_close($sql);
	header("Location: dashboard.php");
?>