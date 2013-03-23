<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
   $lastidx = $_GET["t"];

   $con=mysqli_connect("localhost","progin","progin","progin_405_13510060");
    if (mysqli_connect_errno($con))
        {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }
 // echo "udah ampe sini";
     $sql = "SELECT cat_task_name,task_name,task_status,task_deadline,task_tag_multivalue,checkbox,assignee_name,file FROM task WHERE task_id='$lastidx'";
    if (!mysqli_query($con,$sql))
        {
             die('Error: ' . mysqli_error());
        }
    $result = mysqli_query($con, $sql);
    $res = array();
    $i = 0;
        // echo "<html><body>";


    while ($row = mysqli_fetch_array($result)) {
            //echo "row ".$i;
            //print_r($row);
            array_push($res, $row);
            //echo "<br/>";
            $i++;
    }
       /* foreach ($res as $row) {
            echo($row['KATEGORI_TASK']);
        }*/
  echo json_encode($res);
?>
