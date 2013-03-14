<?php
	/* Configuring Server & Database */
	$host        =    'localhost';
	$user        =    'root';
	$password    =    '';
	$database    =    'progin_405_13510086';
	$con        =    mysql_connect($host,$user,$password) or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');
	
	/* Get the parameter from URL */
	$q	= $_GET["q"];
	$filter = $_GET["filter"];
	//echo "keyword = ".$q;
	//echo "filter = ".$filter; 
	
	$a = array();
	unset($a);
	
	$id = array();
	unset($id);
	
	/* Searching */
	if ($filter == 1) { //Search All
	
	}
	else
	if ($filter == 2) { //Search User (username, email, nama lengkap, birthdate)
		$query 	= "SELECT full_name, username FROM user WHERE username LIKE '%$q%' OR email LIKE '%$q%' OR full_name LIKE '%$q%' OR birthdate LIKE '%$q%';";
		$result	= mysql_query($query);
		while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
			$a[] = $row["full_name"];
			$id[] = $row["username"];
		}
	}
	else 
	if ($filter == 3) { //Search Category
		$query 	= "SELECT cat_id, cat_name FROM category WHERE cat_name LIKE '%$q%';";
		$result	= mysql_query($query);
		while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
			$a[] = $row["cat_name"];
			$id[] = $row["cat_id"];
		}
	}
	else 
	if ($filter == 4) { //Search Task (task name, tag, comment)
		$query 	= "SELECT task_id, task_name FROM task WHERE task_name LIKE '%$q%';";
		$result	= mysql_query($query);
		//tag & comment blm
		while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
			$a[] = $row["task_name"];
			$id[] = $row["task_id"];
		}
	}
	
	//lookup all hints from array if length of q>0
	if (strlen($q) > 0) {
		$hint="";
		for($i=0; $i<count($a); $i++) {
			if (strtolower($q)==strtolower(substr($a[$i],0,strlen($q)))) {
				if ($hint=="") {
					$hint=$a[$i];
				}
				else {
					$hint=$hint." , ".$a[$i];
				}
			}
		}
	}

	// Set output to "no suggestion" if no hint were found
	// or to the correct values
	if ($hint == "") {
		$response="no suggestion";
	}
	else {
		$response=$hint;
	}

	//output the response
	echo $response;
?>