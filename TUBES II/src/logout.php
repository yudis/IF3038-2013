<?php
session_start();
if(isset($_SESSION['login'])){
  unset($_SESSION['login']);}
include 'php/index.php';
?>