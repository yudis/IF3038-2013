<?php 
	require("../php/init_function.php");
	$useractive = $_SESSION['userlistapp'];
	$con = getConnection();
	$result = mysqli_query($con,"SELECT * FROM category");
	
	echo "<div id=\"category-list\">";
	
	echo "<div><hr id=\"border\"></div>";
	
	while($row = mysqli_fetch_array($result))
  	{
		echo "<div class=\"task-category-body\">";
		echo "	<div><div class=\"category-title\"><b>".$row['categoryname']."</b></div><div class=\"delete-category\" align=\"right\">";
		
		if(strcmp($useractive,$row['username']) == 0){
			echo "	<a href=\"../php/deletecategory.php?id=".$row['categoryid']."\"><input name=\"delete\" type=\"button\" value=\"Delete Category\"/></a>";
		}
		echo "</div>";
		echo "</div>";
		echo "	<div class=\"category-title-secondary\">Created by : <i>".$row['username']."</i>, at ".$row['createddate']."</div><br>";
		echo "	<ul>";
			$con2 = getConnection();
			$result2 = mysqli_query($con2,"SELECT * FROM task WHERE categoryid = ".$row['categoryid']);
			while($row2 = mysqli_fetch_array($result2))
  			{
				echo "    	<li><a href = \"task_page.php\">".$row2['taskname']."</a><div class=\"task-tag\">submit by : <b><i>".$row2['username']."</i></b>, deadline : ".$row2['deadline'].", status : <b id=\"red-text\">".$row2['status']."</b></div>";
				echo "		<br><div><div id=\"task-tag-delete\"><a href=\"#\" onClick=\"confirmTask()\"><i>Delete Task</i></a></div><div class=\"task-tag\">Set as <a href=\"javascript:setCompleteStatus()\">Completed Task</a></div></div><br><br>";
				echo "		<div id=\"task-tag\">Tag :<br>";
				$taskid = getTaskId($row2['taskname'],$row['categoryid']);
				$con3 = getConnection();
				$result3 = mysqli_query($con3,"SELECT tagid FROM task_tag WHERE taskid = '".$taskid."'");
				while($row3 = mysqli_fetch_array($result3))
				{
					$tagname = getTagname($row3['tagid']);
					echo "<u>".$tagname."</u> ";
				}
				echo "</div>";
				echo "		</li>";
			}
        echo "   <br><br><br><div class = \"add-task\"><a href = \"add_task.php\"><button>+New Task</button></a></div>";
        echo "  </ul>";
		echo "</div>";
		echo "<div><hr id=\"border\"></div>";
	}
	echo("</div>");
?>