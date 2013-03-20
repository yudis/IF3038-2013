<?php
session_start();
if(!isset( $_SESSION['myusername'])){
	header("location:index.php?status=3");
}

include 'database.php';

// Connect to server and select databse.
$con=mysqli_connect($host,$username,$password,$db_name);
if (mysqli_connect_errno()) {
	echo "Failed to connect to MySQL: ".mysqli_connect_error();
}

$member = $_SESSION['id'];
$task = $_POST['task'];
date_default_timezone_set('Asia/Jakarta');
$timestamp = date('Y-m-d H:i:s', time());
$comment = $_POST['komentar'];

mysqli_query($con, "INSERT INTO `comments` (member,task,timestamp,comment) 
				VALUES ($member, $task, '$timestamp', '$comment')");
mysqli_close($con);

header("location:rinciantugas.php?id=".$task);

?>