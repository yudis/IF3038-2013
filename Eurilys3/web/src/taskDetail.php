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
	
	$result = mysql_query("SELECT * FROM task WHERE idtask ='".$_GET['idtask']."'");
	$task = mysql_fetch_array($result);
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
				<div id="edit_task_header" class="left top30 dynamic_content_head">
					<?php echo $task['taskname']?>
				</div>
				
				<input id="edit_task_button" class="left top30 link_blue_rect" 
					onclick="edit_task()" type="button" value="Edit Task">
				
				
				<input id="save_button_td" class="left top30 link_blue_rect" 
					onclick="save_edit_task('<?php echo $task['idtask'];?>')" type="button" value="Save">
				
				
				<div id="row1_taskdetail" class="left top30 dynamic_content_row">
					<div id="task_name_ltd" class="left dynamic_content_left">Task Name</div>
					<div id="task_name_rtd" class="left dynamic_content_right"> <?php echo $task['taskname']?></div>
				</div>
				
				<div id="row2_taskdetail" class="left top10 dynamic_content_row">
					<div id="attachment_ltd" class="left dynamic_content_left">Attachment</div>
					<div id="attachment_rtd" class="left dynamic_content_right">
						<img id="taskdetail_img" src="../img/taskdetail_img.jpg" alt="Rikka-chan">
					</div>
				</div>
				
				<div id="row3_taskdetail" class="left top10 dynamic_content_row">
					<div id="deadline_ltd" class="left dynamic_content_left">Deadline</div>
					<div id="deadline_rtd" class="left dynamic_content_right"><?php echo $task['deadline'] ?></div>
				</div>
				
				<div id="row4_taskdetail" class="left top10 dynamic_content_row">
					<div id="assignee_ltd" class="left dynamic_content_left">Assignee</div>
					<div id="assignee_rtd" class="left dynamic_content_right"></div>
				</div>
			
				<div id="row5_taskdetail" class="left top10 dynamic_content_row">
					<div id="tag_ltd" class="left dynamic_content_left">Tag</div>
					<div id="tag_rtd" class="left dynamic_content_right"></div>
				</div>
				
				<div id="row7_taskdetail" class="left top10 dynamic_content_row">
					<div id="addcomment_ltd" class="left dynamic_content_left">Add Comment</div>
					<div id="addcomment_rtd" class="left dynamic_content_right">
						<form autocomplete="off">
							<textarea id="comment_textarea" rows="5" cols="50" name="CommentBox">
							</textarea><br>
							<input type="submit" value="Add">
							<br><br><br>
						</form>
					</div>
				</div>
				
				<div id="row6_taskdetail" class="left top10 dynamic_content_row">
					<div id="comment_ltd" class="left dynamic_content_left">Comment</div>
					<div id="comment_rtd" class="left dynamic_content_right">Lorem ipsum dolor sit amet, 
					ea per meliore copiosae gubergren, ei latine iracundia euripidis sed, 
					ut odio lorem qui. Mea ne nobis feugait, sea iuvaret pertinax at, 
					harum commodo ne per. Per timeam dolorum no. Ne nam utroque percipitur, 
					augue partem imperdiet in pri. At est omnes habemus. Atqui antiopam 
					indoctum quo cu, no purto antiopam vis. Agam vidit ea nam, audiam alterum 
					appellantur mel ad. Copiosae erroribus vulputate no est, eos affert 
					minimum atomorum ei. Id nec choro luptatum, oblique suscipit nam ex.
					Sit elitr legere postulant te. Id putent dolorem usu, in sanctus 
					scripserit est. Has at zril quaeque sensibus, at case elaboraret vis, 
					nec natum noluisse salutandi ut. Putent habemus adversarium vim et, 
					has platonem hendrerit eu, vim at urbanitas pertinacia. Id pri partem 
					malorum aliquando, ne eum veniam animal probatus.
					</div>
				</div>
			</div>
		</section>
		
		<!-- Footer Section -->
		<footer>
			<div id="footer_container"> 
				About &nbsp;&nbsp;&nbsp; FAQ &nbsp;&nbsp;&nbsp; Feedback &nbsp;&nbsp;&nbsp; Terms &nbsp;&nbsp;&nbsp; Privay &nbsp;&nbsp;&nbsp; Copyright 
				<br>
				Eurilys 2013
			</div>
		</footer>
		
		
<script>
	getAssignee('<?php echo $task['idtask'];?>');
	getTag('<?php echo $task['idtask'];?>');
	updateDashboard();
	setInterval(function(){updateDashboard()},1000);
</script>
	</body>
</html>