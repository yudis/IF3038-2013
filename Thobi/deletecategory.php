<?php
	$username=$_GET["uname"];
	$kategori=$_GET["cat"];
	
	$con = mysql_connect('localhost', 'root', 'rootadmin');
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("progin_405_13510035", $con);	

	$sql="SELECT task_id FROM task WHERE task_category = '".$kategori."'";
	mysql_query($sql);
	
	while ($row = mysql_fetch_array($result)) {
		$sql="DELETE FROM comment WHERE commented_task = '".$row["task_id"]."'";
		mysql_query($sql);
		$sql="DELETE FROM attachment WHERE task_id = '".$row["task_id"]."'";
		mysql_query($sql);
		$sql="DELETE FROM task_incharge WHERE task_id = '".$row["task_id"]."'";
		mysql_query($sql);
		$sql="DELETE FROM tasktag WHERE task_id = '".$row["task_id"]."'";
		mysql_query($sql);
		$sql="DELETE FROM task WHERE task_category = '".$kategori."'";
		mysql_query($sql);
	}
	
	$sql="DELETE FROM category_incharge WHERE category_id = '".$kategori."'";
	mysql_query($sql);
	$sql="DELETE FROM category WHERE category_id = '".$kategori."'";
	mysql_query($sql);

	echo ("true");
	
	mysql_close($con);
	
	header( "Location: dashboard.php?uname=".$username."&cat=all" ) ;
?>