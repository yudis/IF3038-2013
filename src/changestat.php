
<?php
$id=$_GET["id"];

include "koneksi.php";
$status="select * from tugas where id= '$id'";
$hasil=mysql_query($status);
while($row=mysql_fetch_array($hasil)){

if ($row['status'] == 1)
{
	$simpan = mysql_query("update tugas set status=0 where id= '$id'");
	echo "Belum Selesai";
}
else if ($row['status'] == 0)
	{
	$simpan = mysql_query("update tugas set status=1 where id= '$id'");
	echo "Selesai";
}
}


mysql_close($conn);
?> 