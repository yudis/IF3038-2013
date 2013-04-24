<?php include 'logged_in_header.php'; ?>	
<section>
	<!-- Navigation Bar -->
	<?php include 'navigation_bar.php'; ?>
	
	<div id="dynamic_content">
		<div class='taskDetail'>
			<?php 
				$taskID = $_GET['task_id'];
				
				/* Searching for Task */
				$query 	= "SELECT * FROM task WHERE task_id='$taskID';";
				$result	= mysql_query($query);
				while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
					$taskname = $row['task_name'];
					$taskstatus = $row['task_status'];
					if ($taskstatus == 0) {
						$status = 'Not finished yet';
					}
					else	$status = 'Finished';
					$taskdeadline = $row['task_deadline'];
				}
				
				//Get 'task assignee'
				$ass_query = "SELECT username from task_asignee WHERE task_id='$taskID'";
				$ass_result = mysql_query($ass_query);
				unset($assResponse);
				$assResponse = array();
				while ($ass_row = mysql_fetch_array($ass_result, MYSQL_ASSOC)) { 
					$assResponse[] = $ass_row['username'];
				}
				
				//Get 'tag'
				$tag_query = "SELECT * from tag WHERE task_id='$taskID'";
				$tag_result = mysql_query($tag_query);
				$tagResponse = "";
				while ($tag_row = mysql_fetch_array($tag_result, MYSQL_ASSOC)) { 
					$tagResponse = $tagResponse.$tag_row['tag_name']." , ";
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
			?>
			
			
			<div id='edit_task_header' class='left top30 dynamic_content_head darkBlue'>
				<?php echo $taskname; ?>
			</div>
				
			<form method="POST" action="edit_task_script.php">
				<input type='submit' id="save_edit_task" name='edit_task_submit' class='left top30 link_blue_rect' value='Save'>
				
				<input type="hidden" name="edit_task_id" value="<?php echo $taskID;?>"/>
				<div class='left top30 dynamic_content_row'>
					<div id='task_name_ltd' class='left dynamic_content_left'> Task Name </div>
					<div id='task_name_rtd' class='left dynamic_content_right'> <?php echo $taskname; ?> </div>
				</div>
				<div class='left top20 dynamic_content_row'>
					<div id='task_status_ltd' class='left dynamic_content_left'> Status </div>
					<div id='task_status_rtd' class='left dynamic_content_right'> <?php echo $status; ?> </div>
				</div>
				<div class='left top20 dynamic_content_row'>
					<div id='attachment_ltd' class='left dynamic_content_left'>Attachment</div>
					<div id='attachment_rtd' class='left dynamic_content_right'>
						??? Belum ada attachment
					</div>
				</div>
				
				
				<div class='left top20 dynamic_content_row'>
					<div id='deadline_ltd' class='left dynamic_content_left'>Deadline</div>
					<div id='deadline_rtd' class='left dynamic_content_right'> 
						<input class="edit_task_input" id="edit_task_deadline" name="edit_task_deadline" type="date" name="deadline_td" value="<?php echo $taskdeadline; ?>"/>
					</div>
				</div>
				
				<div class='left top20 dynamic_content_row'>
					<div id='assignee_ltd' class='left dynamic_content_left'>Assignee</div>
					<div id='assignee_rtd' class='left dynamic_content_right'>
					<?php 
						if (count($assResponse) > 0) {
							for($i=0; $i<count($assResponse); $i++) {
								$assResponse[$i];
								echo "
								<img src='../img/done.png' class='cursorPointer' width='8' onclick='javascript:edittaskDeleteAss(\"$taskID\", \"$assResponse[$i]\");'/> &nbsp;&nbsp;&nbsp;
								<span class='userprofile_link darkBlueItalic' onclick='javascript:searchUser(\"$assResponse[$i]\")'> $assResponse[$i] </span> 
								<br>";
							}
						}
					?>
					<br>
					<input class="edit_task_input" id="edit_task_assignee" onkeyup="showAssigne1(this.value)" name="edit_task_assignee" type="text" name="assignee_td" value=""/>
					<div id="taskAssId"></div>
					</div>
				</div>			
				<div class='left top20 dynamic_content_row'>
					<div id='tag_ltd' class='left dynamic_content_left'> Tag </div>
					<div id='tag_rtd' class='left dynamic_content_right'>
						<input class="edit_task_input" id="edit_task_tag" name="edit_task_tag" type="text" name="tag_td" value="<?php echo $tagResponse; ?>"/> 
					</div>
				</div>
			</form>
		</div>				
	</div>
</section>
		
<?php include '../footer.php'; ?>