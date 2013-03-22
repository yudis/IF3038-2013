<?php


    //mendapatkan task dengan id yang terakhir
 $kategori = $_GET["t"];
 $con=mysqli_connect("localhost","progin","progin","progin");
 if (mysqli_connect_errno($con))
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
 // ec $sql = "SELEho "udah ampe sini";  
  
  $sql = "SELECT * FROM task WHERE (cat_task_name='$kategori')";
  //echo $sql;
  if (!mysqli_query($con,$sql))
  {
  die('Error: ' . mysqli_error());
  }
  $result  = mysqli_query($con,$sql);
 
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
