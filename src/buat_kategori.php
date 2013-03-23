<?php
	require_once("connectdb.php");
		
	$query1 = "INSERT INTO kategori (nama, pembuat) VALUES('".$_POST["catname"]."',".$_POST["pembuat"].")";
	$query2 = "INSERT INTO assignee_has_kategori (accounts_idaccounts, kategori_idkategori) VALUES(".$_POST["pembuat"].",(SELECT idkategori from kategori where nama = '".$_POST["catname"]."' and pembuat = ".$_POST["pembuat"]."))";
	
	$result = mysql_query($query1, $sql);
	$result = mysql_query($query2, $sql);
	
	$list_assignee = explode(", ", $_POST["catass"]);
	
	for($i = 0; $i < sizeof($list_assignee); $i++)
	{
		if(strlen($list_assignee[$i]) > 0)
		{
			echo $list_assignee[$i].".<br/>";
			$query3 = "INSERT INTO assignee_has_kategori (accounts_idaccounts, kategori_idkategori) VALUES((SELECT idaccounts FROM accounts WHERE username = '".$list_assignee[$i]."'),(SELECT idkategori from kategori where nama = '".$_POST["catname"]."' and pembuat = ".$_POST["pembuat"]."))";
			$result = mysql_query($query3, $sql);
		}
	}
	mysql_close($sql);
	header("Location: dashboard.php");
?>