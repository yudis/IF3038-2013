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
	
	$task_id		= mysql_real_escape_string($_POST['edit_task_id']);
	$task_deadline		= mysql_real_escape_string($_POST['edit_task_deadline']);
	$task_assignee   	= mysql_real_escape_string($_POST['edit_task_assignee']);
	$task_tag    		= mysql_real_escape_string($_POST['edit_task_tag']);
	
	if (isset($_POST['edit_task_submit'])) { 
		$query = "UPDATE task SET task_deadline='$task_deadline' WHERE task_id='$task_id'";
		mysql_query($query);
		
		//insert assignee array
		$assigneeArray = explode(',', $task_assignee); 		
		for ($i=0; $i<count($assigneeArray); $i++) {
			$query2 	= "INSERT INTO `task_asignee` (`task_id`, `username`) VALUES ('$taskID','$assigneeArray[$i]')";
			$result2	= mysql_query($query2);
		}
		
		/* -> belum
		$tagArray = explode(',', $task_tag); 		
		for ($i=0; $i<count($tagArray); $i++) {
			$query4 = "SELECT * FROM tag WHERE task_id='$taskID'";
			$result = mysql_query($query4);
			while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
				if ($
				$taskID = $row["task_id"];
				//echo "Task id = ".$taskID;
			}
			if (mysql_num_rows($result) > 0) {
				
			}
			$query3  = "INSERT INTO `tag` (`tag_name`, `task_id`) VALUES ('$tagArray[$i]','$taskID')";
			$result3 = mysql_query($query3);
		} */
	}
	
	header('location:dashboard.php');
?>