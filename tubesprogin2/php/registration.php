<?php

$dbhost="localhost";
$dbname="progin_405_13510060";
$dbusername="progin";
$dbpassword="progin";
$dbcon=mysql_connect($dbhost,$dbusername,$dbpassword);
$connection_string=mysql_select_db($dbname);

mysql_connect($dbhost,$dbusername,$dbpassword);
mysql_select_db($dbname);

if(isset($_GET['uname']) && isset($_GET['passw']) && isset($_GET['fullname']) && isset($_GET['email']) && isset($_GET['birthdate'])){

$uname = $_GET['uname'];
$passw = $_GET['passw'];
$fullname = $_GET['fullname'];
$email = $_GET['email'];
$birthdate = $_GET['birthdate'];
$user_id = 'SELECT MAX(user_ID) from pengguna';
$user_id = $user_id + 1;

$insertuser_SQL = 'INSERT INTO pengguna (user_ID, username, password, full_name, avatar, birthdate, email) VALUES ("'. $user_id . '", "'. $uname . '", "'. $passw . '", "'. $fullname . '", "'. $avatar . '", "'. $birthdate . '", "'. $email . '");';

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