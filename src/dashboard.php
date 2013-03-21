<?php
	$title = 'Dashboard';
	$login_permission = 1;
	include 'inc/header.php';
?>
		<script>
			window.onload=function(){localStorage.user_id = <?php echo getUserID(); ?>; refreshTask(localStorage.user_id,0); refreshCategory(localStorage.user_id);};
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
						<li class="add-task-link"><a href="new_tugas.php">New Task</a></li>
						<li class="login"><a href="#" id="loginLink">New Category</a></li>
					</ul>
				</header>
				
				<section class="login-box" id="loginBox">
					<header>
						<h3>New Category</h3>
					</header>
					<form id="newCategoryForm" action="#" method="post" class="vertical">
						<div class="field">
							<label>Name</label>
							<input size="30" maxlength="50" name="name" id="category_name" type="text">
						</div>
						<div class="field">
							<label>Assignee</label>
							<input size="30" maxlength="50" name="assignee[]" id="login_password" type="password">
						</div>
						<div class="field">
							<button type="submit" id="loginButton">Login</button>
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
<?php
	include 'inc/footer.php';
?>
