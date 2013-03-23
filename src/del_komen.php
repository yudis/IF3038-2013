<?php  
include "koneksi.php";

$id = $_GET['id'];
$idtask = $_GET['stat'];
$sql = "DELETE FROM komentar WHERE id='$id'";
$result = mysql_query($sql);
 
$jumlah ="SELECT COUNT(id) as jumlah FROM komentar where idtask='$idtask'";
					$resultjml = mysql_query($jumlah);
$rowjml = mysql_fetch_assoc($resultjml);
echo "Jumlah Komentar : ".$rowjml['jumlah']." <br> ";

echo "Menghapus komentar sukses <br>
	<a href=\"detail.php?id=".$idtask."\">Kembali ke Rincian Tugas";
  
?> 