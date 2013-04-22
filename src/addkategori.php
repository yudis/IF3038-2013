<?php
	$kategori=$_GET["cat"];
	$jumlah=$_GET["n"];
	$list=$_GET["a"];
	
	$userArray = explode("~",$list);
	
	$con = mysql_connect('localhost', 'progin', 'progin');
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("progin_405_13510029", $con);	
	
	$sql="SELECT category_id FROM category";
	$result = mysql_query($sql);
	$maxID = 0;
	$n = 0;
	
	while ($row = mysql_fetch_array($result)) {
		if ((int)$row["category_id"] > $maxID)
			$maxID = (int)$row["category_id"];
	}
	$maxID++;
	
	$sql="INSERT INTO category VALUES ('".$maxID."', '".$kategori."')";
	mysql_query($sql);
	
	while ($n < $jumlah) {
		$sql="INSERT INTO category_incharge VALUES ('".$maxID."', '".$userArray[$n]."')";
		mysql_query($sql);
		//echo("n=".$n."[".$jumlah."]".$userArray[$n]." - ");
		$n++;
	}
	
	echo ("true");
	
	mysql_close($con);
?>