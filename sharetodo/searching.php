<?php
    $con = mysqli_connect("localhost", "progin", "progin", "progin_405_13510027");
    $filter = $_GET["filter"];
    $keyword = $_GET["keyword"];
//    $keyword = "romikacid";
//    $filter = "category";
    
//    if($filter == "all") {
        $task_result = mysqli_query($con, "SELECT * FROM task WHERE namaTask = '$keyword'");
        $user_result = mysqli_query($con, "SELECT * FROM user WHERE username = '$keyword'");
        $category_result = mysqli_query($con, "SELECT * FROM kategori WHERE namaKategori = '$keyword'");
        
    if($filter == "all" || $filter == "task") {
        echo "TASK"; echo "<br>";
        while($task_row = mysqli_fetch_assoc($task_result)) {
            echo $task_row["namaTask"]; echo "<br>";
            echo $task_row["deadline"]; echo "<br>";
            echo $task_row["status"]; echo "<br>";
        }
        echo "<br><br>";
    }
    
        
    if($filter == "all" || $filter == "user") {
        echo "USER"; echo "<br>";
        while($user_row = mysqli_fetch_assoc($user_result)) {
            echo $user_row["username"]; echo "<br>";
            echo $user_row["email"]; echo "<br>";
            echo $user_row["fullname"]; echo "<br>";
            echo $user_row["tanggalLahir"]; echo "<br>";
        }
        echo "<br><br>";
    }            
    
    if($filter == "all" || $filter == "category") {
        echo "CATEGORY"; echo "<br>";
        while($category_row = mysqli_fetch_assoc($category_result)) {
            echo $category_row["namaKategori"]; echo "<br>";
            echo $category_row["creatorKategoriName"];
        }
    }
    
//    }        

?>