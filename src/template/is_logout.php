<?php
	$session_time = 30*24*60*60;
	ini_set('session.gc-maxlifetime', $session_time);

	session_start();
	if (!ISSET($_SESSION['base_url']))
	{
		$folder = substr($_SERVER['SCRIPT_NAME'], 0, strrpos($_SERVER['SCRIPT_NAME'], "/")+1);  
		$protocol = (ISSET($_SERVER['HTTPS']) && $_SERVER['HTTPS'] != "off") ? "https" : "http";
		$_SESSION['base_url'] = $protocol . "://" . $_SERVER['HTTP_HOST'] . $folder;
	}
	
	if (ISSET($_SESSION['user_id']))
	{
		Header("Location:".$_SESSION['base_url']."dashboard.php");
	}
?>