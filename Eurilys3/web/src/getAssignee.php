<?php
	include "connect.php";
	$output="";
	$result = mysql_query("SELECT username FROM do WHERE idtask='".$_GET['idtask']."'");
	while ($row = mysql_fetch_array($result)){
		$output.=$row['username'].', ';
	}
	$output = substr($output, 0, -2);
	
	echo $output;
?>