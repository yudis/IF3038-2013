<?php
session_start();
include("coba.php");
if($_SERVER["REQUEST_METHOD"] == "POST")
{
$myusername=addslashes($_POST['username']); 
$mypassword=addslashes($_POST['password']); 

$sql="SELECT username FROM user WHERE username='$myusername' and password='$mypassword'";
$result=mysql_query($sql);
$row=mysql_fetch_array($result);
$count=mysql_num_rows($result);

if($count==1)
{
//session_register("myusername");
	$_SESSION['login_user']=$myusername;
	header('Location: http://localhost/progin/dashboard.php');
}
else {
	$error="Your Login Name or Password is invalid";
	print "Error";
}
}

?>
