<?php
include 'session.php';
include 'database.php';

// Connect to server and select databse.
$con=mysqli_connect($host,$username,$password,$db_name);
if (mysqli_connect_errno()) {
	echo "Failed to connect to MySQL: ".mysqli_connect_error();
}

$cat_id = $_POST['id'];
mysqli_query($con, "DELETE FROM categories 
				WHERE id=$cat_id");

mysqli_close($con);
header("location:dashboard.php");
?>