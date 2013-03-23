<?php

require_once('config.php');
?>
<?php

session_start();

if (connectDB()) {
    $fullname = $_POST['regname'];
    $password = md5($_POST['regpassword1']);
    $password2 = md5($_POST['regpassword2']);
    $date = $_POST['regdate'];
    
    $target = "img/".$_COOKIE['UserLogin'].$_FILES["regfile"]["name"];
    
    if ($_FILES["regfile"]["name"] != NULL) {
        move_uploaded_file($_FILES["regfile"]["tmp_name"],$target);
        $insertQuery= "UPDATE user SET Avatar='".$target."' WHERE Username = '".$_COOKIE['UserLogin']."'";
        $insert = mysql_query($insertQuery);
    }
    
    if ($fullname != NULL) {
        $insertQuery= "UPDATE user SET Fullname='".$fullname."' WHERE Username = '".$_COOKIE['UserLogin']."'";
        $insert = mysql_query($insertQuery);
    }
    
    if ($_POST['regpassword1'] != NULL) {
        if ($password === $password2 ) {
            $insertQuery= "UPDATE user SET Password='".$password."' WHERE Username = '".$_COOKIE['UserLogin']."'";
            $insert = mysql_query($insertQuery);
        }
        else {
            echo '<script type="text/javascript">alert("password salah")</script>';
        }
        
    }
    
    if ($date != NULL) {
        $insertQuery= "UPDATE user SET DateOfBirth='".$date."' WHERE Username = '".$_COOKIE['UserLogin']."'";
        $insert = mysql_query($insertQuery);
    }
}

header('Location: Profile.php?user='.$_COOKIE['UserLogin']);
?>