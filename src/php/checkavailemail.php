<?php

require('init_function.php');

$con = getConnection();
$emailinput = $_GET['emailinput'];

$query = "SELECT count(*) as isexist FROM user WHERE email = '$emailinput'";
$result = mysqli_query($con,$query);
$row = mysqli_fetch_array($result);
if(strcmp($row['isexist'],"0") == 0){
	echo "asd";
}else{
	echo "Email already exist, please use another email";
}
?>