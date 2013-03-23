<?php
    require_once("db.php");
    $logonSuccess = false;

    if(isset($_GET['u']) && isset($_GET['p'])){
        $logonSuccess = (ProginDB::getInstance()->verify_user_credentials($_GET['u'], $_GET['p']));
        if ($logonSuccess == true) {
            session_start();
            $_SESSION['username'] = $_GET['u'];
            $_SESSION['password'] = $_GET['p'];
        } else {
        }
    }
    echo $logonSuccess;
?>

