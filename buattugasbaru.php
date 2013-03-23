<?php

$this->header(); 
?>
		<div class="content">
			<div class="add-task">
				<header>
					<h1>Add Task</h1>
				</header>
				<form id="new_tugas" action="#" method="post">
					<div class="field">
						<label>Task Name</label>
						<input size="25" maxlength="25" name="nama" id="nama" type="text">
					</div>
					<div class="field">
						<label>Attachment</label>
						<input name="attachment" id="attachment" type="file" accept="image/*,video/*">
					</div>
					<div class="field">
						<label>Deadline</label>
						<input name="deadline" id="deadline" type="date">
					</div>
					<div class="field">
						<label>Assignee</label>
						<input name="assignee" id="assignee" type="text" list="friends">
						<datalist id="friends">
							<option value="Yusuf Ardi">
							<option value="Agnes Theresia">
							<option value="Kevin Indra">
						</datalist>
					</div>
					<div class="field">
						<label>Tag</label>
						<input name="tag" id="tag" type="text">
					</div>
					<div class="buttons">
						<button type="submit">Save</button>
					</div>
				</form>
			</div>
		</div>
<?php $this->footer() ?>