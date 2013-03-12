<?php

require('init_function.php');

$con = getConnection();

$asignee = $_GET['asignee'];
$asigneepieces = explode(",",$asignee);
$result = "";

if($asigneepieces[count($asigneepieces)-1]!=""){
	$tosearch = $asigneepieces[count($asigneepieces)-1];
	$result = mysqli_query($con,"SELECT username FROM user WHERE username LIKE '%$tosearch%'");
	
	while($row = mysqli_fetch_array($result)){
		echo $row['username']."|";
	}
}

//echo($result);
?>