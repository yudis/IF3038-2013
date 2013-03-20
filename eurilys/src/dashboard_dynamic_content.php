<?php
	/* Configuring Server & Database */
	$host        =    'localhost';
	$user        =    'root';
	$password    =    '';
	$database    =    'progin_405_13510086';
	$con        =    mysql_connect($host,$user,$password) or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');
	
	$response = "";
	
	/* Get the task CATEGORY we're going to generate to the HTML page */
	$q	= $_GET["q"];
		
	/* Searching for Task */
	if ($q == 'all') {
		$query 	= "SELECT * FROM task;";
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
			<div class='task_view' id='".$row['task_id']."'>
				<img src='../img/done.png' id='finish_".$row['task_id']."' onclick='javascript:finishTask(".$row['task_id'].")' class='task_done_button' alt=''/>
				<div id='task_name_ltd' class='left dynamic_content_left'>Task Name</div>
				<div id='task_name_rtd' class='left dynamic_content_right'> <a href='taskdetail_img.php'> ".$row['task_name']." </a> </div>
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
			<div class='task_view' id='".$row['task_id']."'>
				<img src='../img/done.png' id='finish_".$row['task_id']."' onclick='javascript:finishTask(".$row['task_id'].")' class='task_done_button' alt=''/>
				<div id='task_name_ltd' class='left dynamic_content_left'>Task Name</div>
				<div id='task_name_rtd' class='left dynamic_content_right'> <a href='taskdetail_img.php'> ".$row['task_name']." </a> </div>
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

	//output the response
	echo $response;
?>