<?php
$con = mysql_connect("localhost:3306","progin","progin");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);

$idtugas = $_GET["q"];
$idkategori = $_GET["p"];

$result = mysql_query("SELECT status FROM tugas WHERE idtugas = '$idtugas'");
$row = mysql_fetch_array($result);
if ($row['status'] == done)
	mysql_query("UPDATE tugas SET status='undone' WHERE idtugas = '$idtugas'");
else 
	mysql_query("UPDATE tugas SET status='done' WHERE idtugas = '$idtugas'");
	
mysql_close($con);

header("location:dashboard.php?q=".$idkategori);
?>