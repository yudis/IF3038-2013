<?php
    session_start();
    
    $newFullName = $_POST["newFullName"];
    $newBirthdate = $_POST["newBirthdate"];
    $newPassword = $_POST["newPassword"];
    $newFileName = $_POST["newFileName"];
    
    //create connection
    $con = mysqli_connect("127.0.0.1","root","root","distributedAgenda");

    //check the connection
    if (mysqli_connect_errno($con)) {
        echo "Gagal melakukan koneksi ke MySQL : " . mysqli_connect_error();
    }
    
    $curUser = $_SESSION['username']; //mengambil username yang sedang aktif
    //$sql = "UPDATE user SET fullname = '" + $newFullName + "' WHERE username = '" + $curUser + "'";
    $sql = "UPDATE user SET fullname='$newFullName' WHERE username='$curUser'";
    mysqli_query($con,$sql);
    
    //$response = $newFullName." ".$newBirthdate." ".$newPassword." ".$newFileName;
    $response = $newFullName;
    echo $response;
    
    //header("Location:dashboard.php");
?>