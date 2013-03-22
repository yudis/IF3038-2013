<?php
	require_once("connectdb.php");
	
	$query = "UPDATE tugas SET status_selesai = ".$_GET["status"]." WHERE idtugas = ".$_GET["idtugas"];
	mysql_query($query, $sql) or die(mysql_error());
	
	mysql_close($sql);
	
	if($_GET["status"] == 1)
	{
		echo "Sudah Selesai";
	}else{
		echo "Belum Selesai";
	}
?>