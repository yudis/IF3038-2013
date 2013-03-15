<html>
<head>
</head>
<body>
<?php
	require "profile.php";
	require "config.php";
	$fullname = $_POST['editname'];
	$dob = $_POST['editdob'];
	
	move_uploaded_file($_FILES["editavatar"]["tmp_name"],"img/".$_FILES["editavatar"]["name"]);
	
	$target = "img/"."EndyDoankavatar.docx"; //session username sbg "target"avatar.docx
	
	$password = md5($_POST['editpassword1']);
	$edit_sql = "UPDATE user SET fullname='$fullname', birthday='$dob', avatar='$target',password='$password' WHERE username ='EndyDoank'";
	mysqli_query($con,$edit_sql);
	header( 'Location: profile.php' );
?>
</body>

</html>
