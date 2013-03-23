<?php 
	session_start();
	ob_start();
	
	/* Configuring Server & Database */
	$host        =    'localhost';
	$user        =    'root';
	$password    =    '';
	$database    =    'progin_405_13510086';

	$con        =    mysql_connect($host,$user,$password) or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');
	
function upload($file_id, $folder="", $types="") {
    if(!$_FILES[$file_id]['name']) return array('','No file specified');

    $file_title = $_FILES[$file_id]['name'];
	
	//Get file extension
	$ext_arr = explode("\.",basename($file_title));
	$ext = pathinfo($_FILES[$file_id]['name'], PATHINFO_EXTENSION);
	
    //Not really uniqe - but for all practical reasons, it is
    $uniqer = substr(md5(uniqid(rand(),1)),0,5);
    $file_name = $uniqer . '_' . $file_title;//Get Unique Name

    $all_types = explode(",",strtolower($types));
    if($types) {
        if(in_array($ext,$all_types));
        else {
            $result = "'".$_FILES[$file_id]['name']."' is not a valid file."; //Show error if any.
            return array('',$result);
        }
    }

    //Where the file must be uploaded to
    if($folder) $folder .= '/';//Add a '/' at the end of the folder
    $uploadfile = $folder . $file_name;

    $result = '';
    //Move the file from the stored location to the new location
    if (!move_uploaded_file($_FILES[$file_id]['tmp_name'], $uploadfile)) {
        $result = "Cannot upload the file '".$_FILES[$file_id]['name']."'"; //Show error if any.
        if(!file_exists($folder)) {
            $result .= " : Folder don't exist.";
        } elseif(!is_writable($folder)) {
            $result .= " : Folder not writable.";
        } elseif(!is_writable($uploadfile)) {
            $result .= " : File not writable.";
        }
        $file_name = '';
        
    } else {
        if(!$_FILES[$file_id]['size']) { //Check if the file is made
            @unlink($uploadfile);//Delete the Empty file
            $file_name = '';
            $result = "Empty file found - please use a valid file."; //Show the error message
			echo "hoho";
        } else {
            chmod($uploadfile,0777);//Make it universally writable.
        }
    }

    return array($file_name,$result);
}
	
	
	
	/* Add Task Script */
	$task_name		= mysql_real_escape_string($_POST['task_name_input']);
	$taskAsigneeName   	= mysql_real_escape_string($_POST['assignee_input']);
	$attachment1 = $_FILES['attachment_file1']['name'];
	$attachment2 = $_FILES['attachment_file2']['name'];
	$attachment3 = $_FILES['attachment_file3']['name'];
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
			//echo "Task id = ".$taskID;
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
		
		
		
		
if($_FILES['attachment_file1']['name']) {
	list($file,$error) = upload('attachment_file1','uploads',',jpg,jpeg,gif,png,flv,mp4,pdf,rar,zip');
	if($error) print $error;
	$query4 	= "INSERT INTO `attachment` (`att_content`, `att_task_id`) VALUES ('$file','$taskID')";
	$result4 = mysql_query($query4);
}
if($_FILES['attachment_file2']['name']) {
	list($file,$error) = upload('attachment_file2','uploads',',jpg,jpeg,gif,png,flv,mp4,pdf,rar,zip');
	if($error) print $error;
	$query5 	= "INSERT INTO `attachment` (`att_content`, `att_task_id`) VALUES ('$file','$taskID')";
	$result5 = mysql_query($query5);
}
if($_FILES['attachment_file3']['name']) {
	list($file,$error) = upload('attachment_file3','uploads',',jpg,jpeg,gif,png,flv,mp4,pdf,rar,zip');
	if($error) print $error;
	$query6 	= "INSERT INTO `attachment` (`att_content`, `att_task_id`) VALUES ('$file','$taskID')";
	$result6 = mysql_query($query6);
}


		header('location:dashboard.php'); //Redirect To Success Page
	}
?>