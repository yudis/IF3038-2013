<?php
# FileName="Connection_php_mysql.htm"
# Type="MYSQL"
# HTTP="true"
$hostname_eurilys = "localhost";
$database_eurilys = "progin_405_13510086";
$username_eurilys = "root";
$password_eurilys = "";
$eurilys = mysql_pconnect($hostname_eurilys, $username_eurilys, $password_eurilys) or trigger_error(mysql_error(),E_USER_ERROR); 
?>