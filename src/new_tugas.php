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
				<form id="new_tugas" action="#" method="post">
					<div class="field">
						<label>Task Name</label>
						<input size="25" maxlength="25" name="nama" id="nama" type="text">
					</div>
					<div class="field">
						<label>Attachment</label>
						<input size = "25" name="attachment" id="attachment" type="file" accept="image/*,video/*">
					</div>
					<div class="field">
						<label>Deadline</label>
						<input size = "25" name="deadline" id="deadline" type="date">
					</div>
					<div class="field">
						<label>Assignee</label>
						<input size = "25" name="assignee[]" id="assignee" type="text" list="friends"><br>
						<label></label>
						<input size = "25" name="assignee[]" id="assignee" type="text" list="friends"><br>
						<datalist id="friends">
							<option value="Irfan Kamil">
							<option value="Tubagus Andhika Nugraha">
							<option value="Sonny Fitra Arfian">
						</datalist>
					</div>
					<div class="field">
						<label>Tag</label>
						<input size = "25" name="tag" id="tag" type="text">
					</div>
					<div class="buttons">
						<button type="submit">Save</button>
					</div>
				</form>
			</div>
		</div>
<?php
	include 'inc/footer.php';
?>
