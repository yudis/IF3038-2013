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
			$query2 	= "INSERT INTO `task_asignee` (`task_id`, `username`) VALUES ('$task_id','$assigneeArray[$i]')";
			$result2	= mysql_query($query2);
		}
		
		
		$tagArray = explode(',', $task_tag); 
		unset($tags);
		$tags = array();
		for ($i=0; $i<count($tagArray); $i++) {
			$query4 = "SELECT tag_name FROM tag WHERE task_id='$task_id'";
			$result = mysql_query($query4);
			while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
				$tags[] = $row['tag_name'];
				if (in_array($row['tag_name'], $tagArray)) {
				}
				else {					
					$tagname = $row['tag_name'];
					$query5 = "DELETE FROM tag WHERE tag_name='$tagname' AND task_id='$task_id'";
				}
			}
			if (in_array($tagArray[$i], $tags)) {
				$query6 	= "INSERT INTO `tag` (`tag_name`, `task_id`) VALUES ('$tagArray[$i]','$task_id')";
				$result6	= mysql_query($query6);
			}
		}
	}
	
	header('location:dashboard.php');
?>