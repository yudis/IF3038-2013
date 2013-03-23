<?php
	include "login.php";
	require "config.php";
	$username = $_SESSION['username'];
	$nama = $_POST['cate'];
	$join = $_POST['join'];
	
	$sql = "INSERT INTO category(`name`) VALUES ('$nama')";
	mysqli_query($con,$sql);
	
	$sql2 = "SELECT id_cat FROM category WHERE name = '$nama'";
	$user = mysqli_query($con,$sql2);
	$hasil = mysqli_fetch_array($user);
	
	$idCat = $hasil['id_cat'];
	
	$sql3 = "INSERT INTO categorycreator(`username`, `id_cat`) VALUES ('$username', '$idCat')";
	mysqli_query($con,$sql3);
	if ($join != "")
	{
		$sql4 = "INSERT INTO joincategory (`id_cat`,`username`) VALUE ('$idCat','$join')";
		mysqli_query($con,$sql4);
	}
	
	$sql5 = "INSERT INTO joincategory (`id_cat`,`username`) VALUE ('$idCat','$username')";
	mysqli_query($con,$sql5);

	header('Location: Dashboard.php');
?>