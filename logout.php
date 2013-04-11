<?php
	session_start();
	echo "<h1>You've been logged out!</h1>";
	session_destroy();
	header("refresh: 2; index.php");
?>