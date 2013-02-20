function edit_task() {
	document.getElementById("deadline_rtd").innerHTML = '<input type="text" name="deadline_td"></input>';
	document.getElementById("assignee_rtd").innerHTML = '<input type="text" name="assignee_td" autocomplete="on"></input>';
	document.getElementById("tag_rtd").innerHTML = '<input type="text" name="tag_td"></input>';
	document.getElementById("edit_task_button").style.display = 'none';
	document.getElementById("save_button_td").style.display = 'block';
}

function save_edit_task() {
	document.getElementById("deadline_rtd").innerHTML = '21/2/2012';
	document.getElementById("assignee_rtd").innerHTML = 'Sharon';
	document.getElementById("tag_rtd").innerHTML = 'HTML 5, CSS 3';
	document.getElementById("save_button_td").style.display = 'none';
	document.getElementById("edit_task_button").style.display = 'block';
}