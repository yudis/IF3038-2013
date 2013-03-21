<?php
	require "profile.php";
	require "config.php";
	session_start();
	$username = $_SESSION['username'];
	$editfullname = $_POST['editname'];
	$editdob = $_POST['editdob'];
	$editpassword = md5($_POST['editpassword1']);
	// if($editfullname==$_SESSION['fullname'] && $editdob==$_SESSION['birthday'] && $editpassword==$_SESSION['password']){
	if($editfullname=="nugroho satrijandi" && $editdob=="1992-10-27"){
		$_SESSION['IsEdit'] = true;
	}else{
		$_SESSION['IsEdit'] = false;
		move_uploaded_file($_FILES["editavatar"]["tmp_name"],"img/".$_FILES["editavatar"]["name"]);
		$target = "img/"."EndyDoankavatar.docx"; //session username sbg "target"avatar.docx
		$edit_sql = "UPDATE user SET fullname='$editfullname', birthday='$editdob', avatar='$target',password='$editpassword' WHERE username ='EndyDoank'";
		mysqli_query($con,$edit_sql);
	}
	header('Location: profile.php');
?>