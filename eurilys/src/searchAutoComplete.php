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
	
	unset($a);
	$a = array();
	unset($id);
	$id = array();
	
	
	/* Searching */
	if ($filter == 1) { //Search All
		$query 	= "SELECT full_name, username FROM user WHERE username LIKE '%$q%' OR email LIKE '%$q%' OR full_name LIKE '%$q%' OR birthdate LIKE '%$q%';";
		$result	= mysql_query($query);
		while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
			$a[] = $row["full_name"];
			$id[] = $row["username"];
		}
		$query 	= "SELECT cat_id, cat_name FROM category WHERE cat_name LIKE '%$q%';";
		$result	= mysql_query($query);
		while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
			$a[] = $row["cat_name"];
			$id[] = $row["cat_id"];
		}
		$query 	= "SELECT * FROM ((task LEFT JOIN tag ON task.task_id = tag.task_id) LEFT JOIN comment ON task.task_id = comment.task_id) 
			WHERE task_name LIKE '%$q%' OR tag_name LIKE '%$q%' OR comment_content LIKE '%$q%'";
		$result	= mysql_query($query);
		while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
			// buang yang double-double
			$a[] = $row["task_name"];
			$id[] = $row["task_id"];
		}
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
		$query 	= "
		SELECT * FROM ((task LEFT JOIN tag ON task.task_id = tag.task_id) LEFT JOIN comment ON task.task_id = comment.task_id) 
		WHERE task_name LIKE '%$q%' OR tag_name LIKE '%$q%' OR comment_content LIKE '%$q%'";
		$result	= mysql_query($query);
		while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
			// buang yang double-double
			$a[] = $row["task_name"];
			$id[] = $row["task_id"];
		}
	}
	
	//lookup all hints from array if length of q>0
	if (strlen($q) > 0) {
		$hint = "";
		if (count($a) > 0) {
			for($i=0; $i<count($a); $i++) {
				if ($hint == "") {
					$hint = "<div style='cursor:pointer;margin-bottom:5px;width:100%;' onclick='javascript:searchTask('".$id[$i]."');'>". $a[$i] ."</div>";
				}
				else { 
					//$hint = $hint." , ".$a[$i];
					$hint = $hint. "<br> <div style='cursor:pointer;margin-bottom:5px;width:100%;' onclick='javascript:searchTask('".$id[$i]."');'>". $a[$i] ."</div>"; 
				}
			}
		}
	}

	// Set output to "no suggestion" if no hint were found
	// or to the correct values
	if ($hint == "") {
		$response = "no suggestion";
	}
	else {
		$response=$hint;
	}

	//output the response
	echo $response;
?>