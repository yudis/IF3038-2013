<?php
include "connect.php";
$name = $_GET["name"];
// Selec Table
$result = mysql_query("SELECT * FROM user WHERE username ='".$name."'");
if (mysql_fetch_array($result)>0)echo "true";	
else echo "false"; 
?>