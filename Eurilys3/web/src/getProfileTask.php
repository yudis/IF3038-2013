<?php
	include "connect.php";
	$arrayCurrentTask ="";
	$arrayFinishedTask="";
	$output="";
	$id ="";
	$result = mysql_query("SELECT idtask FROM do WHERE username ='".$_COOKIE['name']."'");
	while ($row = mysql_fetch_array($result)){
		$result2 = mysql_query("SELECT * FROM task WHERE idtask ='".$row['idtask']."'");
		$row2 = mysql_fetch_array($result2);
		if ($row2['status'] == '0')
			$arrayCurrentTask = $arrayCurrentTask."<li>".$row2['taskname']."</li>";
		else
			$arrayFinishedTask = $arrayFinishedTask."<li>".$row2['taskname']."</li>";	
	}
	
	$output = $arrayCurrentTask."|@|".$arrayFinishedTask;
	echo $output;
?>