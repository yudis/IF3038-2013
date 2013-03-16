<?php
$con = mysql_connect("localhost:3306","root","");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);

$idtugas = $_GET["q"];
$assignee = $_GET['p'];

mysql_query("DELETE FROM assignee WHERE idtugas='$idtugas'");

$result = mysql_query("SELECT * FROM assignee WHERE idtugas = '$idtugas'");
$count = mysql_num_rows($result);
while($row = mysql_fetch_array($result)){
	if ($count > 1)
		echo $row['username'].", ";
	else 
		echo $row['username'];
	$count--;
}
mysql_close($con);
?>