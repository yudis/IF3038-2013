<?php
$con = mysql_connect("localhost:3306","progin","progin");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);
session_start();

$idkategori=$_GET["q"];

$result = mysql_query("SELECT idtugas FROM tugas WHERE tugas.idkategori = '$idkategori'");
while($row = mysql_fetch_array($result)) {
	mysql_query("DELETE FROM tag WHERE idtugas = '$row[idtugas]'");
	mysql_query("DELETE FROM assignee WHERE idtugas = '$row[idtugas]'");
	mysql_query("DELETE FROM attachment WHERE idtugas = '$row[idtugas]'");
	mysql_query("DELETE FROM komentar WHERE idtugas = '$row[idtugas]'");
}
mysql_query("DELETE FROM tugas WHERE idkategori = '$idkategori'");
mysql_query("DELETE FROM hak WHERE idkategori = '$idkategori'");
mysql_query("DELETE FROM kategori WHERE idkategori = '$idkategori'");

header("location:dashboard.php");
?>