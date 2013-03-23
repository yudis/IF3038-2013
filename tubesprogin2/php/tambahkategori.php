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
  $sql2 = "SELECT * FROM category where (cat_name='$t')";
  
  if (!mysqli_query($con,$sql))
  {
  die('Error: ' . mysqli_error());
  }
  $result  = mysqli_query($con,$sql2);
 
    $res = array();
    $i=0;
   // echo "<html><body>";
    
    
   while($row = mysqli_fetch_array($result))  
  {
    //echo "row ".$i;
   // print_r($row);
    array_push($res,$row);
     //echo "<br/>";
    $i++;
  }
  //print_r($res[0]['ID_TASK']);
  //echo"</body></html>";
  echo json_encode($res);
  
 // echo "last index". $con->insert_id;
  
  
  ?>
