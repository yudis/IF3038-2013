<?php
	session_start();
	$base_url = $_SESSION['base_url'];
	session_destroy();
	header("Location:".$base_url."index.php");
?>