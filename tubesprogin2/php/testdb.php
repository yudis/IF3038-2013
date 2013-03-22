<?php
$con=mysqli_connect("localhost","progin","progin","progin");

// Check connection
if (mysqli_connect_errno($con))
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

$result = mysqli_query($con,"SELECT * FROM task");

$res = array();
$i=0;
while($row = mysqli_fetch_array($result))  
  {
    echo "row ".$i;
    print_r($row);
    array_push($res,$row);
  echo "<br />";
  $i++;
  }
  
  /*$a  = $_POST['category'];*/
  
echo json_encode($res);
mysqli_close($con);
?>