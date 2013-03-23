<?php
$con = mysql_connect("localhost:3306","progin","progin");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);

// username and password sent from form 
$isikomentar = $_GET['q']; 
$idtugas = $_GET['r'];
$username = $_GET['s']; 

mysql_query("DELETE FROM komentar WHERE isikomentar='$isikomentar' and idtugas='$idtugas' and username='$username'");

header("location:viewtask.php?q=".$idtugas);

mysql_close($con);
?>