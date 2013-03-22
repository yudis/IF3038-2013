<?php
	
	$con = mysql_connect('localhost', 'root', 'rootadmin');
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("progin_405_13510035", $con);
		
	
	$sql="SELECT username FROM user";

	$result = mysql_query($sql);
	
	echo ("<datalist id='user'>");
		
	while ($row = mysql_fetch_array($result)) {
		echo("<option value=\"".$row["username"]."\">");
	}
		
	echo ("</datalist>");
		
	mysql_close($con);
		
?>