<?php
$con = mysql_connect("localhost:3306","root","");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);
session_start();
$response = '';
//get the q parameter from URL
$username=$_SESSION['id'];
// Fill up array with names
$tugaspribadi = mysql_query("SELECT * FROM tugas WHERE username = '$username'");
while($row = mysql_fetch_array($tugaspribadi)) {
	$response .= "<div class=task_block><div class=task_judul>".$row['namatugas']."</div><div class=task_deadline> Deadline : ".$row['deadline']."</div><div class=task_tag>Tags: ";
	$tagpribadi = mysql_query("SELECT isitag FROM tugas JOIN tag WHERE tugas.idtugas = $row[idtugas] AND tag.idtugas = $row[idtugas]");
	$count = mysql_num_rows($tagpribadi);
	while($row2 = mysql_fetch_array($tagpribadi)) {
		if ($count > 1)
			$response .= $row2['isitag'].", ";
		else 
			$response .= $row2['isitag'];
		$count--;
	}
	if ($row['status'] == "done")
		$status = "checked";
	else 
		$status = '';
	$response .= "<div>Status : <input type=checkbox name=\"status\" value=\"done\" ".$status."/ onchange=\"location.href='changestatus.php?q=".$row['idtugas']."'\"></div>";
	$response .= "</div><button onclick=\"location.href='deletetask.php?q=".$row['idtugas']."'\">Hapus Task...</button></div>";
}
$tugasassign = mysql_query("SELECT * FROM tugas JOIN assignee WHERE assignee.username = '$username' AND tugas.idtugas = assignee.idtugas");
while($row = mysql_fetch_array($tugasassign)) {
	$response .= "<div class=task_block><div class=task_judul>".$row['namatugas']."</div><div class=task_deadline> Deadline : ".$row['deadline']."</div><div class=task_tag>Tags: ";
	$tagassign = mysql_query("SELECT isitag FROM tugas JOIN tag WHERE tugas.idtugas = $row[idtugas] AND tag.idtugas = $row[idtugas]");
	$count = mysql_num_rows($tagassign);
	while($row2 = mysql_fetch_array($tagassign)) {
		if ($count > 1)
			$response .= $row2['isitag'].", ";
		else 
			$response .= $row2['isitag'];
		$count--;
	}
	if ($row['status'] == "done")
		$status = "checked";
	else 
		$status = '';
	$response .= "<div>Status : <input type=checkbox name=\"status\" value=\"done\" ".$status."/ onchange=\"location.href='changestatus.php?q=".$row['idtugas']."'\"></div>";
	$response .= "</div></div>";
}
//output the response
echo $response;
?>