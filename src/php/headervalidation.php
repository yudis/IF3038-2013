<?php

require('init_function.php');

$con = getConnection();

$content = $_GET['content'];
$idx = $_GET['idx'];

if(strcmp($idx,"2")==0){
	$query = "SELECT username FROM user WHERE username LIKE '%$content%'";
	$result = mysqli_query($con,$query);
	while($row = mysqli_fetch_array($result)){
		echo $row['username']."|";
	}
}else{
	echo "";
}

?>