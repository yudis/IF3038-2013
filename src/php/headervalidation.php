<?php

require('init_function.php');

$con = getConnection();

$content = $_GET['content'];
$idx = $_GET['idx'];
$filter = $_GET['filter'];

if(strcmp($idx,"1")==0){
	$query = "SELECT username FROM user WHERE username LIKE '%$content%'";
	$result = mysqli_query($con,$query);
	while($row = mysqli_fetch_array($result)){
		echo $row['username']."|";
	}
	$query = "SELECT categoryname FROM category WHERE categoryname LIKE '%$content%'";
	$result = mysqli_query($con,$query);
	while($row = mysqli_fetch_array($result)){
		echo $row['categoryname']."|";
	}
	$query = "SELECT taskname FROM task WHERE taskname LIKE '%$content%'";
	$result = mysqli_query($con,$query);
	while($row = mysqli_fetch_array($result)){
		echo $row['taskname']."|";
	}
}else if(strcmp($idx,"2")==0){
	if(strcmp($filter,"1")==0){
		$query = "SELECT username FROM user WHERE username LIKE '%$content%'";
		$result = mysqli_query($con,$query);
		while($row = mysqli_fetch_array($result)){
			echo $row['username']."|";
		}
	}else if(strcmp($filter,"2")==0){
		$query = "SELECT email FROM user WHERE email LIKE '%$content%'";
		$result = mysqli_query($con,$query);
		while($row = mysqli_fetch_array($result)){
			echo $row['email']."|";
		}
	}else if(strcmp($filter,"3")==0){
		$query = "SELECT fullname FROM user WHERE fullname LIKE '%$content%'";
		$result = mysqli_query($con,$query);
		while($row = mysqli_fetch_array($result)){
			echo $row['fullname']."|";
		}
	}else if(strcmp($filter,"4")==0){
		$query = "SELECT birthday FROM user WHERE birthday LIKE '%$content%'";
		$result = mysqli_query($con,$query);
		while($row = mysqli_fetch_array($result)){
			echo $row['birthday']."|";
		}
	}
}else if(strcmp($idx,"3")==0){
	$query = "SELECT categoryname FROM category WHERE categoryname LIKE '%$content%'";
	$result = mysqli_query($con,$query);
	while($row = mysqli_fetch_array($result)){
		echo $row['categoryname']."|";
	}
}else if(strcmp($idx,"4")==0){
	$query = "SELECT taskname FROM task WHERE taskname LIKE '%$content%'";
	$result = mysqli_query($con,$query);
	while($row = mysqli_fetch_array($result)){
		echo $row['taskname']."|";
	}
}else{
	echo "";
}

?>