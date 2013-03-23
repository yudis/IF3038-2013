<?php
require('init_function.php');

$con = getConnection();
$birthday = $_GET['birthday'];
$userid = $_GET['userid'];

$query = "UPDATE user SET birthday = '$birthday' WHERE username='$userid'";
mysqli_query($con,$query);

echo $birthday;

?>