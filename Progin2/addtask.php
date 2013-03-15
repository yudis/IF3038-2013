<?php
$con = mysql_connect("localhost:3306","root","");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);
session_start();

$namatugas = $_POST['taskname'];
$time = strtotime(($_POST['tanggal'])." ".($_POST['bulan'])." ".($_POST['tahun']));
$deadline = date("Y/m/d", $time);
$attachment = ("upload/" . $_FILES["attachment"]["name"]);
$idkategori = $_GET["q"];
$username = $_SESSION['id']; 
$count = 1;
$idtugas = '';
$tag = preg_split("/[,]/",$_POST['tag']);
$assignee = preg_split("/[,]/",$_POST['assignee']);

function random() {
	$randomid = '';
	for ($i = 0; $i < 10; $i++)
	{
		$randomid .= mt_rand(65, 90);
	}
	return $randomid;}

while ($count > 0) {
	$idtugas = random();
	$result = mysql_query("SELECT * FROM tugas WHERE idtugas='$idtugas'");
	$count=mysql_num_rows($result);
}
mysql_query("INSERT INTO tugas (idtugas, namatugas, deadline, idkategori, username, status) VALUES ('$idtugas', '$namatugas', '$deadline', '$idkategori', '$username', '')");

mysql_query("INSERT INTO attachment (idtugas, isiattachment) VALUES ('$idtugas', '$attachment')");
move_uploaded_file($_FILES["attachment"]["tmp_name"], "upload/".$_FILES["attachment"]["name"]);

for ($i=0; $i < count($tag); $i++) 
	mysql_query("INSERT INTO tag (idtugas, isitag) VALUES ('$idtugas', '$tag[$i]')");
	for ($j=0; $j < count($assignee); $j++) {
	mysql_query("INSERT INTO assignee (username, idtugas) VALUES ('$assignee[$j]', '$idtugas')");
	echo $assignee[$j]." ";}

mysql_close($con);
header("location:dashboard.php");
?>