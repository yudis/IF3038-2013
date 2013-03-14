<?php
$con = mysql_connect("localhost:3306","root","");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);

$response = '';
//get the q parameter from URL
$idkategori=$_GET["q"];

// Fill up array with names
$result = mysql_query("SELECT * FROM tugas WHERE idkategori = '$idkategori'");
while($row = mysql_fetch_array($result)) {
	$response .= "<div class=task_block><div class=task_judul>".$row['namatugas']."</div><div class=task_deadline> Deadline : ".$row['deadline']."</div><div class=task_tag>Tags: ";
	$result2 = mysql_query("SELECT isitag FROM tugas JOIN tag WHERE tugas.idtugas = $row[idtugas] AND tag.idtugas = $row[idtugas]");
	$count = mysql_num_rows($result2);
	while($row2 = mysql_fetch_array($result2)) {
		if ($count > 1)
			$response .= $row2['isitag'].", ";
		else 
			$response .= $row2['isitag'];
		$count--;
	}
	$response .= "</div></div>";
}
//output the response
echo $response;
?>