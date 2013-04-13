<?php
	include "connect.php";
	$output="";
	$result = mysql_query("SELECT idcat FROM able WHERE username ='".$_COOKIE['name']."'");
	while ($row = mysql_fetch_array($result)){
		$result2 = mysql_query("SELECT catname FROM category WHERE idcat ='".$row['idcat']."'");
		$row2 = mysql_fetch_array($result2);
		$onclick = '"categoryActive(\''. $row['idcat'].'\')"'; 
		$output=$output.'<li><a href="#" onclick='.$onclick.'>'.$row2['catname'].'</a></li>';
	}	
	echo $output;
?>