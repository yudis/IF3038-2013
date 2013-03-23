<?php
require('init_function.php');
$username = $_GET['username'];

$deletedfile = "../avatar/".getAvatar($username);
unlink($deletfile);

$file = $_FILES['changeAvatar'];
$filename = $file['name'];
$extension = end(explode(".", $filename));
$avatarname = $username.".".$extension;
upload_avatar($file,$username);

header("Location: ../page/profile.php?username=$username");
?>