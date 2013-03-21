<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>
	<?php
		$con=mysqli_connect("localhost","progin","progin","progin_405_13510035");
		$taskid = $_GET['idcheckbox'];
		$value = $_GET['checked'];
		if ($value == 'true'){
			$valuenum = '1';
		}else{
			$valuenum = '0';
		}
		
		if (mysqli_connect_errno($con))
		{
			echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}
		
		$result = mysqli_query($con,"UPDATE task SET status= '$valuenum' WHERE task_id = '$taskid'");
		
		$result = mysqli_query($con,"SELECT * FROM task WHERE task_id = '$taskid'");
		while($row = mysqli_fetch_array($result))
		{
		  $temp = $row['task_id'];
		  echo '<div class="judulhasilsearch">';
		  echo $row['task_name'];
		  
		  echo "</div>";
		  echo "<div>";
		  echo "Deadline : ";
		  echo $row['deadline'];
		  echo "</div>";
		  echo "<div>";
		  echo "Tag : ";
		  $subresult = mysqli_query($con,"SELECT tag.tag_name FROM tag,tasktag WHERE tasktag.task_id = '$temp' and tag.tag_id = tasktag.tag_id");
			while($subrow = mysqli_fetch_array($subresult))
			{
				echo $subrow['tag_name'];
				echo " ";
			}
			  $parameter = "'".$temp."'";
			  $function = "change(".$parameter.")";
			  echo "</div>";
			  echo "<div>";
			  echo "Status : ";
			  if ($row['status']=='1'){
				  echo "<input id='checkbox_".$temp."' value = '".$temp."' type='checkbox' checked='checked' onchange=$function>";
				  echo "sudah selesai";
			  }else{
				  echo "<input id='checkbox_".$temp."' value = '".$temp."' type='checkbox' onchange=$function>";
				  echo "belum selesai";
			  }
		  echo "</div>";
		}
					
		
		mysqli_close($con);
	?>
    
</body>
</html>