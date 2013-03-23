<?php
require('init_function.php');

$con = getConnection();
$email = $_GET['email'];
$userid = $_GET['userid'];

$query = "UPDATE user SET email = '$email' WHERE username='$userid'";
mysqli_query($con,$query);

echo $email;

?>