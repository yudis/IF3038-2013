<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 $usernamelama = $_GET["umd"];
 $usernamebaru = $_GET["un"];
 $avatar = $_GET["av"];
 $password = $_GET["p"];
 $fullname = $_GET["f"];
 $email = $_GET["e"];
 $con=mysqli_connect("localhost","progin","progin","progin_405_13510060");
 if (mysqli_connect_errno($con))
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
 // ec $sql = "SELEho "udah ampe sini";  
  
  $sql ="UPDATE pengguna SET username='$usernamebaru',avatar='$avatar',password='$password',full_name='$fullname',email='$email' WHERE username='$usernamelama'";
  echo $sql;
  //echo $sql;
  if (!mysqli_query($con,$sql))
  {
  die('Error: ' . mysqli_error());
  }
  
?>