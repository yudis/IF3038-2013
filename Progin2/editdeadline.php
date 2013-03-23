<?php
$con = mysql_connect("localhost:3306","progin","progin");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_439_13510057", $con);

$idtugas = $_GET["q"];
$time = strtotime(($_GET['tgl'])." ".($_GET['bln'])." ".($_GET['thn']));
$deadline = date("Y/m/d", $time);

mysql_query("UPDATE tugas SET deadline='$deadline' WHERE idtugas = '$idtugas'");

mysql_close($con);

header("location:viewtask.php?q=".$idtugas);
?>