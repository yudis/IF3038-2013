<?php 
	session_start();
	session_destroy();
	ob_start();
	
	/* Configuring Server & Database */
	$host        =    'localhost';
	$user        =    'root';
	$password    =    '';
	$database    =    'progin_405_13510086';

	$con        =    mysql_connect($host,$user,$password) or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');
	
	
	
$allowedExts = array("gif", "jpeg", "jpg", "png");
$extension = end(explode(".", $_FILES["attachment_file"]["name"]));
if ((($_FILES["attachment_file"]["type"] == "image/gif")
|| ($_FILES["attachment_file"]["type"] == "image/jpeg")
|| ($_FILES["attachment_file"]["type"] == "image/jpg")
|| ($_FILES["attachment_file"]["type"] == "image/png"))
&& ($_FILES["attachment_file"]["size"] < 20000)
&& in_array($extension, $allowedExts))
  {
  if ($_FILES["attachment_file"]["error"] > 0)
    {
    echo "Return Code: " . $_FILES["attachment_file"]["error"] . "<br>";
    }
  else
    {
    echo "Upload: " . $_FILES["attachment_file"]["name"] . "<br>";
    echo "Type: " . $_FILES["attachment_file"]["type"] . "<br>";
    echo "Size: " . ($_FILES["attachment_file"]["size"] / 1024) . " kB<br>";
    echo "Temp file: " . $_FILES["attachment_file"]["tmp_name"] . "<br>";

    if (file_exists("uploads/" . $_FILES["attachment_file"]["name"]))
      {
      echo $_FILES["attachment_file"]["name"] . " already exists. ";
      }
    else
      {
      move_uploaded_file($_FILES["attachment_file"]["tmp_name"],
      "uploads/" . $_FILES["attachment_file"]["name"]);
      echo "Stored in: " . "uploads/" . $_FILES["attachment_file"]["name"];
      }
    }
  }
else
  {
  echo "Invalid file";
  }

	
	/* Add Task Script */
	$task_name		= mysql_real_escape_string($_POST['task_name_input']);
	$taskAsigneeName   	= mysql_real_escape_string($_POST['assignee_input']);
	$attachment = $_FILES['attachment_file']['name'];
	$task_deadline  = $_POST['deadline_input'];
		if (isset($_SESSION['username'])) {
		$username = $_SESSION['username']; 
	}
	$tag    		= mysql_real_escape_string($_POST['tag_input']);
	$cat_name   	= ($_POST['cat_name']);
	if (isset($_POST['add_task_button'])) { 
		$query	=    
		"INSERT INTO task (`task_name`, `task_deadline`,`task_creator`,`cat_name`) 
		VALUES ('$task_name','$task_deadline','$username','$cat_name' )";
		$res	=    mysql_query($query);
		
		$query1 	= "SELECT task_id FROM task WHERE 		task_name='$task_name'";
		$result1	= mysql_query($query1);
		while ($row = mysql_fetch_array($result1, MYSQL_ASSOC)) {
			$taskID = $row["task_id"];
			echo "Task id = ".$taskID;
		}
		
		$assigneeArray = explode(',', $taskAsigneeName); 		
		for ($i=0; $i<count($assigneeArray); $i++) {
			//echo $assigneeArray[$i];
			$query2 	= "INSERT INTO `task_asignee` (`task_id`, `username`) VALUES ('$taskID','$assigneeArray[$i]')";
			$result2	= mysql_query($query2);
		}
		
		$tagArray = explode(',', $tag); 		
		for ($i=0; $i<count($tagArray); $i++) {
			$query3 	= "INSERT INTO `tag` (`tag_name`, `task_id`) VALUES ('$tagArray[$i]','$taskID')";
			$result3 = mysql_query($query3);
		}
		
	
			$query4 	= "INSERT INTO `attachment` (`att_content`, `att_task_id`) VALUES ('$attachment','$taskID')";
			$result4 = mysql_query($query4);
			
			


		
		header('location:addtask.php'); //Redirect To Success Page
	}
?>