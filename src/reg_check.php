<?php

include 'koneksi.php';

$u = $_GET["u"];
$e = $_GET["e"];

$query = "SELECT * FROM user WHERE username = '$u'" ;
$query2 = "SELECT * FROM user WHERE email = '$e'" ;

$result = mysql_query($query) ;
$result2 = mysql_query($query2) ;

$count = mysql_num_rows($result) ;
$count2 = mysql_num_rows($result2) ;

echo "$count;$count2;$e";
?>
