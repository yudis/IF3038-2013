<?php
include "connect.php";
// User Name
$a[]="";
$result = mysql_query("SELECT username FROM user");
while ($row = mysql_fetch_array($result)){
	$a[]= $row['username'];
}

// Judul Kategori
$result = mysql_query("SELECT catname FROM category");
$b[]="";
while ($row = mysql_fetch_array($result)){
	$b[]= $row['catname'];
}

// Task Tag
$result = mysql_query("SELECT name FROM tag");
$c[]="";
while ($row = mysql_fetch_array($result)){
	$c[]= $row['name'];
}

//get the q parameter from URL
$q=$_GET["val"];
$userName=$_GET["userName"];
$judulKategori=$_GET["judulKategori"];
$taskTag=$_GET["taskTag"];

if (strlen($q) > 0){
  $hint="";
  // Cari Hint Untuk User Name
  if($userName == 1){
	for($i=0; $i<count($a); $i++)
    {
    if (strtolower($q)==strtolower(substr($a[$i],0,strlen($q))))
      {
		$hint = $hint."<option value='".$a[$i]."'></option>"; 
      }
    }
  }
  // Cari Hint Untuk Judul Kategori
  if($judulKategori == 1){
	for($i=0; $i<count($b); $i++)
    {
    if (strtolower($q)==strtolower(substr($b[$i],0,strlen($q))))
      {
		$hint = $hint."<option value='".$b[$i]."'></option>"; 
      }
    }
  }
  // Cari Hint Untuk Task dan Tag
  if($taskTag == 1){
	for($i=0; $i<count($c); $i++)
    {
    if (strtolower($q)==strtolower(substr($c[$i],0,strlen($q))))
      {
		$hint = $hint."<option value='".$c[$i]."'></option>"; 
      }
    }
  }
	$response=$hint;
	//output the response
	echo $response;
}
?>