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
	
	unset($a);
	$a = array();
	unset($id);
	$id = array();
	unset($tipe);
	$tipe = array();
	
	$query 	= "SELECT DISTINCT full_name, username FROM user WHERE username LIKE '%$q%' OR email LIKE '%$q%' OR full_name LIKE '%$q%' OR birthdate LIKE '%$q%';";
	$result	= mysql_query($query);
	while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
		$a[] = $row["full_name"];
		$id[] = $row["username"];
		$tipe[] = "user";
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