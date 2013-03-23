<?php
$con = mysql_connect("localhost:3306","progin","progin");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_439_13510057", $con);
session_start();

$namakategori = $_POST['categoryname'];
$count = 1;
$idkategori = '';
$userberhak = $_POST['user_berhak'];
$currentuser = $_SESSION['id'];

function random() {
	$randomid = '';
	for ($i = 0; $i < 10; $i++)
	{
		$randomid .= mt_rand(65, 90);
	}
	return $randomid;
}

while ($count > 0) {
	$idkategori = random();
	$result = mysql_query("SELECT * FROM tugas WHERE idkategori='$idkategori'");
	$count=mysql_num_rows($result);
}

mysql_query("INSERT INTO kategori (idkategori, namakategori) VALUES ('$idkategori', '$namakategori');");

mysql_query("INSERT INTO hak (username, idkategori) VALUES ('$currentuser', '$idkategori');");
for($i=0; $i < count($userberhak); $i++) 
	mysql_query("INSERT INTO hak (username, idkategori) VALUES ('$userberhak[$i]', '$idkategori');");
mysql_close($con);

header("location:dashboard.php");
?>