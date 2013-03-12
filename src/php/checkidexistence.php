<?php

require('init_function.php');

$con = getConnection();
$id = $_GET['id'];
$pass = $_GET['pass'];

$query = "SELECT count(*) as isexist FROM user WHERE username = '$id' AND password = '$pass'";
$result = mysqli_query($con,$query);
$row = mysqli_fetch_array($result);
if(strcmp($row['isexist'],"0") == 0){
	echo "false";
}else{
	session_start();
    $_SESSION["userlistapp"]=$id;
	echo "true";
}
?>