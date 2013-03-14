<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>
<?php
// Create connection
$con=mysqli_connect("localhost","progin","progin","progin_405_13510035");

// Check connection
if (mysqli_connect_errno($con))
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
  
  
$result = mysqli_query($con,"SELECT * FROM category");

while($row = mysqli_fetch_array($result))
  {
  echo $row['category_id'] . " " . $row['category_name'];
  echo "<br />";
  }

mysqli_close($con);
?>
</body>
</html>