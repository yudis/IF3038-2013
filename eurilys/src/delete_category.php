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
	$categoryID = $_POST['delete_category_id'];
	$categoryName = $_POST['delete_category_name'];
	
	/* Delete Category Script */
	if (isset($_POST['delete_category_button'])) { //when login button is pressed
		// confirmation 
		// delete task
		// delete task_assignee
		// delete category 
		// delete cat_assignee
		// redirect to dashboard :header('location:dashboard.php');
	} 
?>