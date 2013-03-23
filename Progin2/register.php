<?php
$con = mysql_connect("localhost:3306","progin","progin");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);

// username and password sent from form 
$username = $_POST['username']; 
$password = $_POST['password'];
$fullname = $_POST['nama_lengkap'];
$time = strtotime(($_POST['tanggal'])." ".($_POST['bulan'])." ".($_POST['tahun']));
$birthdate = date("Y/m/d", $time);
$email = $_POST['email'];
$avatar = ("upload/" . $_FILES["avatar"]["name"]);

mysql_query("INSERT INTO user (username, password, fullname, birthdate, phonenumber, email, avatar) VALUES ('$username', '$password', '$fullname', '$birthdate', '', '$email', '$avatar')");

move_uploaded_file($_FILES["avatar"]["tmp_name"], "upload/" . $_FILES["avatar"]["name"]);

session_start();
// store session data
$_SESSION['id'] = $username;
$_SESSION['pagenum'] = 1;
header("location:dashboard.php");

mysql_close($con);
?>