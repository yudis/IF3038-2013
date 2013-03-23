<?php  
$conn=mysql_connect("localhost", "root", "");
mysql_select_db("mytask");
$id = $_GET['id'];
$sqlkomentar = "DELETE FROM komentar WHERE idtask='$id'";
$resultkom = mysql_query($sqlkomentar);
$sqltag = "DELETE FROM tag WHERE idtask='$id'";
$resulttag = mysql_query($sqltag);
$sqlass = "DELETE FROM assignee WHERE idtask='$id'";
$resultass = mysql_query($sqlass);
$sql = "DELETE FROM tugas WHERE id='$id'";
$result = mysql_query($sql);
 
if ($result){
     echo "<div class=\"task_view\" id=\"curtask1\">	Sukses menghapus data <br />
           <a href=\"dashboard.php\">Lihat Dashboard</a></div>";
} else {
     echo "Terjadi kesalahan";
}
  
  
?> 