<?php

require('init_function.php');

$con = getConnection();

$asignee = $_GET['asignee'];
$asigneepieces = explode(",",$asignee);
$result = "";

/*if($asigneepieces[count($asigneepieces)-1]!=""){
	$result = mysql_query("SELECT username FROM user WHERE username LIKE '%$asigneepieces[count($asigneepieces)-1]%'");
	while($row = mysqli_fetch_array($result){
		//echo $row['username'];
	}
}*/

echo($asigneepieces[count($asigneepieces)-1]);
?>