<?php
	session_start();
	ob_start();
	if (isset($_SESSION['username'])) {
		$username = $_SESSION['username']; 
	}
	
	/* Configuring Server & Database */
	$host        =    'localhost';
	$user        =    'root';
	$password    =    '';
	$database    =    'progin_405_13510086';
	$con        =    mysql_connect($host,$user,$password) or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');
	
	$response = "";
	
	/* Get the task id we're going to generate to the HTML page */
	$q	= $_GET["q"];
	$deleteComment = $_GET["delComment"];
	
	if ($deleteComment != "") {
		//delete comment
		$query = "DELETE FROM `comment` WHERE comment_id='$deleteComment'";
		mysql_query($query);
	}
	
	/* Searching for Task */
	$query 	= "SELECT * FROM task WHERE task_id='$q';";
	$result	= mysql_query($query);
	while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {		
		$taskID = $row['task_id'];

		//Get 'tag'
		$tag_query = "SELECT * from tag WHERE task_id='$taskID'";
		$tag_result = mysql_query($tag_query);
		$tagResponse = "";
		while ($tag_row = mysql_fetch_array($tag_result, MYSQL_ASSOC)) { 
			$tagResponse = $tagResponse.$tag_row['tag_name']." , ";
		}
		
		//Get 'attachment'
		$attachment_query = "SELECT * from attachment WHERE att_task_id='$taskID'";
		$attachment_result = mysql_query($attachment_query);
		unset($attachmentResponse);
		$attachmentResponse = array();
		while ($attachment_row = mysql_fetch_array($attachment_result, MYSQL_ASSOC)) { 
			$attachmentResponse[] = $attachment_row['att_content'];
		}
		
		//Get 'comment'		
		unset($commentContent);
		$commentContent = array();
		unset($commentID);
		$commentID = array();
		unset($commentCreator);
		$commentCreator = array();
		unset($commentTime);
		$commentTime = array();
		
		$comment_query = "SELECT comment_id, comment_content, comment_creator, comment_timestamp from comment WHERE task_id='$taskID'";
		$comment_result = mysql_query($comment_query);
		while ($comment_row = mysql_fetch_array($comment_result, MYSQL_ASSOC)) {
			$commentID[] = $comment_row['comment_id'];
			$commentContent[] = $comment_row['comment_content'];
			$commentCreator[] = $comment_row['comment_creator'];
			$commentTime[]    = $comment_row['comment_timestamp'];
		}
		
		//Get 'task assignee'
		$ass_query = "SELECT username from task_asignee WHERE task_id='$taskID'";
		$ass_result = mysql_query($ass_query);
		unset($assResponse);
		$assResponse = array();
		while ($ass_row = mysql_fetch_array($ass_result, MYSQL_ASSOC)) { 
			$assResponse[] = $ass_row['username'];
		}
		
		
		//Generate response
		$response = $response. 
		"
		<div class='taskDetail'>
			<div id='edit_task_header' class='left top30 dynamic_content_head darkBlue'>
				".$row['task_name']."
			</div>
			
			<input id='edit_task_button' class='left top30 link_blue_rect' onclick='edit_task(\"$taskID\")' type='button' value='Edit Task'>
									
			<div class='left top30 dynamic_content_row'>
				<div id='task_name_ltd' class='left dynamic_content_left'> Task Name </div>
				<div id='task_name_rtd' class='left dynamic_content_right'>".$row['task_name']."</div>
			</div>
			
			<div class='left top20 dynamic_content_row'>
				<div id='task_status_ltd' class='left dynamic_content_left'> Status </div>";
			
			if ($row['task_status'] == 0) {
				$status = 'Not finished yet';
			}
			else	$status = 'Finished';
			
			$response = $response. 
			"
				<div id='task_status_rtd' class='left dynamic_content_right'>".$status."</div>
			</div>
			
			<div class='left top20 dynamic_content_row'>
				<div id='attachment_ltd' class='left dynamic_content_left'>Attachment</div>
				<div id='attachment_rtd' class='left dynamic_content_right'>";
			
			if (count($attachmentResponse) > 0) {
				for($i=0; $i<count($attachmentResponse); $i++) {
					$extensyen = explode(".", $attachmentResponse[$i]);
					$extension = end($extensyen);
					if (($extension=='zip') || ($extension == 'pdf') || ($extension == 'rar')){
					$response = $response."
					<a id='taskfile'  href='uploads/".$attachmentResponse[$i]."' target='_blank' >".$attachmentResponse[$i]."</a><br>";
					} else if (($extension == 'jpg') || ($extension == 'jpeg') || ($extension == 'gif') || (($extension == 'png'))) {
					$response = $response."
					<img id='taskimg' width='200px' height='200px' src='uploads/".$attachmentResponse[$i]."'><br>";
					} else if (($extension == 'mp4') || ($extension == 'flv')){
					$response = $response."
					<video id='taskvid' width='320px' height='240px' controls>
					<source src='uploads/".$attachmentResponse[$i]."' type='video/flv'></video><br>";
					} else {
					$response = $response."
					<a id='taskfile'  href='uploads/".$attachmentResponse[$i]."' target='_blank' >".$attachmentResponse[$i]."</a><br>";
					}
				}
			}
			
			$response = $response."
				
			</div>
			
			<div class='left top20 dynamic_content_row'>
				<div id='deadline_ltd' class='left dynamic_content_left'>Deadline</div>
				<div id='deadline_rtd' class='left dynamic_content_right'>".$row['task_deadline']."</div>
			</div>
			
			<div class='left top20 dynamic_content_row'>
				<div id='assignee_ltd' class='left dynamic_content_left'>Assignee</div>
				<div id='assignee_rtd' class='left dynamic_content_right'>";
			
			$assName = "";
			if (count($assResponse) > 0) {
				for($i=0; $i<count($assResponse); $i++) {
					$assName = $assName.$assResponse[$i]." , ";
					$response = $response.
					"<span class='userprofile_link darkBlueItalic' onclick='javascript:searchUser(\"$assResponse[$i]\")'> $assResponse[$i] </span> , ";
				}
			}	
				
			$response = $response."</div>
			</div>
			<input type='hidden' id='hidden_ass_name' value='$assName'/>
			<div class='left top20 dynamic_content_row'>
				<div id='tag_ltd' class='left dynamic_content_left'>Tag</div>
				<div id='tag_rtd' class='left dynamic_content_right'>".$tagResponse."</div>
			</div>
			<div class='left top45 dynamic_content_row'>
					<div id='comment_ltd' class='left dynamic_content_left'> Comment </div>
					<div id='comment_rtd' class='left dynamic_content_right'> </div>
			</div>
		";
		
		if (count($commentContent) > 0) {
			for($i=0; $i<count($commentContent); $i++) {
				$date = strtotime( $commentTime[$i] );
				$date= date('H:m d/m', $date );
				
				$avatar_query	= "SELECT avatar FROM user WHERE username='$commentCreator[$i]'";
				$avatar_result	=  mysql_query($avatar_query) or die(mysql_error());

				$row = mysql_fetch_array($avatar_result, MYSQL_ASSOC);
				$avatar = $row['avatar'];
				
				$response = $response.
				"
				<div class='left top20 dynamic_content_row'>
					<div id='comment_ltd' class='left dynamic_content_left darkBlueItalic userprofile_link' onclick='javascript:searchUser(\"$commentCreator[$i]\")'> 
					<img src='$avatar' width='55'/> <br>
					".$commentCreator[$i].
					"<br>".$date."
					</div>
					<div id='comment_rtd' class='left dynamic_content_right'>".$commentContent[$i]."</div>";
				if ($commentCreator[$i] == $username) {
					$response = $response."<img src='../img/done.png' onclick='javascript:deleteComment(\"$q\",\"$commentID[$i]\")' class='cursorPointer' alt=''>";
				}
				$response = $response."</div>";
			}
		}	
		$response = $response.
		"	<div class='left top20 dynamic_content_row'>
				<div id='addcomment_ltd' class='left dynamic_content_left'> &nbsp; </div>
				<div id='addcomment_rtd' class='left dynamic_content_right'>
					<form autocomplete='off' method='POST' action='add_comment.php'>
						<textarea id='comment_textarea' rows='5' cols='50' name='CommentBox'>
						</textarea> 
						<br>
						<input type='hidden' id='hidden_task_id' name='comment_task_id' value='".$taskID."'>
						<input type='submit' value='Add Comment' name='add_comment_button' class='link_red'>
						<br><br><br>
					</form>
				</div>
			</div> 
		</div>";
	}
	//output the response
	echo $response;
?>