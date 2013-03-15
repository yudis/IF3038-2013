<?php include 'logged_in_header.php'; ?>	
		
<!-- Web Content -->
<section>
	<?php include 'navigation_bar.php'; ?>
	<div id="dynamic_content">
		<br><br>
		<div class="task_view" id="curtask5">
			<img src="../img/done.png" id="finish_5" onclick="javascript:finishTask(5)" class="task_done_button" alt=""/>
			<div id="task_name_ltd_5" class="left dynamic_content_left">Task Name</div>
			<div id="task_name_rtd_5" class="left dynamic_content_right"> <a href="taskdetail_file.php"> Database Sekolah </a> </div>
			<br><br>
			<div id="deadline_ltd_5" class="left dynamic_content_left">Deadline</div>
			<div id="deadline_rtd_5" class="left dynamic_content_right">21/2/2012</div>
			<br><br>
			<div id="tag_ltd_5" class="left dynamic_content_left">Tag</div>
			<div id="tag_rtd_5" class="left dynamic_content_right">HTML 5, CSS 3</div>
			<br>
			<div class="task_view_category"> Proyek </div>
			<br>
		</div>
						
	</div>
</section>
		
<?php include '../footer.php'; ?>