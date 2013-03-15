<?php
	/* Configuring Server & Database */
	$database    =    'progin_405_13510086';
	$con        =    mysql_connect('localhost','root','') or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');
	
	/* Get category as the parameter from the URL */
	$q	= $_GET["q"];
	
	/* Set array */
	unset($a);
	$a = array();	
	
	/* Searching user */
	$query 	= "SELECT * FROM task WHERE task_id='$q'";
	$result	= mysql_query($query);
	while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
		$task_name	= $row['task_name'];
		$task_status	= $row['task_status'];
		$task_deadline	= $row['task_deadline'];
		$cat_name	= $row['cat_name'];
		$task_creator	= $row['task_creator'];
	}	
	
	//Set the search result at response
	if (strlen($q) > 0) {		
		$response = 
			"<div class='task_view'>
				<div class='cat_search_result_label'> Task Name  </div> <div class='left'> ".$task_name." </div>
				<div class='clear'></div><br>
				<div class='cat_search_result_label'> Status </div> <div class='left'> ".$task_status." </div>
				<div class='clear'></div><br>
				<div class='cat_search_result_label'> Deadline  </div> <div class='left'> ".$task_deadline." </div>
				<div class='clear'></div><br>
				<div class='cat_search_result_label'> Category  </div> <div class='left'> ".$cat_name." </div>
				<div class='clear'></div><br>
				<div class='cat_search_result_label'> Creator  </div> <div class='left'> ".$task_creator." </div>
				<div class='clear'></div><br>
			</div>";
	}

	//output the response
	echo $response;
?>