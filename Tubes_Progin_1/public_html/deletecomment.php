<?php
    require_once("db.php");
    session_start();
    if(isset($_GET['id'])){
        ProginDB::getInstance()->query("DELETE FROM comment WHERE id_comment='".$_GET['id']."'");
        echo $_GET['id'];
    }
?>
