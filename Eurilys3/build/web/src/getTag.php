<?php
	include "connect.php";
	$output="";
	$result = mysql_query("SELECT name FROM tag WHERE idtask='".$_GET['idtask']."'");
	while ($row = mysql_fetch_array($result)){
		$output.=$row['name'].', ';
	}
	$output = substr($output, 0, -2);
	
	echo $output;
?>