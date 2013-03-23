<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$value = $_GET["t"];
$taskname = $_GET["nama"];

   $con=mysqli_connect("localhost","progin","progin","progin");
    if (mysqli_connect_errno($con))
        {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }
 // echo "udah ampe sini";
     $sql = "UPDATE task SET task_status='$value',checkbox='$value' WHERE task_name='$taskname'";
  
?>
