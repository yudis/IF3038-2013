<!DOCTYPE html>
<html>
	<head>
		<title>Add New Task</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<script src="calendar.js"></script>
		<link href="calendar.css" rel="stylesheet">
		<script>
			var chktaskname = false;
			var chkattach = false;
			var chkassignee = false;
			var chktag = false;
			function hide_create_button() {
				document.getElementById("create").style.display = 'none';
			}
			function show_create_button() {
				if (chktaskname == true && chkattach == true && chkassignee == true && chktag == true) {
					document.getElementById("create").style.display = 'block';
				}
			}
			function check_string() {
				var i;
				var str = document.getElementById("taskname").value;
				for (i = 0; i < str.length; i++) {
					if ((str.charAt(i) != ' ' && str.charCodeAt(i) < 48) || (str.charCodeAt(i) > 57 && str.charCodeAt(i) < 65) || (str.charCodeAt(i) > 90 && str.charCodeAt(i) < 97) || str.charCodeAt(i) > 122) {
						return false;
					}
				}
				return true;
			}
			function check_task_name() {
				if (document.getElementById("taskname").value.length > 0 && document.getElementById("taskname").value.length < 26) {
					chktaskname = check_string();
					if (chktaskname == true) {
						show_create_button();	
					} else {
						hide_create_button();
					}
				} else {
					chktaskname = false;
					hide_create_button();
				}
			}
			function check_attachment() {
				var str = document.getElementById("attached").value;
				var ext = str.substring(str.lastIndexOf('.') + 1, str.length).toLowerCase();
				if (ext == "pdf" || ext == "doc" || ext == "docx" || ext == "ppt" || ext == "pptx" || ext == "java" || ext == "jpg" || ext =="jpeg" || ext == "gif" || ext == "mp4") {
					chkattach = true;
					show_create_button();
				} else {
					chkattach = false;
					hide_create_button();
				}
			}
			function check_assignee() {
				if (document.getElementById("assignee").value.length > 0) {
					chkassignee = true;
					show_create_button();
				} else {
					chkassignee = false;
					hide_create_button();
				}
			}
			function check_tag() {
				if (document.getElementById("tag").value.length > 0) {
					chktag = true;
					show_create_button();
				} else {
					chktag = false;
					hide_create_button();
				}
			}
			function check_html5() {
				if (navigator.userAgent.indexOf('Chrome') != -1 || navigator.userAgent.indexOf('Opera') != -1){
					document.getElementById("date_html5").style.display = 'block';
					document.getElementById("date_html").style.display = 'none';
				} else {
					document.getElementById("date_html5").style.display = 'none';
					document.getElementById("date_html").style.display = 'block';
				}
			}
		</script>
	</head>
	<body>
		<div id="main-body-general">
			<!--Header-->
			<div id="header">
				<?php
					include("header.php");
				?>
			</div>
			<div><hr id="border"></div>
			<!--Body-->
			<div id="task-page-body">
				<h1>Category: 
                <?php 
					require('../php/init_function.php');
					$categoryid = $_GET['categoryid'];
					echo getCategoryName($categoryid);
				?>
                </h1>
				<div id="add-task">
					Task name:<br>
					Attach file:<br><br><br><br><br>
					Deadline:<br>
					Asignee:<br>
					Tag:<br>
				</div>
				<div id="add-task-form">
				<form action="../php/inserttask.php" method="post">
					<!--Name-->
					<input type="text" id="taskname" onKeyUp="check_task_name()" name="textTaskName"/><br />
					<!--Attachment
                    "
                    -->
					<input id="attached" onChange="check_attachment()" type="file" name="attachment1"/><br />
  					<input type="file" name="attachment2"/><br />
					<input type="file" name="attachment3"/><br />
					<input type="file" name="attachment4"/><br />
					<input type="file" name="attachment5"/><br />                                                            
					<!--Deadline-->
					<input type="text" class="calendarSelectDate" name="textBirthday"/><div id="calendarDiv"></div>
						</div><br /><br />
					<!--Assignee-->
					<input type="text" id="assignee" onKeyUp="check_assignee()" name="textAssignee"/><br />
					<!--Tag (multivalue)-->
					<input type="text" id="tag" onKeyUp="check_tag()" name="textTag"/><br />
					<br>
					<button id="create">Add Task</button>
				</form>
				</div>
			</div>
		</div>
	</body>
</html>