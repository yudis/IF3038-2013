<?php
	session_start();
	
	if (!ISSET($_SESSION['user_id']))
	{
		Header("Location:".$_SESSION['base_url']."index.php");
	}
?>