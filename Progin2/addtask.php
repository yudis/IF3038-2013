<?php
$con = mysql_connect("localhost:3306","root","");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);

$namatugas = $_POST['taskname'];
$deadline = $_POST['tahun'].'-'$_POST['bulan'].'-'$_POST['tanggal'];
$idkategori = $_GET["q"];
$username = 'admin'; 
$count = 1;

function random() {
	$randomid = '';
	for ($i = 0; $i < 10; $i++)
	{
		$randomid .= mt_rand(65, 90);
	}
	return $randomid;}

while (count > 0) {
	$idtugas = random();
	$result = mysql_query("SELECT * FROM tugas WHERE idtugas='$idtugas'");
	$count=mysql_num_rows($result);
}

mysql_query("INSERT INTO `tugas` (`idtugas`, `namatugas`, `attachment`, `deadline`, `idkategori`, `username`) VALUES
('$idtugas', '$namatugas', '', '$deadline', '$idkategori', '$username');");

mysql_close($con);
?>