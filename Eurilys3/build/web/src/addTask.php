<!DOCTYPE html>
<?php
	if(!isset($_COOKIE['name'])){
		header("Location: ../index.php");
	}
?>
<?php include 'connect.php'?>
<?php
	$result = mysql_query("SELECT * FROM user WHERE username ='".$_COOKIE['name']."'");
	$user = mysql_fetch_array($result);
?>
<html>
	<head>
		<link href='../css/desktop_style.css' rel='stylesheet' type='text/css'>
		<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
		<script type="text/javascript" src="../js/edit_task.js"> </script> 
		<script type="text/javascript" src="../js/animation.js"> </script> 
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >		
		<title> Eurilys </title>
	</head>
	
	<body>
		<!-- Web Header -->
		<header>
			<div id="header_container"> 
				<div id="logo_container">
					<a href="dashboard.php"> <img src="../img/logo.png" alt=""> </a>
				</div>
				<input id="search_box" type="text" placeholder="Type here to search">
				<div id="header_menu"> 
					<div class="header_menu_button current_header_menu"> <a href="dashboard.php"> DASHBOARD </a>   </div>
					<div class="header_menu_button">  <a href="profile.php"> <?php echo strtoupper($user['username']) ?> </a> </div>
					<div class="header_menu_button"> <a id="logout" href="../clear.php"> LOGOUT </a> </div>
				</div>
				
			</div>
		</header>	
	
		
		<!-- Web Content -->
		<section>
			<div id="navbar">
				<div id="short_profile">
					<img id="profile_picture" src="../img/avatar1.png" alt="">
					<div id="profile_info">
						Welcome,
						<br>
						<a href="profile.php"><?php echo $user['name']?></a>
					</div>
				</div>
				<div id="category_list">
					<div class="link_blue_rect" id="category_title"><a href="#" onclick="allCategory()">All Categories </a></div>
					<ul id="category_item">
					</ul>
					<div id="add_task_link"><a href='javascript:void(0)' onclick="getCategoryURL()"> + new task </a> </div>
					<div id="delete_category" onclick="deleteCategory()">- delete category</a></div>
					<div id="add_new_category" onclick="toggle_visibility('category_form');"> + new category </div>
					<div id="category_form">
						<div id="category_form_inner">	
							Category name : <br>
							<input type="text" id="add_category_name" value="">
							<br><br>
							Assignee(s) : <br>
							<input type="text" id="add_category_asignee_name" value="">
							<br><br>
							<div id="add_category_button" class="link_red" onclick="add_category()"> Add </div>
						</div>
					</div>
				</div>
			</div>
			<div id="dynamic_content">
				<div id="add_task_container">
					<div id="add_task_header" class="left top30 dynamic_content_head">
						Add New Task
					</div>
					<form name="addtask_form" id="addtask_form" action="<?php echo "addTaskBackend.php?idcat=".$_GET['idcat'] ?>" method="post" target="_self" enctype="multipart/form-data">
						<div id="row1_addtask" class="left top30 dynamic_content_row">
							<div id="task_name_lat" class="left dynamic_content_left">Task Name</div>
							<div id="task_name_rat" class="left dynamic_content_right">
								<input id="task_name_input" onkeyup="javascript:checkTaskName();" onchange="javascript:checkTaskName();" type="text" name="task_name_input" class="left">
								<img src="../img/none.png" id="taskname_validation" class="left signup_form_validation" alt="validation image"/>
							</div>
						</div>
						
						<div id="row2_addtask" class="left top10 dynamic_content_row">
							<div id="attachment_lat" class="left dynamic_content_left">Attachment</div>
							<div id="attachment_rat" class="left dynamic_content_right">
								<input id="attachment_upload" type="file" onchange="javascript:checkTaskAttachment();" name="attachment_upload" class="left">
								<img src="../img/none.png" id="task_attachment_validation" class="left signup_form_validation" alt="validation image"/>
							</div>
						</div>
						
						<div id="row3_addtask" class="left top10 dynamic_content_row">
							<div id="deadline_lat" class="left dynamic_content_left">Deadline</div>
							<div id="deadline_rat" class="left dynamic_content_right">
								<input id="deadline_input" type="date" onchange="javascript:checkDeadline()" name="deadline_input" class="left">
								<img src="../img/none.png" id="deadline_validation" class="left signup_form_validation" alt="validation image"/>
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
					</form>
					<div id="row6_addtask" class="left top10 dynamic_content_row">
						<input id="add_task_button" type="button" onclick="addTask()" value="Add Task" class="link_blue_rect">
					</div>
				</div>
			</div>
		</section>
		
		<!-- Footer Section -->
		
		<footer>
			<div id="footer_container"> 
				<br><br>
				About &nbsp;&nbsp;&nbsp; FAQ &nbsp;&nbsp;&nbsp; Feedback &nbsp;&nbsp;&nbsp; Terms &nbsp;&nbsp;&nbsp; Privay &nbsp;&nbsp;&nbsp; Copyright 
				<br>
				Eurilys 2013
			</div>
		</footer>
<script>
	updateDashboard();
	setInterval(function(){updateDashboard()},1000);
</script>
	</body>
</html>