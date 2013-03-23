<?php
$kategori=$_GET["kategori"];

$con = mysql_connect('localhost', 'progin', 'progin');
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin", $con);

$sql="SELECT Nama FROM task WHERE Kategori = '".$kategori."'";

$result = mysql_query($sql);

while($row = mysql_fetch_array($result)) 
	{
	echo $row['Nama'];
	echo "<&nbsp>";
	echo "<input type=\"submit\" value=\"Lihat Rincian\" ></input>";
	}
echo "<div id=\"addtask\" style=\"display: block;\">
		<a href=\"tambah.html\"><img src=\"images/newtask.png\" vspace=\"100\"></a>
	</div>";

mysql_close($con);
?>