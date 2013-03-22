	<?php
		$taskid=$_GET["t"];
		$status=$_GET["s"];
		
		$con = mysql_connect('localhost', 'root', 'rootadmin');
		if (!$con)
		{
			die('Could not connect: ' . mysql_error());
		}
		
		mysql_select_db("progin_405_13510035", $con);
		
		$sql="UPDATE task SET status='".$status."' WHERE task_id = '".$taskid."'";

		mysql_query($sql);
		
		echo($taskid.",".$status);
		
		mysql_close($con);
		
	?>
