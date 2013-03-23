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
	
	unset($a);
	$a = array();
	unset($id);
	$id = array();
	unset($tipe);
	$tipe = array();
	
	
	/* Searching */
	if ($filter == 1) { //Search All
		$query 	= "SELECT distinct full_name, username FROM user WHERE username LIKE '%$q%' OR email LIKE '%$q%' OR full_name LIKE '%$q%' OR birthdate LIKE '%$q%';";
		$result	= mysql_query($query);
		while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
			$a[] = $row["full_name"];
			$id[] = $row["username"];
			$tipe[] = "user";
		}
		$query 	= "SELECT distinct cat_id, cat_name FROM category WHERE cat_name LIKE '%$q%';";
		$result	= mysql_query($query);
		while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
			$a[] = $row["cat_name"];
			$id[] = $row["cat_id"];
			$tipe[] = "category";
		}
		$query 	= "SELECT DISTINCT * FROM ((task LEFT JOIN tag ON task.task_id = tag.task_id) LEFT JOIN comment ON task.task_id = comment.task_id) 
			WHERE task_name LIKE '%$q%' OR tag_name LIKE '%$q%' OR comment_content LIKE '%$q%'";
		$result	= mysql_query($query);
		while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
			// buang yang double-double
			$a[] = $row["task_name"];
			$id[] = $row["task_id"];
			$tipe[] = "task";
		}
	}
	else
	if ($filter == 2) { //Search User (username, email, nama lengkap, birthdate)
		$query 	= "SELECT DISTINCT full_name, username FROM user WHERE username LIKE '%$q%' OR email LIKE '%$q%' OR full_name LIKE '%$q%' OR birthdate LIKE '%$q%';";
		$result	= mysql_query($query);
		while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
			$a[] = $row["full_name"];
			$id[] = $row["username"];
			$tipe[] = "user";
		}
	}
	else 
	if ($filter == 3) { //Search Category
		$query 	= "SELECT DISTINCT cat_id, cat_name FROM category WHERE cat_name LIKE '%$q%';";
		$result	= mysql_query($query);
		while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
			$a[] = $row["cat_name"];
			$id[] = $row["cat_id"];
			$tipe[] = "category";
		}
	}
	else 
	if ($filter == 4) { //Search Task (task name, tag, comment)
		$query 	= "
		SELECT DISTINCT * FROM ((task LEFT JOIN tag ON task.task_id = tag.task_id) LEFT JOIN comment ON task.task_id = comment.task_id) 
		WHERE task_name LIKE '%$q%' OR tag_name LIKE '%$q%' OR comment_content LIKE '%$q%'";
		$result	= mysql_query($query);
		while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
			// buang yang double-double
			$a[] = $row["task_name"];
			$id[] = $row["task_id"];
			$tipe[] = "task";
		}
	}
	
	//lookup all hints from array if length of q>0
	if (strlen($q) > 0) {
		$hint = "";
		if (count($a) > 0) {
			for($i=0; $i<count($a); $i++) {
				if ($hint == "") {
					$hint = "<div style='cursor:pointer;display:block;margin-bottom:2px;width:100%;' onclick=\"javascript:searchResult('".$id[$i]."' ,'".$tipe[$i]."');\">". $a[$i] ."</div>";
				}
				else { 
					$hint = $hint. "<br> <div style='cursor:pointer;display:block;margin-bottom:2px;width:100%;' onclick=\"javascript:searchResult('".$id[$i]."' ,'".$tipe[$i]."');\">".$a[$i]."</div>";
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