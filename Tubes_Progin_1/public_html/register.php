<?php
require_once("db.php");

if ($_SERVER['REQUEST_METHOD'] == "POST"){
    ProginDB::getInstance()->create_account($_POST['regusername'], $_POST['regname'], $_POST['regdate'], $_POST['regpassword1'], $_POST['regemail'], $_POST['regfile']);
    session_start();
    $_SESSION['username'] = $_POST['regusername'];
    header('Location: dashboard.php' );
    exit;
}
?>