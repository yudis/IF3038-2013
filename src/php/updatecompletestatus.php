<?php

require('init_function.php');

$con = getConnection();
$status = $_GET['status'];
$taskid = $_GET['taskid'];

if(strcmp("UNCOMPLETE",$status)==0){
	$query = "UPDATE task SET status='COMPLETE' where taskid=$taskid";
	mysqli_query($con,$query);
	echo "COMPLETE";
}else{
	$query = "UPDATE task SET status='UNCOMPLETE' where taskid=$taskid";
	mysqli_query($con,$query);
	echo "UNCOMPLETE";
}

?>