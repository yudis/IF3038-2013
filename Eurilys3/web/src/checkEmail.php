<?php
include "connect.php";
$email = $_GET["email"];
// Selec Table
$result = mysql_query("SELECT * FROM user WHERE email ='".$email."'");
if (mysql_fetch_array($result)>0)echo "true";	
else echo "false";
?>