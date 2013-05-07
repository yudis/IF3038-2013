<?php
    require_once("db.php");

    if ($_SERVER['REQUEST_METHOD'] == "POST"){
        session_start();
        $_SESSION['username'] = $_POST['regusername'];
        $_SESSION['password'] = $_POST['regpassword1'];
        move_uploaded_file($_FILES["regfile"]["tmp_name"],"avatar/".$_FILES["regfile"]["name"]);
        ProginDB::getInstance()->create_account($_POST['regusername'], $_POST['regname'], $_POST['regdate'], $_POST['regpassword1'], $_POST['regemail'], $_FILES["regfile"]["name"]);
        header('Location: dashboard.php' );
        exit;
    }
    
    $code = 0;
    if (isset($_GET['u']) || isset($_GET['e'])){
        $registeredUser = ProginDB::getInstance()->verify_user_exists_by_username($_GET['u']);
        $registeredEmail = ProginDB::getInstance()->verify_user_exists_by_email($_GET['e']);
        if ($registeredUser > 0 && $registeredEmail > 0){ //both not unique
            $code = 1;
        } else if ($registeredUser > 0){ //user not unique
            $code = 2;
        } else if ($registeredEmail > 0){ //email not unique
            $code = 3;
        }
    }
    echo $code;
?>