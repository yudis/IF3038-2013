<?php
	$username=$_POST["uname"];
	$password=$_POST["pwd"];
	$fullname=$_POST["name"];
	$email=$_POST["email"];
	$unparsed=$_POST["bday"];
	
	$birthday= substr($unparsed,6,4).'-'.substr($unparsed,3,2).'-'.substr($unparsed,0,2);
	
	$con = mysql_connect('localhost', 'root', 'rootadmin');
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
		mysql_select_db("progin_405_13510035", $con);
		
	
	$sql="INSERT INTO user VALUES ('".$username."', '".$password."', '".$fullname."','".$email."', '".$birthday."', '".$_FILES["ava"]["name"]."')";

	mysql_query($sql);
	
	mysql_close($con);
		
	$allowedExts = array("jpg", "jpeg", "gif", "png");
	$extension = end(explode(".", $_FILES["ava"]["name"]));
	if ((($_FILES["ava"]["type"] == "image/gif")
	|| ($_FILES["ava"]["type"] == "image/jpeg")
	|| ($_FILES["ava"]["type"] == "image/png")
	|| ($_FILES["ava"]["type"] == "image/jpg"))
	&& ($_FILES["ava"]["size"] < 2000000)
	&& in_array($extension, $allowedExts))
	{
		if ($_FILES["ava"]["error"] > 0)
		{
			echo "Return Code: " . $_FILES["ava"]["error"] . "<br>";
		}
		else
		{
			echo "Upload: " . $_FILES["ava"]["name"] . "<br>";
			echo "Type: " . $_FILES["ava"]["type"] . "<br>";
			echo "Size: " . ($_FILES["ava"]["size"] / 1024) . " kB<br>";
			echo "Temp file: " . $_FILES["ava"]["tmp_name"] . "<br>";

			if ($_FILES["ava"]["type"] == "image/jpg")
				move_uploaded_file($_FILES["ava"]["tmp_name"], "avatar/".$username.".jpg");
			else if ($_FILES["ava"]["type"] == "image/jpeg")
				move_uploaded_file($_FILES["ava"]["tmp_name"], "avatar/".$username.".jpg");
		}
	}
	else
	{
		echo "Invalid file";
	}
	
	header( "Location: dashboard.php?uname=".$username."&cat=all" ) ;
?>