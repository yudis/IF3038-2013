<?php 
	require("../php/init_function.php");
	$con = getConnection();
	$result = mysqli_query($con,"SELECT * FROM category");
	
	echo "<div id=\"category-list\">";
	
	echo "<div><hr id=\"border\"></div>";
	
	while($row = mysqli_fetch_array($result))
  	{
		echo "<div class=\"task-category-body\">";
		echo "	<div class=\"category-title\"><b>".$row['categoryname']."</b></div>";
		echo "	<div class=\"category-title\"><b>Created by : ".$row['username']."</b></div>";
		echo "	<div class=\"category-title\"><b>Created date : ".$row['createddate']."</b></div>";
		echo "	<ul>";
			$con2 = getConnection();
			$result2 = mysqli_query($con2,"SELECT * FROM task WHERE categoryid = ".$row['categoryid']);
			while($row2 = mysqli_fetch_array($result2))
  			{
				echo "    	<li><a href = \"task_page.php\">".$row2['taskname']."</a><div class=\"task-tag\">submit by : <b><i>".$row2['username']."</i></b>, deadline : ".$row2['deadline'].", status : ".$row2['status']."</div></li>";
			}
        echo "   <br><div class = \"add-task\"><a href = \"add_task.php\">Create New Task</a></div>";
        echo "  </ul>";
		echo "</div>";
		echo "<div><hr id=\"border\"></div>";
	}
	echo("</div>");
?>