<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Test Result</title>

</head>

<body>

	<header>
		<a href ='dashboard.php'>
		<?php
			if(isset($_SESSION['avatar'])) {echo "<img1 src='{$_SESSION['avatar']}'>";}
			elseif(isset($_SESSION['name'])) {echo "<img1 src='avatar/default.jpg'>";}
		?>
		</a>
		
		<div class="header"><center><a href="dashboard.php"><img src="images/todolistss.jpg" alt="logo"/></a></center>
		<center>
		  <table width="400" border="0">
		    <tr>
		      <td><a href="dashboard.php" id="linkdashboard">DashBoard</a></td>
				      <td><a href="halamanprofil.php" id="linkprofile">My Profile</a></td>
				      <td><a href="logout.php" id="linklogout">LogOut</a></td>
	        </tr>
	      </table>
		  </center>
		  <center>
		    <form id="form1" name="form1" method="post" action="Test Result.php">
		      <label>Search: 
	          <input type="text_search" name="text_search" id="text_search" placeholder="Search" />
	          <input type="SUBMIT" name="SUBMIT" id="SUBMIT" value="Search" > 
	          </label>
		      
		      
		      <label>Filter: 
		        <select id="filter" name="filter" width="400">
		          <option> All </option>
		          <option> Users </option>
		          <option> Kategori </option>
		          <option> Task </option>
	            </select>
	          </label>
	      </form>
		  </center>
		    <div id="sign">
			<?php
				if(isset($_SESSION['uname'])){
					echo "Hello there, <b><a href='halamanprofil.php'>".$_SESSION['uname']."</a></b> Welcome Home!  <b><a href='logout.php'>Log Out</a></b>";
				}
			?>
			</div>
	  </div>
		
    </header>

<?php

$name= $_POST['text_search']; //get the nama value from form
$filter= $_POST['filter']; 
$connect=mysql_connect("localhost","progin","progin","progin_439_13510100") or die(mysql_error());
	if(!$connect){
		die("Could not reach $host.");
	}
	$db_name="progin_439_13510100";
	$db_select=mysql_select_db($db_name,$connect);
	if(!$db_select){
		die("Could not reach database $db_name.");
}
$q1 = "SELECT * from user where username like '%$name%' ";
$q2 = "SELECT * from kategori where Kategori like '%$name%' ";
$q3 = "SELECT * from task where nama_task like '%$name%' ";
$q4 = "SELECT * from user where username like '%$name%' UNION kategori where Kategori like '%$name%' UNION task where nama_task like '%$name%' ";

if ($filter == "Users"){
	$result2 = mysql_query($q1); //execute the query $q1
	echo "<center>";
	echo "<h2> Users </h2>";
	echo "<table border='1' cellpadding='5' cellspacing='8'>";
	echo "
		<tr>
		<th>IDUser</th>
		<th>Username</th>
		<th>Name</th>
		<th>Date</th>
		<th>E-mail</th>
		<th>Task</th>
		</tr>";
	while ($row = mysql_fetch_array($result2)) {  //fetch the result from query into an array
		  echo "<tr>";
		  echo "<td>" . $row["iduser"] . "</td>"; 
		  echo "<td>" . $row["username"] . "</td>";
		  echo "<td>" . $row["name"] . "</td>"; 
		  echo "<td>" . $row["dob"] . "</td>";
		  echo "<td>" . $row["email"] . "</td>";
		  echo "<td>" . $row["jmltask"] . "</td>";
		  echo "</tr>";
	}
	echo "</table>";
}

elseif ($filter == "Kategori"){
	$result2 = mysql_query($q2); //execute the query $q2
	echo "<center>";
	echo "<h2> Kategori </h2>";
	echo "<table border='1' cellpadding='5' cellspacing='8'>";
	echo "
		<tr>
		<th>Kategori</th>
		<th>Task</th>
		<th>Asignee</th>
		</tr>";
	while ($row = mysql_fetch_array($result2)) {  //fetch the result from query into an array
		  echo "<tr>"; 
		  echo "<td>" . $row["Kategori"] . "</td>";
		  echo "<td>" . $row["Task"] . "</td>"; 
		  echo "<td>" . $row["Asignee"] . "</td>";
		  echo "</tr>";
	}
	echo "</table>";
}

elseif ($filter == "Task"){
	$result2 = mysql_query($q3); //execute the query $q3
	echo "<center>";
	echo "<h2> Task </h2>";
	echo "<table border='1' cellpadding='5' cellspacing='8'>";
	echo "
		<tr>
		<th>Nama</th>
		<th>Tanggal</th>
		<th>Status</th>
		<th>Tag</th>
		</tr>";
	while ($row = mysql_fetch_array($result2)) {  //fetch the result from query into an array
		  echo "<tr>"; 
		  echo "<td>" . $row["nama_task"] . "</td>";
		  echo "<td>" . $row["tanggal"] . "</td>"; 
		  echo "<td>" . $row["status"] . "</td>";
		  echo "<td>" . $row["tag"] . "</td>";
		  echo "</tr>";
	}
	echo "</table>";
}

else{
	$result2 = mysql_query($q1); //execute the query $q1
	echo "<center>";
	echo "<h2> Users </h2>";
	echo "<table border='1' cellpadding='5' cellspacing='8'>";
	echo "
		<tr>
		<th>IDUser</th>
		<th>Username</th>
		<th>Name</th>
		<th>Date</th>
		<th>E-mail</th>
		<th>Task</th>
		</tr>";
	while ($row = mysql_fetch_array($result2)) {  //fetch the result from query into an array
		  echo "<tr>";
		  echo "<td>" . $row["iduser"] . "</td>"; 
		  echo "<td>" . $row["username"] . "</td>";
		  echo "<td>" . $row["name"] . "</td>"; 
		  echo "<td>" . $row["dob"] . "</td>";
		  echo "<td>" . $row["email"] . "</td>";
		  echo "<td>" . $row["jmltask"] . "</td>";
		  echo "</tr>";
	}
	echo "</table>";
	$result3 = mysql_query($q2); //execute the query $q2
	echo "<center>";
	echo "<h2> Kategori </h2>";
	echo "<table border='1' cellpadding='5' cellspacing='8'>";
	echo "
		<tr>
		<th>Kategori</th>
		<th>Task</th>
		<th>Asignee</th>
		</tr>";
	while ($row2 = mysql_fetch_array($result3)) {  //fetch the result from query into an array
		  echo "<tr>"; 
		  echo "<td>" . $row2["Kategori"] . "</td>";
		  echo "<td>" . $row2["Task"] . "</td>"; 
		  echo "<td>" . $row2["Asignee"] . "</td>";
		  echo "</tr>";
	}
	echo "</table>";
	
	$result4 = mysql_query($q3); //execute the query $q3
	echo "<center>";
	echo "<h2> Task </h2>";
	echo "<table border='1' cellpadding='5' cellspacing='8'>";
	echo "
		<tr>
		<th>Nama</th>
		<th>Tanggal</th>
		<th>Status</th>
		<th>Tag</th>
		</tr>";
	while ($row3 = mysql_fetch_array($result4)) {  //fetch the result from query into an array
		  echo "<tr>"; 
		  echo "<td>" . $row3["nama_task"] . "</td>";
		  echo "<td>" . $row3["tanggal"] . "</td>"; 
		  echo "<td>" . $row3["status"] . "</td>";
		  echo "<td>" . $row3["tag"] . "</td>";
		  echo "</tr>";
	}
	echo "</table>";
}
?>


</body>
</html>
