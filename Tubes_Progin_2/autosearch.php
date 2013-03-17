<?php
$a[]="tttttt1";
$a[]="tttttt2";
$a[]="tttttt3";
$a[]="tttttt4";
$a[]="tttttt5";

require "config.php";
$sql = "SELECT name FROM task";
$user = mysqli_query($con,$sql);

while (($user != null) && ($current_user = mysqli_fetch_array($user)))
{
	$a[]=$current_user['name'];
}

//get the q parameter from URL
$q=$_GET["q"];

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
        //$hint="<option value=\"".$a[$i]."\">";
		//$hint="<div id=\"ac\" onclick=\"autocomplete()\">".$a[$i]."</div>";
		$hint = $a[$i];
        }
      else
        {
        //$hint=$hint."<option value=\"".$a[$i]."\">";
		//$hint=$hint."<div id=\"ac\" onclick=\"autocomplete()\">".$a[$i]."</div>";
		$hint .= "<br>".$a[$i];
		}
      }
    }
  }

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