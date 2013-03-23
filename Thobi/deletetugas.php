	<?php
		$taskid=$_GET["t"];
		
		$con = mysql_connect('localhost', 'progin', 'progin');
		if (!$con)
		{
			die('Could not connect: ' . mysql_error());
		}
		
		mysql_select_db("progin_405_13510029", $con);
		
		$sql="DELETE FROM comment WHERE commented_task = '".$taskid."'";
		mysql_query($sql);
		$sql="DELETE FROM attachment WHERE task_id = '".$taskid."'";
		mysql_query($sql);
		$sql="DELETE FROM task_incharge WHERE task_id = '".$taskid."'";
		mysql_query($sql);
		$sql="DELETE FROM tasktag WHERE task_id = '".$taskid."'";
		mysql_query($sql);
		$sql="DELETE FROM task_creator WHERE task_id = '".$taskid."'";
		mysql_query($sql);
		$sql="DELETE FROM task WHERE task_id = '".$taskid."'";
		mysql_query($sql);
		
		echo($taskid.",".$status);
		
		mysql_close($con);
		
	?>
