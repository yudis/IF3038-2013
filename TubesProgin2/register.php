<?php

require_once('config.php');
?>
<?php

session_start();

if (connectDB()) {
    $username = $_POST['regusername'];
    $fullname = $_POST['regname'];
    $password = md5($_POST['regpassword1']);
    $email = $_POST['regemail'];
    $date = $_POST['regdate'];
    
    $target = "img/".$username.$_FILES["regfile"]["name"];
    
    move_uploaded_file($_FILES["regfile"]["tmp_name"],$target);
    $insertQuery= "INSERT INTO USER (`Username`, `Fullname`, `Password`, `DateOfBirth`, `Email`, `Avatar`) VALUES
    ('".$username."', '".$fullname."','".$password."', '".$date."', '".$email."', 'img/".$target."');";
    
    $insert = mysql_query($insertQuery);
    
    setcookie("UserLogin", $username, time() + 3600*24*30);
    $_SESSION['username'] = $username;
    
    header('Location: Dashboard.php');
}

?>