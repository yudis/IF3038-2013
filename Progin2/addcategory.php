<?php
$con = mysql_connect("localhost:3306","root","");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);

$idkategori = 1;
$namakategori = $_POST['taskname'];
$username = 'admin'; 

$result1 = mysql_query("INSERT INTO `kategori` (`idkategori`, `namakategori`) VALUES
('idkategori', '$namakategori');");
$result2 = mysql_query("INSERT INTO `hak` (`username`, `idkategori`) VALUES
('$username', '$idkategori');");

mysql_close($con);
?>