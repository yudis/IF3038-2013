<?php

$this->header();
$id = $_GET['cat'];
//$task = Task::model()->find("id_user = ".$id, array("nama_task","deadline","status"));
?>
		<div class="content">
			<div class="add-task">
				<header>
					<h1>Add Task</h1>
				</header>
				<form id="new_tugas" action="newtask" method="post">
					<input name="id_kategori" type="hidden" value="<?php echo $id; ?>">
					<div class="field">
						<label>Task Name</label>
						<input size="50" maxlength="50" name="nama_task" id="nama" type="text">
					</div>
					<div class="field">
						<label>Attachment</label>
						<input name="attachment" id="attachment" type="file" accept="image/*,video/*">
					</div>
					<div class="field">
						<label>Deadline</label>
						<input name="deadline" id="deadline"  type="text" pattern="^[1-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]$" onclick="datePicker.showCalendar(event);" title="Tahun harus minimal dari tahun 1955." required/>
					</div>
					<div class="field">
						<label>Assignee</label>
						<input name="assignee" id="assignee" type="text">
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