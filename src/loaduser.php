<?php
	
	$con = mysql_connect('localhost', 'progin', 'progin');
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("progin_405_13510029", $con);
		
	
	$sql="SELECT username FROM user";

	$result = mysql_query($sql);
	
	echo ("<datalist id='listuser'>");
		
	while ($row = mysql_fetch_array($result)) {
		echo("<option value=\"".$row["username"]."\">");
	}
		
	echo ("</datalist>");
		
	mysql_close($con);
		
?>