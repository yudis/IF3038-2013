<?php

include 'koneksi.php';

$query = "SELECT username FROM user";

$result = mysql_query($query);

while ($row = mysql_fetch_array($result)) {
   echo "<option value='".$row['username']."'></option>";
}

mysql_close($conn);
?>