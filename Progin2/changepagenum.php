<?php
session_start();

$pg = $_GET['pg'];
$id = $_GET['q'];

$_SESSION['pagenum'] = $pg;

header("location:viewtask.php?q=".$id);
?>