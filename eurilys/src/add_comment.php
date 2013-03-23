<?php
	session_start();
	ob_start();

	/* Configuring Server & Database */
	$host        =    'localhost';
	$user        =    'root';
	$password    =    '';
	$database    =    'progin_405_13510086';

	$con        =    mysql_connect($host,$user,$password) or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');

	if (isset($_SESSION['username'])) {
		$username = $_SESSION['username']; 
	}
		
	/* Add Category Script */	
	$comment = mysql_real_escape_string($_POST['CommentBox']);
	$taskID  = $_POST['comment_task_id'];

	if (isset($_POST['add_comment_button'])) { //when add comment button is pressed		
		$query 	= "INSERT INTO `comment`(`comment_content`,`task_id`,`comment_creator`) VALUES ('$comment','$taskID','$username')";
		$result	= mysql_query($query);
		header('location:dashboard.php'); 
	}
?>