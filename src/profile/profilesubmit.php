<?php
	require_once("../connectDB.php");
	
	$username = $_GET['u'];
	$password = $_POST['password'];
	$email = $_POST['email'];
	$fullname = $_POST['fullname'];
	$dob = $_POST['dob'];
	$photo = "";
	
	if ($_FILES['files']['name'])
	{
		$photo = $_FILES['files']['name'];
		move_uploaded_file($_FILES['files']['tmp_name'], '../images/'.$_FILES['files']['name']);
	}
	else
	{
		$query = "SELECT photo FROM login WHERE username = '$username'";
		$result = mysql_query($query);
		if ($result)
		{
			$row = mysql_fetch_array($result);
			$photo = $row['photo'];
		}
	}
	
	$query = "UPDATE login SET username = '$username', password = '$password', email = '$email', fullname = '$fullname', dob = '$dob', photo = '$photo' WHERE username = '$username'";
	$result = mysql_query($query);
	if ($result)
	{
		header("Location: index.php?u=".$_GET['u']."&e=".$_GET['e']);
	}
	
	mysql_close($con);
?>