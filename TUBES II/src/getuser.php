<?php

session_start();

$q=$_GET["q"];
$r=$_GET["r"];

$check="false";-

$con = mysql_connect('localhost', 'progin', 'progin');
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin", $con);

$usr="SELECT Username FROM profil";

$qusr = mysql_query($usr);

while ($row = mysql_fetch_array($qusr))
{
if ($row["Username"] == $q){
	$check = "true";
}
}

if ($check == "true") {

$pwd="SELECT Password FROM profil WHERE Username = '".$q."'";

$pswd = mysql_query($pwd);


while($raw = mysql_fetch_array($pswd)) 
	{
	if ($raw['Password'] == $r) {
		$_SESSION['login'] = $q;
		 echo "Accepted";
	}else {
		echo "Password Salah";
	}
	}
}else {
	echo "User not exist";
}

mysql_close($con);
?>