<?php
	require '../utilities/db.php';
	require '../utilities/model.php';
	require '../utilities/view.php';
	require '../models/kategori.php';
	$q=$_GET["q"];
	$Arr=explode(',', $_GET["Arr"]);
	session_start();
	$kategori = new Kategori();
	$kategori->NewKategori($q,$_SESSION['user']);
	$i=0;
	while(!empty($Arr[$i]))
	{
		if($Arr[$i]!=$_SESSION['user'])
		{
			$kategori->addNewestCoordinator($Arr[$i]);
		}
		$i++;
	}
	
	$kategori = new Kategori();
	$i=0;
	$success= $kategori->getAllKategori();
	
	while (!empty($success[$i]))
	{
		$n=0;
		$users=$kategori->getUser($success[$i]["id"]);
		while(!empty($users[$n]))
		{
			if($users[$n]["user"]==$_SESSION["user"])
			{
				$id_kategori=$success[$i]["id"];
				echo "<li><a name='",$id_kategori,"' href=\"\" onclick=\"loadtugas('",$id_kategori,"');setChosen('",$id_kategori,"'); return false;\" >";
				echo $success[$i]["nama"];
				echo "</a></li>";
				break;
			}
			else
			{
				$n++;
			}
		}
		$i++;
	}
?>