<?php


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 $tasknamelama = $_GET["tmd"];
 $tasknamebaru = $_GET["tn"];
 $attachment = $_GET["at"];
 $deadline = $_GET["d"];
 $asign = $_GET["as"];
 $tag = $_GET["tg"];
 $con=mysqli_connect("localhost","progin","progin","progin_405_13510060");
 if (mysqli_connect_errno($con))
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
 // ec $sql = "SELEho "udah ampe sini";  
  
  $sql ="UPDATE task SET task_name='$tasknamebaru',task_deadline='$deadline',task_tag_multivalue='$tg',assignee_name='$asign',file='$at' WHERE task_name='$tasknamelama'";
  //echo $sql;
  if (!mysqli_query($con,$sql))
  {
  die('Error: ' . mysqli_error());
  }
?>
