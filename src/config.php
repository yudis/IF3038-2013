<?php
$mysql_hostname = "localhost";
$mysql_user = "progin";
$mysql_password = "progin";
$mysql_database = "progin_405_13510074";
$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Opps some thing went wrong");
mysql_select_db($mysql_database, $bd) or die("Opps some thing went wrong");
?>