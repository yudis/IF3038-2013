<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 $kategori = $_GET["t"];
 $namaasign =$_GET["na"];
 $con=mysqli_connect("localhost","progin","progin","progin");
 if (mysqli_connect_errno($con))
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
 // ec $sql = "SELEho "udah ampe sini";  
  
  $sql = "INSERT INTO authorized (cat_name,user_name) VALUES ($kategori,$namaasign)";
  $sql2 = "SELECT * FROM authorized WHERE (cat_name='$kategori',user_name='$cat_name')";
  //echo $sql;
  if (!mysqli_query($con,$sql))
  {
  die('Error: ' . mysqli_error());
  }
   if (!mysqli_query($con,$sql2))
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
?>
