<?php
	session_start();
	$full_url = $_SESSION['full_url'];
	session_destroy();
	header("Location:".$full_url."index.php");
?>