<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

require_once("database.php");
$con = connectDatabase();
$task=$_GET['task'];
$query = 'DELETE from task WHERE namaTask="'.$task.'"';
mysqli_query($con, $query);
$query = 'DELETE from tasktoasignee WHERE namaTask="'.$task.'"';
mysqli_query($con, $query);
header("Location:dashboard.php");
?>
