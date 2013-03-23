<?php
    session_start();
        
    $username = $_POST["username"];
    $email = $_POST["email"];
    $password = $_POST["password"];
    $full_name = $_POST["full_name"];
    $birth_date = $_POST["birth_date"];
    
    /***upload avatar***/
    sleep(0.5);    
    // define a constant for the maximum upload size
    define ("MAX_FILE_SIZE", 1024 * 50);    

    // create an array of permitted MIME types
    $permitted = array("image/gif", "image/jpeg", "image/jpg",
                       "image/png");
    
    // upload if file is OK
    if (in_array($_FILES["avatar"]["type"], $permitted)
        && $_FILES["avatar"]["size"] > 0
        && $_FILES["avatar"]["size"] <= MAX_FILE_SIZE) {
        $file = $username . ".png";
        move_uploaded_file($_FILES["avatar"]["tmp_name"], "server/" . $file);                   
    }
    
    $con = mysqli_connect("127.0.0.1","progin","progin","progin_405_13510027");
    mysqli_query($con, "INSERT INTO user (username, email, password, fullname, tanggalLahir, avatar) VALUES ('$username', '$email', '$password', '$full_name', '$birth_date', '$file')");
    
    $_SESSION["username"] = $username;
    $_SESSION["loggedin"] = "yes";
    header("Location:dashboard.php");
?>