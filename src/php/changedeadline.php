<?php
require('init_function.php');
$deadlinetime = $_GET['deadlinetime'].":00";
$taskid = $_GET['taskid'];

$con = getConnection();
$query = "UPDATE task SET deadline = '$deadlinetime' WHERE taskid='$taskid'";
mysqli_query($con,$query);
echo "Deadline : ".$deadlinetime;
?>