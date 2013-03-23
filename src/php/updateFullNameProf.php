<?php
require('init_function.php');

$con = getConnection();

$newname = $_GET['name'];
$userid = $_GET['userid'];


$query = "UPDATE user SET fullname = '$newname' WHERE username='$userid'";
mysqli_query($con,$query);

echo $newname;

?>