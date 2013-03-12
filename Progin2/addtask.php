<?php
$con = mysql_connect("localhost:3306","root","");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);

$namatugas = $_POST['taskname'];
$deadline = $_POST['tahun'].'-'$_POST['bulan'].'-'$_POST['tanggal'];
$idkategori = 1;
$username = 'admin'; 

$result = mysql_query("INSERT INTO `tugas` (`idtugas`, `namatugas`, `attachment`, `deadline`, `idkategori`, `username`) VALUES
('1', '$namatugas', '', '$deadline', '$idkategori', '$username');");

mysql_close($con);
?>