<%
	String title = "New Tugas";
	boolean login_permission = true;
%>
<%@include file="inc/header.jsp" %>
		<div class="content">
			<div class="add-task">
				<header>
					<h1>Add Task</h1>
				</header>
				<form id="new_tugas" action="new_task.jsp" method="post" enctype="multipart/form-data">
					<div class="field">
						<label>Task Name</label>
						<input size="30" maxlength="25" name="name" id="name" type="text">
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
						<input type="hidden" value="<%= request.getParameter("category_id")%>" name="category_id" />
					</div>
					<div class="buttons">
						<button type="submit">Save</button>
						<button id="more_assignee">Add Assignee</button><br>
						<button id="more_attachment">Add Attachment</button><br>
					</div>
				</form>
			</div>
		</div>
<%@include file="inc/footer.jsp" %>