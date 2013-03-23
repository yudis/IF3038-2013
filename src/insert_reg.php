<?php

include 'koneksi.php';

$user = $_POST["username"];
$pass = $_POST["password"];
$name = $_POST["long_name"];
$email = $_POST["email"];
$date = $_POST["birth_date"];
$ava = $_FILES["avatar_upload"]["name"] ;

//Insert data to database
$query = "INSERT INTO `user`(`username`, `password`, `fullname`, `email`, `birthdate`, `avatar`) VALUES ('$user','$pass','$name','$email','$date','$ava')" ;
mysql_query($query) ;

// Upload File
$allowedExts = array("jpg", "jpeg", "gif", "png");
$temp = (explode(".", $_FILES["avatar_upload"]["name"]));
$extension = end($temp);
if ($_FILES["avatar_upload"]["error"] > 0) {
    echo "Return Code: " . $_FILES["avatar_upload"]["error"] . "<br>";
} else {
    if (file_exists("upload/" . $_FILES["avatar_upload"]["name"])) {
        echo $_FILES["avatar_upload"]["name"] . " already exists. ";
    } else {
        move_uploaded_file($_FILES["avatar_upload"]["tmp_name"], "../img/avatar/" . $_FILES["avatar_upload"]["name"]);
        echo "Stored in: " . "../img/avatar/" . $_FILES["avatar_upload"]["name"];
    }
}

header("location:dashboard.html") ;
?>
