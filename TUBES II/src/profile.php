<?php
session_start();
if(isset($_SESSION['login']) && !empty($_SESSION['login'])){
include 'php/profile.php';
}else
{
include 'php/index.php';
}