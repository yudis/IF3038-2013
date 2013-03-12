<?php 
	function getConnection(){
		// Create connection
		$con=mysqli_connect("localhost","root","","progin_405_13511601");
		// Check connection
		if (mysqli_connect_errno($con))
		{
	  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}
		return $con;
	}
	
	function upload_file($file,$username){
		if ($file["error"] > 0)
	  {
		  echo "Error: " . $file["error"] . "<br>";
	  }
	else
	  {
		  $extension = end(explode(".", $file["name"]));
		  move_uploaded_file ($file['tmp_name'],"../avatar/$username.$extension");
	  }
	}
	
	function getNextTaskId(){
		$con = getConnection();
		$query = "SELECT max(taskid) as max FROM task"	;
		$result = mysqli_query($con,$query);
		$row = mysqli_fetch_array($result);
		return $row['max'] + 1;
	}
	
	function getNextCategoryId(){
		$con = getConnection();
		$query = "SELECT max(categoryid) as max FROM category"	;
		$result = mysqli_query($con,$query);
		$row = mysqli_fetch_array($result);
		return $row['max'] + 1;
	}
	
	function getTagId($tag){
		$con = getConnection();
		
		if (isTagExist($tag)){
			$query = "SELECT tagid FROM tag WHERE tagname = '$tag'";
			$result = mysqli_query($con,$query);
			$row = mysqli_fetch_array($result);
			return $row['tagid'];
		}else{ 
			$query = "INSERT INTO tag values ('','$tag')";
			mysqli_query($con,$query);
			return getTagId($tag);
		}
	}
	
	function getTagname($tagid){
		$con = getConnection();
		$query = "SELECT tagname FROM tag WHERE tagid = '$tagid'";
		$result = mysqli_query($con,$query);
		$row = mysqli_fetch_array($result);
		return $row['tagname'];
	}
	
	function getTaskId($taskname,$categoryid){
		$con = getConnection();
		$query = "SELECT taskid FROM task WHERE taskname = '$taskname' AND categoryid='$categoryid'";
		$result = mysqli_query($con,$query);
		$row = mysqli_fetch_array($result);
		return $row['taskid'];
	}
	
	function isTagExist($tag){
		$con = getConnection();
		$query = "SELECT count(*) as count FROM tag WHERE tagname = '$tag'";
		$result = mysqli_query($con,$query);
		$row = mysqli_fetch_array($result);
		if (strcmp($row['count'],"0") == 0)
			return false;
		else 
			return true;
	}
?>