<?php
	session_start();
	unset($_SESSION['userlistapp']);
	header("Location: ../index.php");
?>