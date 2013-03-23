<?php
$con = mysql_connect("localhost:3306","progin","progin");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);

$idtugas = $_GET["q"];
$attachment = ("upload/" . $_FILES["attachment"]["name"]);
mysql_query("INSERT INTO attachment (idtugas, isiattachment) VALUES ('$idtugas', '$attachment')");
move_uploaded_file($_FILES["attachment"]["tmp_name"], "upload/".$_FILES["attachment"]["name"]);

mysql_close($con);
header("location:viewtask.php?q=".$idtugas);
?>