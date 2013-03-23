<?php

include 'koneksi.php';

$query = "SELECT * FROM tag";

$result = mysql_query($query);

while ($row = mysql_fetch_array($result)) {
   echo "<option value='".$row['namatag']."'></option>";
}

mysql_close($conn);
?>