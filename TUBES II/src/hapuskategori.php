<?php
$newkat=$_GET["hkategori"];

$con = mysql_connect('localhost', 'progin', 'progin');
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin", $con);

$sql="DELETE FROM kategori WHERE kat = '".$newkat."'";

$del=mysql_query($sql);

echo "Kategori telah di hapus";


mysql_close($con);
?>