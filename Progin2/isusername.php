<?php
$con = mysql_connect("localhost:3306","root","");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);

// Fill up array with names
$result = mysql_query("SELECT * FROM user");
while($row = mysql_fetch_array($result))
	$a[]= $row['username'];

//get the q parameter from URL
$q=$_GET["q"];

//lookup all hints from array if length of q>0
if (strlen($q) > 0) {
  $i=0;
  $response="<font color=\"red\">Username tidak ditemukan</font>";
  while(($i < count($a)) && ($response == "<font color=\"red\">Username tidak ditemukan</font>")) {
    if (strtolower($q)==strtolower($a[$i])) {
      $response="<font color=\"green\">Username ditemukan</font>";
    }
	$i++;
  }
}

//output the response
echo $response;
?>