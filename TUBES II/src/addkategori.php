<?php
$newkat=$_GET["tkategori"];

$con = mysql_connect('localhost', 'progin', 'progin');
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin", $con);

$sql="INSERT INTO kategori (kat) VALUES ('$newkat')";

$add=mysql_query($sql);

echo "Kategori ditambahkan";

mysql_close($con);
?>