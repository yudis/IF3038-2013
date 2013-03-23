
<?php
$id=$_GET["id"];
$deadline=$_GET["deadline"];

include "koneksi.php";
$simpan = mysql_query("update tugas set deadline='$deadline' where id= '$id'");
$tugas="select * from tugas where id= '$id'";
$hasil=mysql_query($tugas);
while($row=mysql_fetch_array($hasil)){
echo $row['deadline'];
}
mysql_close($conn);
?> 