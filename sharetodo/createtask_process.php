<?php
    $task_name = $_POST["task_name"];
    $deadline = $_POST["deadline"];
//    $category = $_GET["kategori"];
//    $creator = $_SESSION["username"];
    $category = "html";
    $creator = "romikacid";
    
    $con = mysqli_connect("localhost", "root", "", "sharetodo");
    mysqli_query($con, "INSERT INTO task (namaTask, namaKategori, deadline, creatorTaskName, status) VALUES ('$task_name', '$category', '$deadline', '$creator', 'belum')");
    
    $i = 0;    
    foreach($_FILES["attachment"]["name"] as $filename) {        
        move_uploaded_file($_FILES["attachment"]["tmp_name"][$i], "server/" . $filename);
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
    
?>