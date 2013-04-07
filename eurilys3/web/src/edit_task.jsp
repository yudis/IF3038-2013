<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="logged_in_header.jsp"%>	
<section>
	<!-- Navigation Bar -->
	<%@include file="navigation_bar.jsp"%>
	
	<div id="dynamic_content">
		<div class='taskDetail'>
			<div id='edit_task_header' class='left top30 dynamic_content_head darkBlue'>
				TASK NAME
			</div>
				
			<form method="POST" action="">
				<input type='submit' id="save_edit_task" name='edit_task_submit' class='left top30 link_blue_rect' value='Save'>
				
				<input type="hidden" name="edit_task_id" value=""/>
				<div class='left top30 dynamic_content_row'>
					<div id='task_name_ltd' class='left dynamic_content_left'> Task Name </div>
					<div id='task_name_rtd' class='left dynamic_content_right'> TASK NAME </div>
				</div>
				<div class='left top20 dynamic_content_row'>
					<div id='task_status_ltd' class='left dynamic_content_left'> Status </div>
					<div id='task_status_rtd' class='left dynamic_content_right'> DONE / NOT DONE </div>
				</div>
				<div class='left top20 dynamic_content_row'>
					<div id='attachment_ltd' class='left dynamic_content_left'>Attachment</div>
					<div id='attachment_rtd' class='left dynamic_content_right'>
						ATTACHMENT....
					</div>
				</div>
				
				
				<div class='left top20 dynamic_content_row'>
					<div id='deadline_ltd' class='left dynamic_content_left'>Deadline</div>
					<div id='deadline_rtd' class='left dynamic_content_right'> 
						<input class="edit_task_input" id="edit_task_deadline" name="edit_task_deadline" type="date" name="deadline_td" value=""/>
					</div>
				</div>
				
				<div class='left top20 dynamic_content_row'>
					<div id='assignee_ltd' class='left dynamic_content_left'>Assignee</div>
					<div id='assignee_rtd' class='left dynamic_content_right'>
                                            <img src='../img/done.png' class='cursorPointer' width='8' onclick=''/> &nbsp;&nbsp;&nbsp;
                                            <span class='userprofile_link darkBlueItalic' onclick=''> ASS-NAME 1 </span> 
                                            <br>
                                            <img src='../img/done.png' class='cursorPointer' width='8' onclick=''/> &nbsp;&nbsp;&nbsp;
                                            <span class='userprofile_link darkBlueItalic' onclick=''> ASS-NAME 2 </span> 
                                            <br>
                                            <img src='../img/done.png' class='cursorPointer' width='8' onclick=''/> &nbsp;&nbsp;&nbsp;
                                            <span class='userprofile_link darkBlueItalic' onclick=''> ASS-NAME 3 </span> 
                                            <br>
					<br>
					<input class="edit_task_input" id="edit_task_assignee" name="edit_task_assignee" type="text" name="assignee_td" value=""/>
					</div>
				</div>			
				<div class='left top20 dynamic_content_row'>
					<div id='tag_ltd' class='left dynamic_content_left'> Tag </div>
					<div id='tag_rtd' class='left dynamic_content_right'>
						<input class="edit_task_input" id="edit_task_tag" name="edit_task_tag" type="text" name="tag_td" value=""/> 
					</div>
				</div>
			</form>
		</div>				
	</div>
</section>
<%@include file="footer.jsp"%>