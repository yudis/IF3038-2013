<?php
    require_once("db.php");
    $logonSuccess = false;

    if ($_SERVER['REQUEST_METHOD'] == "POST") {
        $logonSuccess = (ProginDB::getInstance()->verify_user_credentials($_POST['logusername'], $_POST['logpassword']));
        if ($logonSuccess == true) {
            session_start();
            $_SESSION['username'] = $_POST['logusername'];
            header('Location: dashboard.php');
        } else {
            header('Location: index.php');
        }
    } 
?>

