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
    $result = mysqli_query($con,"SELECT fullname, tanggalLahir FROM user WHERE username='$curUser'");
    $row = mysqli_fetch_array($result);
    //mengantisipasi adanya kekosongan field fullname
    if ($newFullName == "-111") {    
        $newFullName = $row['fullname'];
    }
    //mengantisipasi adanya kekosongan field birthdate
    if ($newBirthdate == "-222") {
        $newBirthdate = $row['tanggalLahir'];
    }
    
    //update database
    $sql = "UPDATE user SET fullname='$newFullName', tanggalLahir='$newBirthdate',password='$newPassword' WHERE username='$curUser'";
    mysqli_query($con,$sql);
    
    //$response = $newFullName." ".$newBirthdate." ".$newPassword." ".$newFileName;
    //$response = $newFullName.$newBirthdate;
    //echo $response;
    
    header("Content-type: text/xml");
    echo "<?xml version='1.0' encoding='utf-8'?>";
    echo "<response>";
    echo "<fullname>".$newFullName."</fullname>";
    echo "<birthdate>".$newBirthdate."</birthdate>";
    //echo "<password>".$newPassword."</password>"; tidak perlu dikirimkan ke client
    //echo "<filename>".$newFileName."</filename>";
    echo "</response>";
?>