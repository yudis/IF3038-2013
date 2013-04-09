<?php
  session_start();
	$_uname = $_GET['username'];
	$_passwd = $_GET['passwd'];
	$found = false;
	require("dbfunc.php");
	require("datefunc.php");
	$tgl = date("Y-m-d H:i:s");
	
	$qres = mysql_query("SELECT * FROM user");
	while($row = mysql_fetch_array($qres)) {
		if (strcmp($row['username'],$_uname) == 0) {
			if (strcmp($row['password'],$_passwd) == 0) {
				$found = true;
				$join=$row['djoin'];
				break;
			}
		}
	}
	if ($found) {
		echo "Welcome, ".$_uname;
		$_SESSION['username'] = $_uname;
		//if (diffHari($tgl,$join)>=10)
	} else {
		echo "Invalid username/password";
	}
	mysql_close($con);
?>

