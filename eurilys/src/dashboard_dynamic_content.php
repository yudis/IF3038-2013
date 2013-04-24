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
	
	$response = "";
	
	if (isset($_SESSION['username'])) {
		$username = $_SESSION['username']; 
	}
	
	/* Get the task CATEGORY we're going to generate to the HTML page */
	$q	= $_GET["q"];
	$taskdone= $_GET["taskdone"]; //0 = finishTask(), 1 = deleteTask()
	$taskid = $_GET['taskid'];
	
	if ($taskdone == 0) { //finishTask()
		$query 	= "UPDATE task SET task_status='1' WHERE task_id='$taskid';";
		$result	= mysql_query($query);
	}
	if ($taskdone == 1) { //deleteTask();
		$query 	= "DELETE FROM `task` WHERE task_id='$taskid';";
		$result	= mysql_query($query);
	}
	
	/* Searching for Task */
	if ($q == 'all') {
		$query 	= "SELECT * FROM (`task` natural join `task_asignee`) where (task_creator ='$username') OR (username='$username') ";
		$result	= mysql_query($query);
		while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
			
			//Get 'tag'
			$taskID = $row['task_id'];
			$tag_query = "SELECT * from tag WHERE task_id='$taskID'";
			$tag_result = mysql_query($tag_query);
			$tagResponse = "";
			while ($tag_row = mysql_fetch_array($tag_result, MYSQL_ASSOC)) { 
				$tagResponse = $tagResponse.$tag_row['tag_name']." , ";
			}
			
			//Generate response
			$response = $response. 
			"
			<br>
			<div class='task_view' id='".$row['task_id']."'>";
			
			if ($row['task_creator'] == $username) {
				$response = $response. 
				"<div id='delete_".$row['task_id']."' onclick='javascript:deleteTask(".$row['task_id'].")' class='task_done_button'> Delete </div>
				<div class='task_done_button'>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</div>"; 
			}
			
			if ($row['task_status'] == 0) {
				$response = $response. 			
				"<div id='finish_".$row['task_id']."' onclick='javascript:finishTask(".$row['task_id'].")' class='task_done_button'> Mark as Finished </div>";
			}
			else {
				$response = $response."<img src='../img/yes.png' class='task_done_button' alt=''/>";
			}
			
			$response = $response. 			
				"
				<div id='task_name_ltd' class='left dynamic_content_left'>Task Name</div>
				<div id='task_name_rtd' class='left dynamic_content_right darkBlueLink' onclick='javascript:viewTask(".$row['task_id'].")'> ".$row['task_name']." </div>
				<br><br>
				<div id='deadline_ltd' class='left dynamic_content_left'>Deadline</div>
				<div id='deadline_rtd' class='left dynamic_content_right'> ".$row['task_deadline']." </div>
				<br><br>
				<div id='tag_ltd' class='left dynamic_content_left'>Tag</div>
				<div id='tag_rtd' class='left dynamic_content_right'>".$tagResponse."</div>
				<br>
				<div class='task_view_category'> ".$row['cat_name']." </div>
				<br>
			</div>";
		}
	}
	else {
		//search category id where category creator = username
		$catQuery = "SELECT cat_id, cat_name FROM category WHERE cat_name = '$q' AND cat_creator =  '$username'";
		$catResult = mysql_query($catQuery);
		
		$catID = "";
		$catName = "";
		
		while ($catRow = mysql_fetch_array($catResult, MYSQL_ASSOC)) {
			$catID = $catRow['cat_id'];
			$catName = $catRow['cat_name'];
		}
		if ($catName == $q) {
			echo"
			<form method='POST' action='delete_category.php'>
				<input type='hidden' name='delete_category_id' value='$catID'/>
				<input type='hidden' name='delete_category_name' value='$catName'/>
				<input type='submit' id='delete_category_button' name='delete_category_button' class='link_red top20' value='Delete Category'/> 
			</form>";
		}
		
		//searching for specific task (per category)
		$query 	= "SELECT * FROM task where cat_name='$q';";
		$result	= mysql_query($query);
		while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
			
			//Get 'tag'
			$taskID = $row['task_id'];
			$tag_query = "SELECT * from tag WHERE task_id='$taskID'";
			$tag_result = mysql_query($tag_query);
			$tagResponse = "";
			while ($tag_row = mysql_fetch_array($tag_result, MYSQL_ASSOC)) { 
				$tagResponse = $tagResponse.$tag_row['tag_name']." , ";
			}
			
			//Generate response
			$response = $response. 
			"
			<br>
			<div class='task_view' id='".$row['task_id']."'>";
			
			if ($row['task_creator'] == $username) {
				$response = $response. 
				"<div id='delete_".$row['task_id']."' onclick='javascript:deleteTask(".$row['task_id'].")' class='task_done_button'> Delete </div>
				<div class='task_done_button'>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</div>"; 
			}
			
			if ($row['task_status'] == 0) {
				$response = $response. 			
				"<div id='finish_".$row['task_id']."' onclick='javascript:finishTask(".$row['task_id'].")' class='task_done_button'> Mark as Finished </div>";
			}
			else {
				$response = $response."<img src='../img/yes.png' class='task_done_button' alt=''/>";
			}
			
			$response = $response. 			
				"
				<div id='task_name_ltd' class='left dynamic_content_left'>Task Name</div>
				<div id='task_name_rtd' class='left dynamic_content_right darkBlueLink' onclick='javascript:viewTask(".$row['task_id'].")'> ".$row['task_name']." </div>
				<br><br>
				<div id='deadline_ltd' class='left dynamic_content_left'>Deadline</div>
				<div id='deadline_rtd' class='left dynamic_content_right'> ".$row['task_deadline']." </div>
				<br><br>
				<div id='tag_ltd' class='left dynamic_content_left'>Tag</div>
				<div id='tag_rtd' class='left dynamic_content_right'>".$tagResponse."</div>
				<br>
				<div class='task_view_category'> ".$row['cat_name']." </div>
				<br>
			</div>
			";
		}
	}

	//output the response
	echo $response;
?>