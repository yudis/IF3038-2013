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
		//echo $username;
	}
	
	/* Add Category Script */	
	$catName= mysql_real_escape_string($_POST['add_category_name']);
	$catAsigneeName = $_POST['add_category_asignee_name'];
	if (isset($_POST['add_category_button'])) { //when login button is pressed
		
		$query 	= "INSERT INTO `category`(`cat_name`, `cat_creator`) VALUES ('$catName','$username')";
		$result	= mysql_query($query);
		
		$query1 	= "SELECT cat_id FROM category WHERE cat_name='$catName'";
		$result1	= mysql_query($query1);
		while ($row = mysql_fetch_array($result1, MYSQL_ASSOC)) {
			$categoryID = $row["cat_id"];
			echo "Cat id = ".$categoryID;
		}
		
		$assigneeArray = explode(',', $catAsigneeName); 		
		for ($i=0; $i<count($assigneeArray); $i++) {
			//echo $assigneeArray[$i];
			$query2 	= "INSERT INTO `cat_asignee`(`cat_id`, `username`) VALUES ('$categoryID','$assigneeArray[$i]')";
			$result2	= mysql_query($query2);
		}
		
		
		header('location:dashboard.php');
	}



?>