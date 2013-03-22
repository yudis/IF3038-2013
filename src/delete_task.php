<?php
	require_once("connectdb.php");
	
	$query1 = "DELETE FROM accounts_has_tugas WHERE tugas_idtugas = ".$_GET["idtugas"];
	$query2 = "DELETE FROM tugas_has_tag WHERE tugas_idtugas = ".$_GET["idtugas"];
	$query3 = "DELETE FROM tugas WHERE idtugas = ".$_GET["idtugas"];
	
	$result = mysql_query($query1, $sql);
	$result = mysql_query($query2, $sql);
	$result = mysql_query($query3, $sql);
	mysql_close($sql);
	header("Location: dashboard.php");
?>