<?php
include 'session.php';
include 'database.php';

// Connect to server and select database
$con = mysqli_connect($hostname,$username,$password,$db_name);
if (mysqli_connect_errno()) {
	echo "Failed to connect to MySQL: ".mysqli_connect_errno();
}

$task_id = $_POST['deltask'];
mysqli_query($con, "DELETE FROM tasks
				WHERE id=$task_id");

mysqli_close($con);
header("location:dashboard.php");
?>