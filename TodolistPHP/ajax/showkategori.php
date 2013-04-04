<?php
	require '../utilities/db.php';
	require '../utilities/model.php';
	require '../utilities/view.php';
	require '../models/kategori.php';
	session_start();
	$kategori = new Kategori();
	$i=0;
	$success = $kategori->getAllKategori($i);
	while (!empty($success[$i]))
	{
		$n=0;
		$users=$kategori->getUser($success[$i]["id"]);
		while(!empty($users[$n]))
		{
			if($users[$n]["user"]==$_SESSION["user"]["username"])
			{
				$id_kategori=$success[$i]["id"];
				echo '<option value="',$success[$i]["id"],'">',$success[$i]["nama"],'</option>';
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