<?php
    session_start();
    
    $task_name = $_POST["task_name"];
    $deadline = $_POST["deadline"];
    $kategori = $_POST["kategori"];
    echo $kategori;
    
//    $creator = $_SESSION["username"];
    //$category = "html";
    //$creator = "romikacid";
    $creator = $_SESSION['username'];
    
    $con = mysqli_connect("127.0.0.1","progin","progin","progin_405_13510027");
    //default status dari tugas yang baru saja disubmit adalah belum
    mysqli_query($con, "INSERT INTO task (namaTask, namaKategori, deadline, creatorTaskName, status) VALUES ('$task_name', '$kategori', '$deadline', '$creator', 'belum')");
    
    $i = 0;    
    foreach($_FILES["attachment"]["name"] as $filename) {        
        move_uploaded_file($_FILES["attachment"]["tmp_name"][$i], "C:/xampp/htdocs/progin/tubes/server/" . $filename);
        mysqli_query($con, "INSERT INTO attach (namaTask, attachment) VALUES ('$task_name', '$filename')");
        $i++;
    }
    
    $tag_input = $_POST["tag"];
    $tags = explode(",", $tag_input);
    $j = 0;
    foreach($tags as $tag) {
        mysqli_query($con, "INSERT INTO tagging (namaTask, tag) VALUES ('$task_name', '$tag')");
        $j++;
    }
    
    //header("Location: dashboard.php");
?>