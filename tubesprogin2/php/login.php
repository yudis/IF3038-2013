<?php

$dbhost="localhost";
$dbname="progin_405_13510060";
$dbusername="progin";
$dbpassword="progin";
$dbcon=mysql_connect($dbhost,$dbusername,$dbpassword);
$connection_string=mysql_select_db($dbname);

mysql_connect($dbhost,$dbusername,$dbpassword);
mysql_select_db($dbname);

if(isset($_GET['uname']) && isset($_GET['passw'])){

$uname = $_GET['uname'];
$passw = $_GET['passw'];

$getUser_sql = 'SELECT * FROM pengguna WHERE username="'. $uname . '" AND password = "' . $passw . '"';
$getUser = mysql_query($getUser_sql);
$getUser_result = mysql_fetch_assoc($getUser);
$getUser_RecordCount = mysql_num_rows($getUser);

if($getUser_RecordCount < 1){ echo '0';} 
else { echo $getUser_result['username'];
		session_start();
		$_SESSION['uname']='username';
		$_SESSION['loggedin']=1;}
}

?>