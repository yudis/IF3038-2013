<?php
$con = mysql_connect("localhost:3306","progin","progin");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);

// username and password sent from form 
$myusername = $_POST['myusername']; 
$mypassword = $_POST['mypassword']; 

$result = mysql_query("SELECT * FROM user WHERE username='$myusername' and password='$mypassword'");

// Mysql_num_row is counting table row
$count=mysql_num_rows($result);

// If result matched $myusername and $mypassword, table row must be 1 row
if($count >= 1){
	session_start();
	// store session data
	$_SESSION['id'] = $myusername;
	$_SESSION['pagenum'] = 1;
	header("location:dashboard.php");
}
else {
	header("location:index.php?q=0");
}

mysql_close($con);
?>