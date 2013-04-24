<?php
	session_start();
	ob_start();
	if (isset($_SESSION['username'])) {
		$username = $_SESSION['username']; 
	}
	
	/* Configuring Server & Database */
	$host        =    'localhost';
	$user        =    'root';
	$password    =    '';
	$database    =    'progin_405_13510086';

	$con        =    mysql_connect($host,$user,$password) or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');
	
	$categoryID = $_POST['delete_category_id'];
	$categoryName = $_POST['delete_category_name'];
	
	unset($deletedTaskID);
	$deletedTaskID = array();
	
	/* Delete Category Script */
	if (isset($_POST['delete_category_button'])) { //when login button is pressed
		echo "
		<script> 
			var deleteCategoryConfirm = confirm('Are you sure you want to delete this CATEGORY and all the TASKS related?');
			if (deleteCategoryConfirm == false) {
				window.location.href='dashboard.php';
			}
		</script>";
		
		/* The following will be executed if confirmed */
		$task_query = "SELECT task_id from task WHERE cat_name='$categoryName'";
		$task_result = mysql_query($task_query);
		$i = 0;
		while ($task_row = mysql_fetch_array($task_result, MYSQL_ASSOC)) { 
			$deletedTaskID[] = $task_row['task_id'];
			//echo "task id = ".$task_row['task_id']."</br>";
			
			// delete task_assignee
			$query = "DELETE FROM `task_asignee` WHERE task_id='$deletedTaskID[$i]'";
			mysql_query($query);
			$i++;
		}

		// delete task
		if (count($deletedTaskID) > 0) {
			for($j=0; $j<count($deletedTaskID); $j++) {
				$query = "DELETE FROM `task` WHERE task_id='$deletedTaskID[$j]'";
				mysql_query($query);
			}
		}
		
		// delete cat_assignee
		$query = "DELETE FROM `cat_asignee` WHERE cat_id='$categoryID'";
		mysql_query($query);
			
		// delete category 
		$query = "DELETE FROM `category` WHERE cat_id='$categoryID'";
		mysql_query($query);
		
		header('location:dashboard.php');
	} 
?>