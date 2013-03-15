<?php
//This is only displayed if they have submitted the form 

	$suggest = $_GET['suggest'];
	$con = mysql_connect("localhost:3306", "root", "");
	if (!$con) {
		die('Could not connect: ' . mysql_error());
	}

	mysql_select_db("progin_405_13510057", $con);

		//If they did not enter a search term we give them an error 
		if ($suggest == "") {
			echo "<p>Tolong masukkan data yang ingin anda cari";
			exit;
		}


		// We preform a bit of filtering 
		$suggest = strtoupper($suggest);
		$suggest = strip_tags($suggest);
		$suggest = trim($suggest);
		
		//Now we search for our search term, in the field the user specified 
		$data = mysql_query("SELECT * FROM user WHERE upper(username) LIKE'%$suggest%'");
		$rows = mysql_num_rows($data);

		//Melakukan Query dengna menambahkan fungsi limit
		if ($rows == 0)
		{
			
		}
		else
		{
			$data = mysql_query("SELECT * FROM user WHERE upper(username) LIKE'%$suggest%'");
			echo "<datalist id=\"user\">";
			while ($info = mysql_fetch_array($data)) {
				echo "<option value=\"".$info['username']."\">";
				echo $info['username'];
			}
			echo "</datalist>";
		}
	mysql_close($con);
?>
