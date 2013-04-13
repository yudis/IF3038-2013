<?php
$login_session = '';
if (isset($_SESSION['login_user'])) {
  $user_check=$_SESSION['login_user'];
  $ses_sql=mysql_query("select username from user where username='$user_check' ");
  $ses2_sql=mysql_query("select id from user where username='$user_check' ");
  $ses3_sql=mysql_query("select fullname from user where username='$user_check' ");
  $ses4_sql=mysql_query("select tanggalbirthday from user where username='$user_check' ");
  $ses5_sql=mysql_query("select bulanbirthday from user where username='$user_check' ");
  $ses6_sql=mysql_query("select tahunbirthday from user where username='$user_check' ");

  $row=mysql_fetch_array($ses_sql);
  $row2=mysql_fetch_array($ses2_sql);
  $row3=mysql_fetch_array($ses3_sql);
  $row4=mysql_fetch_array($ses4_sql);
  $row5=mysql_fetch_array($ses5_sql);
  $row6=mysql_fetch_array($ses6_sql);

  $login_session=$row['username'];
  $IDlogin_session=$row2['id'];
  $FullNamelogin_session=$row3['fullname'];
  $Tanggallogin_session=$row4['tanggalbirthday'];
  $Bulanlogin_session=$row5['bulanbirthday'];
  $Tahunlogin_session=$row6['tahunbirthday'];
} else { 
  header('Location: index.php');
}
?>