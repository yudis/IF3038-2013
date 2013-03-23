<?php
	session_start();
	ob_start();
	if (isset($_SESSION['username'])) {
		$username = $_SESSION['username']; 
	}
	
	/* Configuring Server & Database */
	$host        =    'localhost';
	$user        =    'root';
	$password    =    '';
	$database    =    'progin_405_13510086';
	$con        =    mysql_connect($host,$user,$password) or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');
		
	$taskID	= $_GET["taskID"];
	$assID = $_GET["assID"];
	
	$query = "DELETE FROM `task_asignee` WHERE task_id='$taskID' AND username='$assID'";
	mysql_query($query);
?>