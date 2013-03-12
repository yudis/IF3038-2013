<?php

require('init_function.php');

$con = getConnection();
$idinput = $_GET['idinput'];

$query = "SELECT count(*) as isexist FROM user WHERE username = '$idinput'";
$result = mysqli_query($con,$query);
$row = mysqli_fetch_array($result);
if(strcmp($row['isexist'],"0") == 0){
	echo "";
}else{
	echo "Username already exist, please try another username";
}
?>