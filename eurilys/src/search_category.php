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
	unset($taskList);
	$taskList = array();
	
	/* Searching user */
	$query 	= "SELECT * FROM category WHERE cat_id='$q'";
	$result	= mysql_query($query);
	while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
		$cat_id = $row['cat_id'];
		$cat_name = $row['cat_name'];
		$cat_creator = $row['cat_creator'];
	}
	
	/* Search Corresponding task */
	$query 	= "SELECT task_name, task_id FROM task WHERE cat_name='$cat_name'";
	$result	= mysql_query($query);
	
	while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
		$taskList[] = $row['task_name'];
	}
	
	//Set the search result at response
	if (strlen($q) > 0) {		
		$response = "<div class='task_view'>
			<div class='cat_search_result_label'> Category Name  </div> <div class='left'> ".$cat_name." </div>
			<div class='clear'></div><br>
			<div class='cat_search_result_label'> Creator  </div> <div class='left'> ".$cat_creator." </div>
			<div class='clear'></div><br>
			<div class='cat_search_result_label'> List of Task </div>";
		
		if (count($taskList) > 0) {
			for($i=0; $i<count($taskList); $i++) {
				if ($i != 0) {
					$response = $response . "<div class='cat_search_result_label'> &nbsp; </div>";
				}
				$response = $response . "<div class='left'>" .$taskList[$i] ."</div><div class='clear'></div><br>";
			}
		}
		$response = $response."</div>";
	}

	//output the response
	echo $response;
?>