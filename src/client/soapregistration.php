<?php
	$username = $_POST['username'];
	$password = sha1($_POST['password']);
	$namaleng = $_POST['namaleng'];
	$tanggal = $_POST['tanggal'];
	$email = $_POST['email'];
	$fakepath = $_FILES['avatar']['tmp_name'];
	$avatar = $_FILES['avatar']['name'];
	move_uploaded_file($fakepath, 'pict/'.$avatar);
	
	// soap client
	//$soapserveraddress = "http://localhost/GitHub/IF3038-2013/src/server/soapserver.php/";
	//$uri = "urn://localhost/GitHub/IF3038-2013/src/server/soapserver.php/"; 
	
	$soapserveraddress = "http://tubes4asdasd.aws.af.cm/soapserver.php/";
	$uri = "urn://tubes4asdasd.aws.af.cm/soapserver.php/";
	$client = new SoapClient(null, array(
	  'location' => $soapserveraddress,
      'uri'      => $uri,
	  'trace'    => 1 ));
	$status = $client->__soapCall("registration", array($username, $password, $namaleng, $tanggal, $email, $avatar));
	//$_SESSION['user_id'] = (int)$user_id;
	//echo '<script>alert("keren");</script>';
	//header('Location: dashboard/index.php');
	header('Location: dashboard/index.php');
	//echo $status;
?>