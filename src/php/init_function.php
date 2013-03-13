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
	
	function upload_avatar($file,$username){
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
	
	function upload_attachment($file,$username){
		if ($file["error"] > 0)
	  {
		  echo "Error: " . $file["error"] . "<br>";
	  }
	else
	  {
		  $filename = $file["name"];
		  move_uploaded_file ($file['tmp_name'],"../attachment/$username-$filename");
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
	
	function getCategoryName($categoryid){
		$con = getConnection();
		$query = "SELECT categoryname FROM category WHERE categoryid='$categoryid'";
		$result = mysqli_query($con,$query);
		$row = mysqli_fetch_array($result);
		return $row['categoryname'];
	}
	
	function isResponsibility($categoryid,$useractive){
		$con = getConnection();
		$query = "SELECT count(*) as responsibility FROM responsibility WHERE categoryid='$categoryid' and username='$useractive'";
		$result = mysqli_query($con,$query);
		$row = mysqli_fetch_array($result);
		if (strcmp($row['responsibility'],"0") == 0)
			return false;
		else 
			return true;
	}
	
	function getUser($useractive){
		$con = getConnection();
		$query = "SELECT * FROM user WHERE username='$useractive'";
		$result = mysqli_query($con,$query);
		$row = mysqli_fetch_array($result);
		return $row;
	}
	
	function getTask($taskid){
		$con = getConnection();
		$query = "SELECT * FROM task WHERE taskid='$taskid'";
		$result = mysqli_query($con,$query);
		$row = mysqli_fetch_array($result);
		return $row;
	}
	
	function typeFile($filename){
		$explode = explode(".", $filename);
		$extension = end($explode);
		$extension = strtolower($extension);
		if(strcmp($extension,"jpg") == 0 || strcmp($extension,"png") == 0){
			return 0;
		}else if(strcmp($extension,"mp4") == 0){
			return 1;
		}else{
			return 2;	
		}
	}
	
	function getNComment($taskid){
		$con = getConnection();
		$query = "SELECT count(*) as total FROM comment WHERE taskid='$taskid'";
		$result = mysqli_query($con,$query);
		$row = mysqli_fetch_array($result);
		return $row['total']." Comment";
	}
	
	function getAvatar($username){
		$con = getConnection();
		$query = "SELECT avatar FROM user WHERE username='$username'";
		$result = mysqli_query($con,$query);
		$row = mysqli_fetch_array($result);
		return $row['avatar'];
	}
?>