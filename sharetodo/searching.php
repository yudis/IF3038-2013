<?php

$con = mysqli_connect("127.0.0.1","progin","progin","progin_405_13510027");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("survey_db", $con);

$result = mysql_query("SELECT * FROM Responden
WHERE );


?>