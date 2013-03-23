<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width; initial-scale=1.0"/>
<title>Profile | So La Si Do</title>
<link href="css/profile.css" rel="stylesheet" type="text/css" />
<link href="css/mediaqueries.css" rel="stylesheet" type ="text/css" />
<link href='http://fonts.googleapis.com/css?family=Skranji' rel='stylesheet' type='text/css'/>
</head>

<body>
<div class="header">
	<a href="dashboard.php"><img align="left" src="images/logo.png" width="150" height="50" />
	<h6>Dashboard</a> | <a href="profile.php">Profile</a> | <a href="logout.php">Logout</a>
	
   | Search: <input type="search">
  <input type="submit" value="GO">
	</div>
	<div class="container">
<div class="data">
<center>
<h1>Profile</h1>
<img src="images/ava1.jpg" width="200" height="150" hspace="15" vspace="15" />
<?php
	$q = $_SESSION['login'];

	$con = mysql_connect('localhost', 'progin', 'progin');
	if (!$con)
	  {
	  die('Could not connect: ' . mysql_error());
	  }

	mysql_select_db("progin", $con);

$sql="SELECT * FROM profil WHERE Username = '".$q."'";

$result = mysql_query($sql);

while($row = mysql_fetch_array($result))
  {
  echo "<p>User :".$row['Username']; 
	echo "</p>";
		echo "<p>Nama Lengkap :".$row['FullName'];
			echo "</p>";
				echo "<p>Tanggal Lahir :".$row['TanggalLahir'];
					echo "</p>";
						echo "<p>E-mail :".$row['Email'];
							echo "</p>";
  }

mysql_close($con);
?>
  
  <form id="form1" name="form1" method="post" action="">
	<input type="image" src="images/edit.png" name="image" ></input>
  </form>
</center>  
</div>
</div>
</body>
</html>
