<?php
$username=$_GET["username"];
$password=$_GET["password"];
$email=$_GET["email"];
$name=$_GET["name"];
$date=$_GET["date"];
$month=$_GET["month"];
$year=$_GET["year"];
$avatar=$_GET["avatar"];

$con = mysql_connect('localhost', 'progin', 'progin');
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin", $con);

$usr="SELECT Username FROM profil";
$check="false";

$qusr = mysql_query($usr);

while ($row = mysql_fetch_array($qusr))
{
if ($row["Username"] == $username){
	$check = "true";
}
}

$dcheck="false";

if ($check=="false") {

$eml="SELECT Email FROM profil";
$qeml = mysql_query($eml);

while ($rml = mysql_fetch_array($qeml))
{
if ($rml["Email"] == $email){
	$dcheck = "true";
}
}

if ($dcheck=="false") {
$sql="INSERT INTO profil (Username,Password,Fullname,Avatar,Email) VALUES ('$username','$password','$name','$avatar','$email')";

$add=mysql_query($sql);

echo "Accepted";
}
else {
echo "Email Exist";
}
}else {
echo "Username Exist";
}

mysql_close($con);
?>