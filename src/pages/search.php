<?php

// Business logic here

$q = $_GET['q'];

$terms = str_replace(' ', '%', $q);
$terms = "%$terms%";
$terms = addslashes($terms);

$catf = 'nama_kategori';
$taskf = 'nama_task';
$userf = 'nama_user';

$type = $_GET['type'];
$all = $type == 'all';

// Presentation logic here

$this->requireJS('search');
$this->header('Search', 'search');
?>
<div class="content">
	<div>
	<header>
		<h1>Search</h1>
	</header>
	<div class="search-results<?php if ($all) echo ' all' ?>">
	<?php if ($type == 'task' || $all):

$tasks = Task::model()->findAllLimit("nama_task LIKE '$terms'", array(), 0, 10);
?>
<div class="result-set">
	<section class="tasks">
		<header>
			<h3>Tasks</h3>
		</header>
		<!-- Tasks -->
		<div id="taskList">
			<?php foreach ($tasks as $task) { require dirname(__FILE__) . "/../template/task.php"; } ?>
			<?php if (!$tasks) { echo '<p>No tasks were found.</p>'; } ?>
		</div>
	</section>
</div>
	<?php endif; if ($type == 'user' || $all):
		$users = User::model()->findAllLimit("username LIKE '$terms' OR fullname LIKE '$terms'", array(), 0, 10);
	?>

	<div class="result-set">
		<section class="users">
			<header>
				<h3>Users</h3>
			</header>
			<div id="userList">
				<?php foreach ($users as $user) { require dirname(__FILE__) . "/../template/user.php"; } ?>
				<?php if (!$users) { echo '<p>No users were found.</p>'; } ?>
			</div>
		</section>
	</div>
<?php endif; if ($type == 'category' || $all):
		$categories = Category::model()->findAllLimit("nama_kategori LIKE '$terms'", array(), 0, 10);
	?>
	<div class="result-set">
		<section class="categories">
			<header>
				<h3>Categories</h3>
			</header>
			<ul id="categoryList">
			<?php foreach ($categories as $cat): ?>
				<li id="categoryLi<?php echo $cat->id_kategori ?>"><a href="dashboard.php?cat=<?php echo $cat->id_kategori ?>" data-category-id="<?php echo $cat->id_kategori ?>"><?php echo $cat->nama_kategori ?></a></li>

			<?php endforeach; ?>
			</ul>
			<?php if (!$categories) { echo '<p>No categories were found.</p>'; } ?>
		</section>
	</div>
	<?php endif; ?>
</div>
</div>
<script>
var isAll = <?php echo $all ? 'true' : 'false'; ?>;
var q = "<?php echo $q ?>";
var currentType = "<?php echo $type ?>";
</script>
<?php $this->footer() ?>