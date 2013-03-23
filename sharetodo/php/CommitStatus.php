<?php
    session_start();
    
    $statusUpdated = $_GET['stat'];
    $namaTask = $_GET['task'];
    
    //create connection
    $con = mysqli_connect("127.0.0.1","root","root","distributedAgenda");

    //check the connection
    if (mysqli_connect_errno($con)) {
        echo "Gagal melakukan koneksi ke MySQL : " . mysqli_connect_error();
    }else {
        $result = mysqli_query($con,"UPDATE task SET status='$statusUpdated' WHERE namaTask='$namaTask'");
        echo "Update status berhasil";
    }
?>