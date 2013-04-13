<!DOCTYPE html>
<%--<?php
			include_once("../php/loginchecker.php");
		?>
<?php include '../php/fungsiget.php'?>
	<?php
      $kategori = get_allkategoriphp();
      print_r($kategori);
    //$all_task = get_alltaskphp();
    ?>--%>
<html>
	<!--
	<IFRAME name="iframe" src="src/header.html" width='100%' height='auto' marginwidth=0 marginheight=0 hspace=0 vspace=0 frameborder=0 scrolling=auto></IFRAME>
	-->
	
	<head>
		<link href='../css/desktop_style.css' rel='stylesheet' type='text/css'>
		<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
		<script type="text/javascript" src="../js/animation.js"> </script>
		<script type="text/javascript" src="../js/catselector.js"> </script> 	
                <script type="text/javascript" src="../js/add_task.js"></script>
                <script type="text/javascript" src="../js/fungsiget.js">
                    
                  
                </script>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
		<title> Eurilys </title>
	</head>

	<body>
		<!-- Web Header -->
		<%@include file="../src/header.jsp"%>
		<% String kategori="PROGIN";%>
		
		<!-- Web Content -->
		<section>
			<div id="navbar">
				<div id="short_profile">
					<img id="profile_picture" src="../img/avatar1.png" alt="">
					<div id="profile_info">
						Ruth Natasha 
						<br><br>
						<div class="link_tosca" id="edit_profile_button"> Edit Profile </div>
					</div>
				</div>
				
			</div>
			<div id="dynamic_content">
                            <form >
				<div id="add_task_container">
					<div id="add_task_header" class="left top30 dynamic_content_head">
						Add New Task
					</div>
			
					<div id="row1_addtask" class="left top30 dynamic_content_row">
						<div id="task_name_lat" class="left dynamic_content_left">Task Name</div>
						<div id="task_name_rat" class="left dynamic_content_right">
							<input id="task_name_input" onkeydown="javascript:checkTaskName();" type="text" name="task_name_input" class="left">
							<img src="../img/yes.png" id="taskname_validation" class="left signup_form_validation" alt="validation image"/>
						</div>
					</div>
					
					<div id="row2_addtask" class="left top10 dynamic_content_row">
						<div id="attachment_lat" class="left dynamic_content_left">Attachment</div>
						<div id="attachment_rat" class="left dynamic_content_right">
							<input id="attachment_upload" type="file" onchange="javascript:checkTaskAttachment();" name="attachment_file" class="left">
							<img src="../img/yes.png" id="task_attachment_validation" class="left signup_form_validation" alt="validation image"/>
						</div>
					</div>
					
					<div id="row3_addtask" class="left top10 dynamic_content_row">
						<div id="deadline_lat" class="left dynamic_content_left">Deadline</div>
						<div id="deadline_rat" class="left dynamic_content_right">
							<input id="deadline_input" type="date" name="deadline_input">
						</div>
					</div>
					
					<div id="row4_addtask" class="left top10 dynamic_content_row">
						<div id="assignee_lat" class="left dynamic_content_left">Assignee</div>
						<div id="assignee_rat" class="left dynamic_content_right">
							<input id="assignee_input" type="text" name="assignee_input" autocomplete="on">
						</div>
					</div>
				
					<div id="row5_addtask" class="left top10 dynamic_content_row">
						<div id="tag_lat" class="left dynamic_content_left">Tag</div>
						<div id="tag_rat" class="left dynamic_content_right">
							<input id="tag_input" type="text" name="tag_input" >
						</div>
					</div>
					
					<div id="row6_addtask" class="left top10 dynamic_content_row">
						<input id="add_task_button" type="button" onclick="add_task('<%out.print(kategori);%>')" value="Add Task" class="link_blue_rect">
					</div>
				</div>
                            </form>
			</div>
		</section>
		
		<!-- Footer Section -->
		<div class="thin_line"></div>
		<footer>
			<div id="footer_container"> 
				<br><br>
				About &nbsp;&nbsp;&nbsp; FAQ &nbsp;&nbsp;&nbsp; Feedback &nbsp;&nbsp;&nbsp; Terms &nbsp;&nbsp;&nbsp; Privay &nbsp;&nbsp;&nbsp; Copyright 
				<br>
				Eurilys 2013
			</div>
		</footer>
	</body>

<!-- ini nanti jadiin footer -->
</html>
