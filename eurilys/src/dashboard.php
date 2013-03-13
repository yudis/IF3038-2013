<?php include 'logged_in_header.php'; ?>	
		
<!-- Web Content -->
<section>
	<?php include 'navigation_bar.php'; ?>
	<div id="dynamic_content">
		<br><br>
		<br><br>
		<div class="task_view" id="curtask1">
			<img src="../img/done.png" id="finish_1" onclick="javascript:finishTask(1)" class="task_done_button" alt=""/>
			<div id="task_name_ltd" class="left dynamic_content_left">Task Name</div>
			<div id="task_name_rtd" class="left dynamic_content_right"> <a href="taskdetail_img.php"> Catatan Progin </a> </div>
			<br><br>
			<div id="deadline_ltd" class="left dynamic_content_left">Deadline</div>
			<div id="deadline_rtd" class="left dynamic_content_right">21/2/2012</div>
			<br><br>
			<div id="tag_ltd" class="left dynamic_content_left">Tag</div>
			<div id="tag_rtd" class="left dynamic_content_right"> notes, catatan</div>
			<br>
			<div class="task_view_category"> Kuliah </div>
			<br>
		</div>
		
		<div class="task_view" id="curtask2">
			<img src="../img/done.png" id="finish_2" onclick="javascript:finishTask(2)" class="task_done_button" alt=""/>
			<div id="task_name_ltd_2" class="left dynamic_content_left">Task Name</div>
			<div id="task_name_rtd_2" class="left dynamic_content_right"> <a href="taskdetail_file.php"> Tubes Progin I </a> </div>
			<br><br>
			<div id="deadline_ltd_2" class="left dynamic_content_left">Deadline</div>
			<div id="deadline_rtd_2" class="left dynamic_content_right">21/2/2012</div>
			<br><br>
			<div id="tag_ltd_2" class="left dynamic_content_left">Tag</div>
			<div id="tag_rtd_2" class="left dynamic_content_right">HTML 5, CSS 3</div>
			<br>
			<div class="task_view_category"> Kuliah </div>
			<br>
		</div>
		<br><br>
		<div class="task_view" id="curtask3">
			<img src="../img/done.png" id="finish_3" onclick="javascript:finishTask(3)" class="task_done_button" alt=""/>
			<div id="task_name_ltd_3" class="left dynamic_content_left">Task Name</div>
			<div id="task_name_rtd_3" class="left dynamic_content_right"> <a href="taskdetail_file.php"> Imagine Cup </a> </div>
			<br><br>
			<div id="deadline_ltd_3" class="left dynamic_content_left">Deadline</div>
			<div id="deadline_rtd_3" class="left dynamic_content_right">21/2/2012</div>
			<br><br>
			<div id="tag_ltd_3" class="left dynamic_content_left">Tag</div>
			<div id="tag_rtd_3" class="left dynamic_content_right">HTML 5, CSS 3</div>
			<br>
			<div class="task_view_category"> Lomba </div>
			<br>
		</div>
		<br><br>
		<div class="task_view" id="curtask4">
			<img src="../img/done.png" id="finish_4" onclick="javascript:finishTask(4)" class="task_done_button" alt=""/>
			<div id="task_name_ltd_4" class="left dynamic_content_left">Task Name</div>
			<div id="task_name_rtd_4" class="left dynamic_content_right"> <a href="taskdetail_file.php"> Proyek BNI </a> </div>
			<br><br>
			<div id="deadline_ltd_4" class="left dynamic_content_left">Deadline</div>
			<div id="deadline_rtd_4" class="left dynamic_content_right">21/2/2012</div>
			<br><br>
			<div id="tag_ltd_4" class="left dynamic_content_left">Tag</div>
			<div id="tag_rtd_4" class="left dynamic_content_right">HTML 5, CSS 3</div>
			<br>
			<div class="task_view_category"> Proyek </div>
			<br>
		</div>
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
		<br><br><br><br>				
	</div>
</section>
		
<?php include '../footer.php'; ?>