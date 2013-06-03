<?php
	require_once("db.php");
	require_once("login/registration.php");
	
	// soap server
	$uri = "http://localhost/GitHub/IF3038-2013/src/server/soapserver.php/";
	//$uri = "http://tubes4asdasd.aws.af.cm/soapserver.php/";
	$server = new SoapServer(null, array('uri' => $uri));
	$server->addFunction("registration"); 
	$server->handle(); 
?>