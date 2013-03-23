<?php
	$title = 'New Tugas';
	$login_permission = 1;
	include 'inc/header.php';
?>
		<div class="content">
			<div class="add-task">
				<header>
					<h1>Add Task</h1>
				</header>
				<form id="new_tugas" action="new_task.php" method="post" enctype="multipart/form-data">
					<div class="field">
						<label>Task Name</label>
						<input size="30" maxlength="25" name="nama" id="nama" type="text">
					</div>
					<div class="field">
						<label>Attachment</label>
						<span id="attachment_field">
							<input size = "30" name="attachment[]" id="attachment" type="file" accept="image/*,video/*">
						</span>
					</div>
					<div class="field">
						<label>Deadline</label>
						<input size = "30" name="deadline" id="deadline" type="date">
					</div>
					<div class="field">
						<label>Assignee</label>
						<span id="assignee_field">
							<datalist id="suggestion">
							</datalist>
							<input size="30" maxlength="50" name="assignee[]" id="assignee_add" type="text" onkeyup="assignee_autocomplete(this)" list="suggestion">
						</span>
					</div>
					<div class="field">
						<label>Tag</label>
						<input size = "30" name="tag" id="tag" type="text">
					</div>
					<div class="buttons">
						<button type="submit">Save</button>
						<button id="more_assignee">Add Assignee</button><br>
						<button id="more_attachment">Add Attachment</button><br>
					</div>
				</form>
			</div>
		</div>
<?php
	include 'inc/footer.php';
?>
