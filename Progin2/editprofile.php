<?php
session_start();
if(!isset($_SESSION['id']))
  header("location:index.php");

$con = mysql_connect("localhost:3306","progin","progin");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);

if (!empty($_POST['nama_lengkap'])) {
	echo "wkwkwkw";
	$fullname = $_POST['nama_lengkap'];
	echo $fullname;
	mysql_query("UPDATE user SET fullname='$fullname' WHERE username='$_SESSION[id]'");
}

if (!empty($_POST['password'])) {
	echo "hahahaha";
	$password = $_POST['password'];
	mysql_query("UPDATE user SET password='$password' WHERE username='$_SESSION[id]'");
}

if (($_POST['bulan']) != '--Bulan--') {
	echo "lololol";
	$birthdate = date("Y/m/d", strtotime(($_POST['tanggal'])." ".($_POST['bulan'])." ".($_POST['tahun'])));
	mysql_query("UPDATE user SET birthdate='$birthdate' WHERE username='$_SESSION[id]'");
}

if (!empty($_FILES["avatar"]["name"])) {
	echo "wkwoakwoakw";
	$avatar = ("upload/" . $_FILES["avatar"]["name"]);
	mysql_query("UPDATE user SET avatar='$avatar' WHERE username='$_SESSION[id]'");
	
	move_uploaded_file($_FILES["avatar"]["tmp_name"], "upload/" . $_FILES["avatar"]["name"]);

}

header("location:profile.php");

mysql_close($con);
?>