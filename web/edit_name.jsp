<?php
/* edit_name.php */

$nama = $_GET["nama"];
$old_username = $GET["username"];

$con=mysql_connect("localhost","progin","progin","progin_439_13510002");
if (mysql_connect_errno($con)){
	echo "Failed to connect to MySQL: " . mysql_connect_error();
}

$sql_name = "UPDATE user set (name = $nama) where (username = $old_username)";
if (!mysql_query ($con , $sql_name)){
	die('Error : '.mysql_error());
}

?>