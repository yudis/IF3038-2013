<?php
include "login.php";
require "config.php";

$q=$_GET["q"];
$a= array();
$sql = "SELECT username FROM user WHERE username LIKE '%$q%'";
$user = mysqli_query($con,$sql);
while (($user != null ) && ($hasil = mysqli_fetch_array($user)))
{
	$a[] = $hasil['username'];
}

//lookup all hints from array if length of q>0
if (strlen($q) > 0)
  {
  $hint="";
  for($i=0; $i<count($a); $i++)
    {
    if (strtolower($q)==strtolower(substr($a[$i],0,strlen($q))))
      {
      if ($hint=="")
        {
        $hint=$a[$i];
        }
      else
        {
        $hint=$hint." , ".$a[$i];
        }
      }
    }
  }

// Set output to "no suggestion" if no hint were found
// or to the correct values
if ($hint == "")
  {
  $response="";
  }
else
  {
  $response=$hint;
  }

//output the response
echo $response;
?>