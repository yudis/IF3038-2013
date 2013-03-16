<?php
$con = mysql_connect("localhost:3306","root","");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);

$idtugas = $_GET["q"];
$tag = preg_split("/[,]/",$_GET['p']);

mysql_query("DELETE FROM tag WHERE idtugas = '$idtugas'");
for ($i=0; $i < count($tag); $i++) 
	mysql_query("INSERT INTO tag (idtugas, isitag) VALUES ('$idtugas', '$tag[$i]')");
	
mysql_close($con);

?>