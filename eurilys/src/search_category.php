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
	$query 	= "SELECT * FROM category WHERE cat_id='$q'";
	$result	= mysql_query($query);
	while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
		$cat_id = $row['cat_id'];
		$cat_name = $row['cat_name'];
		$cat_creator = $row['cat_creator'];
	}
	
	//Set the search result at response
	if (strlen($q) > 0) {		
		$response = "<div class='half_div'> 
			<div class='user_search_result_label'> Category Name  </div> <div class='left'> ".$cat_name." </div>
			<div class='clear'></div><br>
			<div class='user_search_result_label'> Creator  </div> <div class='left'> ".$cat_creator." </div>
			</div>";
		//$response = $response ."<div class='half_div'>  <div class='clear'></div> <br>";
		//$response = $response ."<div class='user_search_result_label'> Email </div><div class='left'> ".$email." </div><div class='clear'></div><br>";	
		//$response = $response . "<div class='user_search_result_label'>  Birthdate  </div> <div class='left'> ".$birthdate." </div><div class='clear'></div></div>";
	}

	//output the response
	echo $response;
?>