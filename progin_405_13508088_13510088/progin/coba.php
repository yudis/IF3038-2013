<?php
$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_database = "tubes2";
$bd = mysql_connect($mysql_hostname, $mysql_user);
mysql_select_db($mysql_database, $bd) or die("Opps some thing went wrong");
?>