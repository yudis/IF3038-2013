<?php
$q=$_GET["q"];

$con = mysql_connect('localhost', 'root', '');
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("mytask", $con);


$sql = "DELETE FROM kategori WHERE id='".$q."'";
$result = mysql_query($sql);

if ($result){
     echo "Sukses menghapus data <br />
           <a href=\"dashboard.php\">Lihat Dashboard</a>";
} else {
     echo "Terjadi kesalahan";
}

mysql_close($con);
?> 