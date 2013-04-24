<?php
	/* Configuring Server & Database */
	$database    =    'progin_405_13510086';
	$con        =    mysql_connect('localhost','root','') or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');
	
	/* Get username as the parameter from the URL */
	$q	= $_GET["q"];
	
	/* Set array */
	unset($a);
	$a = array();	
	
	/* Searching user */
	$query 	= "SELECT * FROM user WHERE username='$q'";
	$result	= mysql_query($query);
	while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
		$username = $row['username'];
		$fullname = $row['full_name'];
		$birthdate = $row['birthdate'];
		$avatar = $row['avatar'];
		$email = $row['email'];
	}
	
	//Set the search result at response
	if (strlen($q) > 0) {		
		$response = "<div class='half_div'><div id='upperprof'><img id='mainpp' width='225' src='$avatar' alt=''><div id='namauser'>".$fullname."</div></div> <br/><br/></div>";
		$response = $response ."<div class='half_div'> <div class='user_search_result_label'> Username  </div> <div class='left'> ".$username." </div> <div class='clear'></div> <br>";
		$response = $response ."<div class='user_search_result_label'> Email </div><div class='left'> ".$email." </div><div class='clear'></div><br>";	
		$response = $response . "<div class='user_search_result_label'>  Birthdate  </div> <div class='left'> ".$birthdate." </div><div class='clear'></div></div>";
	}

	//output the response
	echo $response;
?>