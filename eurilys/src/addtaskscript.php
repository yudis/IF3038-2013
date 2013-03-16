<?php 
	session_start();
	session_destroy();
	ob_start();
	
	/* Configuring Server & Database */
	$host        =    'localhost';
	$user        =    'root';
	$password    =    '';
	$database    =    'progin_405_13510086';

	$con        =    mysql_connect($host,$user,$password) or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');
	
	/* Add Task Script */
	$task_name		= mysql_real_escape_string($_POST['task_name_input']);
	$assignee   	= mysql_real_escape_string($_POST['assignee_input']);
	$task_deadline  = $_POST['deadline_input'];
		if (isset($_SESSION['username'])) {
		$username = $_SESSION['username']; 
	}
	$tag    		= mysql_real_escape_string($_POST['tag_input']);
	
	if (isset($_POST['add_task_button'])) { 
		$query	=    
		"INSERT INTO task (`task_name`, `task_deadline`,`task_creator`) 
		VALUES ('$task_name','$task_deadline','$username' )";
		
		$res	=    mysql_query($query);
	
		
		header('location:addtask.php'); //Redirect To Success Page
	}
?>