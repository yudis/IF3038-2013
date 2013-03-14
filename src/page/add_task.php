<!DOCTYPE html>
<html>
	<head>
		<title>Add New Task</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<script src="calendar.js"></script>
		<link href="calendar.css" rel="stylesheet">
		<script type="text/javascript" src="add_task.js"></script>
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
					Task name:<br><br>
					Attach file:<br><br><br><br><br><br>
					Deadline,<br>
					Date:<br>
					Time:<br><br>
					Asignee:<br><br>
					Tag:
				</div>
				<div id="add-task-form">
				<form action="../php/inserttask.php" method="post">
					<!--Name-->
					<div id="spacing">
					<input type="text" id="taskname" onKeyUp="check_task_name()" name="textTaskName"/>
					</div>
					<!--Attachment
                    "
                    -->
					<div id="spacing-attach">
					<input id="attached1" onChange="check_attachment1()" type="file" name="attachment1"/><br />
  					<input id="attached2" onChange="check_attachment2()" type="file" name="attachment2"/><br />
					<input id="attached3" onChange="check_attachment3()" type="file" name="attachment3"/><br />
					<input id="attached4" onChange="check_attachment4()" type="file" name="attachment4"/><br />
					<input id="attached5" onChange="check_attachment5()" type="file" name="attachment5"/>
					</div>					
					<!--Deadline-->
					<div id="spacing-deadline">
					<input type="text" class="calendarSelectDate" name="textDateBirthday"/><div id="calendarDiv"></div><br />
					<input type="text" onKeyUp="check_time()" id="time" name="textTimeBirthday" placeholder="HH:MM"/>
					</div>
					<!--Assignee-->
					<div id="spacing">
					<input type="text" id="task-assignee" onKeyUp="addAssignee()" name="textAssignee" list="assignee-task" value="" placeholder="tag1,tag2,tag3">
					<div id="shared-with"></div>
					</div>
					<!--Tag (multivalue)-->
					<div id="spacing">
					<input type="text" id="tag" onKeyUp="check_tag()" name="textTag" placeholder="tag1,tag2,tag3"/>
					</div>
					<div id="warning-message"></div>
					<button id="create">Add Task</button>
				</form>
				</div>
                
                <!-- BUATAN WHILDA TEST ADD TASK JANGAN DIHAPUS KY ATI2 KLO CONFLICT -->
				<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
				<div>
					Task name:<br>
					Attach file:<br><br><br><br><br>
					Deadline:<br><br>
					Asignee:<br>
					Tag:<br>
				</div>
				<div>
				<form enctype="multipart/form-data" method="post" action="../php/inserttask.php?categoryid=<?php echo $categoryid;?>">
					<!--Name-->
					<input type="text" id="taskname"name="textTaskNameb"/><br />
					<!--Attachment
                    "
                    -->
					<input type="file" name="attachment1b"/><br />
  					<input type="file" name="attachment2b"/><br />
					<input type="file" name="attachment3b"/><br />
					<input type="file" name="attachment4b"/><br />
					<input type="file" name="attachment5b"/><br />                                                            
					<!--Deadline-->
					<input type="date" id="date_html5" /><br /><br />
					<!--Assignee-->
					<input type="text" id="assignee" name="textAssigneeb"/><br />
					<!--Tag (multivalue)-->
					<input type="text" id="tag" name="textTagb"/><br />
					<br>
					<input type="submit" value="Submit NOW!!"/>
				</form>
				</div>
			</div>
		</div>
	</body>
</html>