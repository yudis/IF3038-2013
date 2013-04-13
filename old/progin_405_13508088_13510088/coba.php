<?php
	$con = mysql_connect("localhost","root","");
	if (!$con)
	  {
	  die('Could not connect: ' . mysql_error());
	  }
	mysql_select_db("tubes2", $con);
	$result = mysql_query('SELECT username FROM user WHERE username = "firauliya"');
	while($row = mysql_fetch_array($result))
	  {
	  echo $row['username'];
	  }
//$username =$_POST['username'];
//$password =md5($_POST['password']);
/*
$query =mysql_query("SELECT * FROM users WHERE username='$username' AND password='$password'");
$data_user =mysql_fetch_array($query);

if($data_user['username'] == $username and $data_user['password'] == $password){

session_register('username','password');
header('location:index.php');
}else {

$error ="";
if(empty($username) and empty($_POST['password'])){
	$error ="<b>Username</b> dan <b>Password</b> kosong";
}
else if(empty($username)){
$error ="<b>Username</b> kosong";
}else if(empty($_POST['password'])){
$error ="<b>Password</b> kosong";
}else{
$error ="<b>Username</b> dan <b>Password</b> tidak sesuai";
}
echo "<h3>Login Gagal:</h3><p>$error. <br /><a href='login.php'>Kembali</a><p>";
}*/
?>
	