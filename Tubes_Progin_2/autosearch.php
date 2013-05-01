<?php
include "login.php";
$username = $_SESSION['username'];

$q=$_GET["q"];
$tipe=$_GET["tipe"];

require "config.php";

//lookup all hints from array if length of q>0
if (strlen($q) > 0)
  {
  $hint="";
  if ($tipe == "all result")
  {
	$sql="SELECT name FROM task WHERE name LIKE '%$q%' AND id_task in (SELECT id_task FROM assignee WHERE username='$username')";
	$user = mysqli_query($con,$sql);
	$hasiltask = array();
	while(($user != null) && ($current_user = mysqli_fetch_array($user)))
	{
		$hasiltask[] = $current_user['name'];
	}
	
	if (count($hasiltask) > 0)
	{
		$hint="task";
		for($i=0; $i<count($hasiltask); $i++)
		{
			if (strtolower($q)==strtolower(substr($hasiltask[$i],0,strlen($q))))
			{
				$hint .= "<br>".$hasiltask[$i];
			}
		}
		$hint .= ",";
	}
	
	$sql="SELECT username FROM user WHERE username LIKE '%$q%'";
	$user = mysqli_query($con,$sql);
	$hasiluser = array();
	while(($user != null) && ($current_user = mysqli_fetch_array($user)))
	{
		$hasiluser[] = $current_user['username'];
	}
	
	if (count($hasiluser) > 0)
	{
		$hint.="user";
		for($i=0; $i<count($hasiluser); $i++)
		{
			if (strtolower($q)==strtolower(substr($hasiluser[$i],0,strlen($q))))
			{
				$hint .= "<br>".$hasiluser[$i];
			}
		}
		$hint.=",";
	}
	
	$sql="SELECT name FROM category WHERE name LIKE '%$q%' AND id_cat in (SELECT id_cat FROM joincategory WHERE username='$username') UNION SELECT name FROM category WHERE id_cat in (SELECT id_cat FROM categorycreator WHERE name LIKE '%$q%' AND username='$username')";
	$user = mysqli_query($con,$sql);
	$hasilkategori = array();
	while(($user != null) && ($current_user = mysqli_fetch_array($user)))
	{
		$hasilkategori[] = $current_user['name'];
	}
	
	if (count($hasilkategori) > 0)
	{
		$hint.="kategori";
		for($i=0; $i<count($hasilkategori); $i++)
		{
			if (strtolower($q)==strtolower(substr($hasilkategori[$i],0,strlen($q))))
			{
				$hint .= "<br>".$hasilkategori[$i];
			}
		}
		//$hint.=",";
	}
  }
  else if($tipe == "username")
  {
	$sql="SELECT username FROM user WHERE username LIKE '%$q%'";
	$user = mysqli_query($con,$sql);
	$hasiluser = array();
	while(($user != null) && ($current_user = mysqli_fetch_array($user)))
	{
		$hasiluser[] = $current_user['username'];
	}
	
	if (count($hasiluser) > 0)
	{
		$hint="user";
		for($i=0; $i<count($hasiluser); $i++)
		{
			if (strtolower($q)==strtolower(substr($hasiluser[$i],0,strlen($q))))
			{
				$hint .= "<br>".$hasiluser[$i];
			}
		}
		//$hint.=",";
	}
  }
  else if($tipe=="task")
  {
	//$sql="SELECT name FROM task WHERE name LIKE '%$q%'";
	$sql="SELECT name FROM task WHERE name LIKE '%$q%' AND id_task in (SELECT id_task FROM assignee WHERE username='$username')";
	$user = mysqli_query($con,$sql);
	$hasiltask = array();
	while(($user != null) && ($current_user = mysqli_fetch_array($user)))
	{
		$hasiltask[] = $current_user['name'];
	}
	
	if ($hasiltask)
	{
		$hint="task";
		for($i=0; $i<count($hasiltask); $i++)
		{
			if (strtolower($q)==strtolower(substr($hasiltask[$i],0,strlen($q))))
			{
				$hint .= "<br>".$hasiltask[$i];
			}
		}
		//$hint .= ",";
	}
  }
  else if ($tipe=="category")
  {
	//$sql="SELECT name FROM category WHERE name LIKE '%$q%'";
	$sql="SELECT name FROM category WHERE name LIKE '%$q%' AND id_cat in (SELECT id_cat FROM joincategory WHERE username='$username') UNION SELECT name FROM category WHERE id_cat in (SELECT id_cat FROM categorycreator WHERE name LIKE '%$q%' AND username='$username')";
	$user = mysqli_query($con,$sql);
	$hasilkategori = array();
	while(($user != null) && ($current_user = mysqli_fetch_array($user)))
	{
		$hasilkategori[] = $current_user['name'];
	}
	
	if (count($hasilkategori) > 0)
	{
		$hint="kategori";
		for($i=0; $i<count($hasilkategori); $i++)
		{
			if (strtolower($q)==strtolower(substr($hasilkategori[$i],0,strlen($q))))
			{
				$hint .= "<br>".$hasilkategori[$i];
			}
		}
		//$hint.=",";
	}
  }
  /*
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
    }*/
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