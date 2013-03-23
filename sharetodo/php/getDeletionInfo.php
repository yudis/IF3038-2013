<?php
    session_start();
    
    $task = $_GET['task'];
    $curUser = $_SESSION['username']; //mengambil username yang sedang aktif
    
    //create connection
    $con = mysqli_connect("127.0.0.1","root","root","distributedAgenda");

    //check the connection
    if (mysqli_connect_errno($con)) {
        echo "Gagal melakukan koneksi ke MySQL : " . mysqli_connect_error();
    }
    
    $result = mysqli_query($con,"SELECT creatorTaskName FROM task WHERE namaTask='$task'");
    $row = mysqli_fetch_array($result); //eksekusi hanya sekali karena tugas bersifat unik
    
    if ($curUser == $row['creatorTaskName']) {
        //melakukan penghapusan task
        //penghapusan task meliputi banyak hal terkait, seperti menghapus tag, attachment, komentar, dll
        mysqli_query($con,"DELETE FROM usertotask WHERE namaTask='$task'");
        mysqli_query($con,"DELETE FROM tasktoassignee WHERE namaTask='$task'");
        mysqli_query($con,"DELETE FROM komentar WHERE namaTask='$task'");
        mysqli_query($con,"DELETE FROM tagging WHERE namaTask='$task'");
        mysqli_query($con,"DELETE FROM attach WHERE namaTask='$task'");
        mysqli_query($con,"DELETE FROM task WHERE namaTask='$task'");
        echo "1";
    } else {
        echo "0";
    }
?>