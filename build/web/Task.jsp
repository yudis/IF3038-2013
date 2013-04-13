<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Task</title>
</head>

<body>
<?php
include 'Header.php';
?>
<a href ='Add_Task.php'>
<?php
$connect=mysql_connect("localhost","progin","progin","progin_439_13510100") or die(mysql_error());
	if(!$connect){
		die("Could not reach $host.");
	}
	$db_name="progin_439_13510100";
	$db_select=mysql_select_db($db_name,$connect);
	if(!$db_select){
		die("Could not reach database $db_name.");
}
$Nama = $_POST['add_nama'];
$Tanggal = $_POST['add_tanggal'];
$Status = $_POST['add_status'];
$Tag = $_POST['add_tag'];
$q = "INSERT INTO task(nama_task, tanggal, status, tag) VALUES ('$Nama', '$Tanggal', '$Status', '$Tag')";
$q2 = "SELECT * FROM task";
$add = mysql_query($q);
$result = mysql_query($q2);

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
while ($row = mysql_fetch_array($result)) {  //fetch the result from query into an array
	  echo "<tr>"; 
	  echo "<td>" . $row["nama_task"] . "</td>";
	  echo "<td>" . $row["tanggal"] . "</td>"; 
	  echo "<td>" . $row["status"] . "</td>";
	  echo "<td>" . $row["tag"] . "</td>";
	  echo "</tr>";
}
echo "</table>";
?>

</body>
</html>
