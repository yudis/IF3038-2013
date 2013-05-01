<?php
	require '../utilities/db.php';
	require '../utilities/model.php';
	require '../utilities/view.php';
	require '../models/kategori.php';
	
	session_start();
	$kategori = new Kategori();
	$i=0;
	$success= $kategori->getAllKategori();
	$success2= $kategori->getAssigneeCat($_SESSION["user"]["username"]);
	while (!empty($success[$i]))
	{
		$n=0;
		$users=$kategori->getUser($success[$i]["id"]);
		while(!empty($users[$n]))
		{
			if($users[$n]["user"]==$_SESSION["user"]["username"])
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
	$x=0;
	while (!empty($success2[$x]))
	{
		$id_kategori=$success2[$x]["id"];
		echo "<li><a name='",$id_kategori,"' href=\"\" onclick=\"loadtugas('",$id_kategori,"');setChosen('",$id_kategori,"'); return false;\" >";
		echo $success2[$x]["nama_kategori"];
		echo "</a></li>";
		$x++;
	}
?>