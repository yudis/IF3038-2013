<?php
    require_once("db.php");
    
    session_start();
    unset($_SESSION['username']);
    header('Location: index.php');
?>
