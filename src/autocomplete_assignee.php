<?php
	require_once("connectdb.php");
	$query = "SELECT idaccounts, username FROM accounts WHERE username LIKE '%".$_GET["q"]."%' ORDER BY username LIMIT 10";
	//echo $query;
	$result = mysql_query($query);
		
	while($row = mysql_fetch_array($result))
	{
		echo '<div class="hasil_ac" onClick="autocomplete_diklik(\''.$row["username"].'\')">'.$row["username"].'</div>';
	}
	
	mysql_close();
?>