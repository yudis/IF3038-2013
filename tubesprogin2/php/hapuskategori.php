<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 $kategori = $_GET["t"];

   $con=mysqli_connect("localhost","progin","progin","progin_405_13510060");
    if (mysqli_connect_errno($con))
        {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }
 // echo "udah ampe sini";
     $sql = "DELETE FROM category where cat_name='$kategori'";
     $sql2 = "DELETE FROM task where cat_task_name='$kategori'";
    if (!mysqli_query($con,$sql))
        {
             die('Error: ' . mysqli_error());
        }
         if (!mysqli_query($con,$sql2))
        {
             die('Error: ' . mysqli_error());
        }
?>
