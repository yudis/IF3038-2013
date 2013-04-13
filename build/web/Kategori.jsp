<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Kategori</title>
</head>

<body>
<?php
include 'Header.php';
?>
<a href ='Add_Kategori.php'>
<?php
require ('connect.php');
$Kategori = $_POST['add_kategori'];
$Task = $_POST['add_task'];
$Asignee = $_POST['add_asignee'];	
$q = "INSERT INTO kategori(Kategori, Task, Asignee) VALUES ('$Kategori', '$Task', '$Asignee')";
$q2 = "SELECT * FROM kategori";
$add = mysql_query($q);
$result = mysql_query($q2);

echo "<center>";
echo "<h2> Kategori </h2>";
echo "<table border='1' cellpadding='5' cellspacing='8'>";
echo "
	<tr>
	<th>Kategori</th>
	<th>Task</th>
	<th>Asignee</th>
	</tr>";
while ($row = mysql_fetch_array($result)) {  //fetch the result from query into an array
	  echo "<tr>"; 
	  echo "<td>" . $row["Kategori"] . "</td>";
	  echo "<td>" . $row["Task"] . "</td>"; 
	  echo "<td>" . $row["Asignee"] . "</td>";
	  echo "</tr>";
}
echo "</table>";
?>
</body>
</html>
