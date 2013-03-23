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
<form action="javascript:registertake()" name="registrasi">
		<p>Username: <input  id="DID" name="DID" type="text"></input></p>
		<p>Password: <input id="DP" name="DP" type="password"></input></p>
		<p>Confirm Password: <input  id="DCP" name="DCP" type="password"></input></p>
		<p>Name: <input  id="DName" name="DName" type="text"></input></p>
		<p>Tanggal Lahir: <select id="Day" ><option value="01">01</option><option value="02">02</option></select><select id="DMonth"><option value="01">01</option><option value="02">02</option></select><select id="DYear"><option value="1992">1992</option><option value="1993">1993</option></select></p>
		<p>Alamat email: <input  id="DMail" name="DMail" type="text"></input></p>
		<input id="signup" value="save" type="submit" ></input>
	</form>
</center>  
</div>
<div class="assignment">
  <p>Daftar Tugas :</p>
  <?php
	$r = $_SESSION['login'];

	$con = mysql_connect('localhost', 'progin', 'progin');
	if (!$con)
	  {
	  die('Could not connect: ' . mysql_error());
	  }

	mysql_select_db("progin", $con);

$sqw="SELECT Nama,Deadline FROM task WHERE ID = '".$r."'";

$resultg = mysql_query($sqw);

echo "<div class=\"colmid\">";
echo "<div class=\"colleft\">";
echo "<div class=\"col1\">";
while($raw = mysql_fetch_array($resultg))
  {
  echo $raw['Nama'];
  echo "<br/>";
  }
echo "</div>";
echo "<div class=\"col2\">";
while($raw = mysql_fetch_array($resultg))
  {
  echo $raw['Deadline'];
  echo "<br/>";
  }
echo "</div>";
mysql_close($con);
?>
        <div class="col3">
            <img src="images/cek.png" width="35" height="35" /><br/>
			<img src="images/cek.png" width="35" height="35" /><br/>
			<br/>
			<br/>
        </div> 
    </div> 
  </div>
</div>
</div>
</body>
</html>
