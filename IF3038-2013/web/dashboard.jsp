<%
	String title = "Dashboard";
	boolean login_permission = true;
%>
<%@include file="inc/header.jsp" %>
		<script>
			window.onload=function(){localStorage.user_id = <%=user_id%>; refreshTask(localStorage.user_id,0); refreshCategory(localStorage.user_id); document.getElementById('taskLink').setAttribute('style','display:none;');};
			function refreshTaskRoutine() {
			  refreshTask(localStorage.user_id,_category_id); refreshCategory(localStorage.user_id);
			}
			var interval = setInterval(refreshTaskRoutine, 10000);
		</script>
		<div class="content">
			<div class="dashboard">	
				<header>
					<h1>Dashboard</h1>
					<ul>
						<li class="add-task-link"><a id="taskLink" href="new_tugas.jsp">New Task</a></li>
						<li class="login"><a href="#" id="categoryLink">New Category</a></li>
					</ul>
				</header>
				
				<section class="login-box" id="loginBox">
					<header>
						<h3>New Category</h3>
					</header>
					<form id="newCategoryForm" action="new_category.jsp" method="post" class="vertical">
						<div class="field">
							<label>Category Name</label>
							<input size="30" maxlength="50" name="name" id="category_name" type="text">
						</div>
						<div class="field">
							<label>Assignee</label>
							<span id="assignee_field">
								<input size="30" maxlength="50" name="assignee[]" id="assignee_add" type="text" onkeyup="assignee_autocomplete(this)" list="suggestion">
							</span>
						</div>
						<div class="field">
							<button id="more_assignee">Add More Assignee</button><br>
							<button type="submit" id="categoryButton">Add Category</button>
						</div>
					</form>
				</section>
				
				<div class="primary">
					<section class="tasks current" id="activeTask">
					
					</section>

					<section class="tasks completed" id="doneTask">

					</section>
				</div>
			
				<div class="secondary">
					<section class="categories">
						<header>
							<h3>Categories</h3>
						</header>
						<ul id="categoryList">

						</ul>
					</section>
				</div>

			</div>

		</div>
<%@include file="inc/footer.jsp" %>

