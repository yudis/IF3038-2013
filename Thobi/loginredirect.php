<?php
	$username=$_POST["uname"];
	header( "Location: dashboard.php?uname=".$username."&cat=all" ) ;

?>