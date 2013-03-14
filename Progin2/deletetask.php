<?php
$con = mysql_connect("localhost:3306","root","");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);
session_start();

$idtugas=$_GET["q"];

mysql_query("DELETE FROM tag WHERE idtugas = '$idtugas'");
mysql_query("DELETE FROM assignee WHERE idtugas = '$idtugas'");
mysql_query("DELETE FROM attachment WHERE idtugas = '$idtugas'");
mysql_query("DELETE FROM komentar WHERE idtugas = '$idtugas'");

mysql_query("DELETE FROM tugas WHERE idtugas = '$idtugas'");
header("location:dashboard.php");
?>