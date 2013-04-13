<?php
session_start();
$con = mysqli_connect("localhost","root","", 'tubes2');
if (mysqli_connect_errno()) {
  echo "Failed to onnect to MYSQL: " . mysqli_connect_error();
}



$username = $_POST['username'];
$email = $_POST['email'];
$password = $_POST['password'];
$re_password = $_POST['confpassword'];
$fullname = $_POST['fullname'];
$sex = $_POST['sex'];
$tanggalbirthday = $_POST['tanggalbirthday'];
$bulanbirthday = $_POST['bulanbirthday'];
$tahunbirthday = $_POST['tahunbirthday'];
$avatar = $_POST['avatar'];


if($username == "")
{
die("Opps! You don't enter a username!");
}

if($password == "" || $re_password == "")
{
die("Opps! You didn't enter one of your passwords!");
}

if($password != $re_password)
{
die("Ouch! Your passwords don't match! Try again.");
}

echo ('test1');
if(!mysqli_query($con, "INSERT INTO user (username, password, fullname, email, sex, tanggalbirthday, bulanbirthday, tahunbirthday, avatar)
VALUES ('$username', '$email', '$password', '$fullname', '$sex', '$tanggalbirthday', '$bulanbirthday', '$tahunbirthday', '$avatar')"))
{
die("We could not register you due to a mysql error (Contact the website owner if this continues to happen.)");
}
echo ('test2');

$_SESSION['login_user'] = $username;
echo($_SESSION['login_user']);
if (isset($_SESSION['login_user'])) {
  echo('logged in');
}
header('Location: dashboard.php');

?>