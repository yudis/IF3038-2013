function edit_task(task_id) {
	/*
	var task_id = document.getElementById("hidden_task_id").value;
	var task_name = document.getElementById("task_name_rtd").innerHTML;
	var task_status = document.getElementById("task_status_rtd").innerHTML;
	var task_deadline = document.getElementById("deadline_rtd").innerHTML;
	var task_assignee = document.getElementById("hidden_ass_name").value;
	var task_tag = document.getElementById("tag_rtd").innerHTML;
	
	document.getElementById("deadline_rtd").innerHTML = '<input class="edit_task_input" id="edit_task_deadline" type="date" name="deadline_td" value="'+task_deadline+'"/>';
	document.getElementById("assignee_rtd").innerHTML = '<input class="edit_task_input" id="edit_task_assignee" type="text" name="assignee_td" autocomplete="on" value="'+task_assignee+'"/>';
	document.getElementById("tag_rtd").innerHTML = '<input class="edit_task_input" id="edit_task_tag" type="text" name="tag_td" value="'+task_tag+'"/>';
	
	document.getElementById("edit_task_button").style.display = 'none';
	document.getElementById("save_button_td").style.display = 'block';*/
	window.location.href="edit_task.php?task_id="+task_id;
}

function save_edit_task() {
	var task_id = document.getElementById("hidden_task_id").value;
	var task_deadline = document.getElementById("edit_task_deadline").value;
	var task_assignee = document.getElementById("edit_task_assignee").value;
	var task_tag = document.getElementById("edit_task_tag").value;
		
	/*
	document.getElementById("deadline_rtd").innerHTML = '21/2/2012';
	document.getElementById("assignee_rtd").innerHTML = 'Sharon';
	document.getElementById("tag_rtd").innerHTML = 'HTML 5, CSS 3'; */
	
}

function edittaskDeleteAss(taskID,assID) {
	
}

function addMoreAssignee(id) {
}