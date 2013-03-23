<?php
$t = $_GET["t"];

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$con=mysqli_connect("localhost","progin","progin","progin_405_13510060");
if (mysqli_connect_errno($con))
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
  
  $sql = "INSERT INTO category (cat_name) VALUES ('$t')";
  
  
  if (!mysqli_query($con,$sql))
  {
  die('Error: ' . mysqli_error());
  }
  
  
 // echo "last index". $con->insert_id;
  echo json_encode(array('last_idx'=>$con->insert_id));
  
  ?>
