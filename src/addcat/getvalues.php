<?php
	session_start();
	require_once("../connectDB.php");
	
	$type = ($_POST['type'] == "category") ? $_POST['type'] : "username";
	$value = $_POST['value'];
	$table = ($type == "category") ? $type : "login";
	$query = "SELECT * FROM $table WHERE $type LIKE '$value%'";
	$result = mysql_query($query);
	
	if ($result)
	{
		while($row = mysql_fetch_array($result))
		{
			if (($output = $row[$type]) != $_SESSION['username'])
			{
				echo "<p>".$output."</p>";
			}
		}
	}
	else
	{
		echo "";
	}
	
	mysql_close($con);
?>