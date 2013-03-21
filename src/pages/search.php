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

$this->header('Search', 'search');
?>
<div class="content">
	<div>
	<header>
		<h1>Search</h1>
	</header>
	<?php if ($type == 'tasks' || $all):

$tasks = Task::model()->findAll("nama_task LIKE '$terms'");
?>
<div class="result-set">
	<section class="tasks">
		<!-- Tasks -->
		<?php
foreach ($tasks as $task):
	$deadline_datetime = new DateTime($task->deadline); ?>

		<article class="task" data-task-id="<?php echo $task->id_task ?>">
			<header>
				<h1>
					<label>
						<span class="task-checkbox"><input type="checkbox" class="task-checkbox" data-task-id="<?php echo $task->id_task ?>"></span>
						<a href="tugas.php?id=<?php echo $task->id_task ?>"><?php echo $task->nama_task; ?></a>
					</label>
				</h1>
			</header>
			<div class="details">
				<p class="deadline">
					<span class="detail-label">Deadline:</span>
					<span class="detail-content">
						<?php echo $deadline_datetime->format('j F Y') ?>
					</span>
			</p>
				<p class="tags">
					<span class="detail-label">Tag:</span>
					<?php foreach ($task->getTags() as $tag) {
						echo '<span class="tag">' . $tag->tag . '</span>';
					} ?>
				</p>
			</div>
		</article>

<?php endforeach; ?>
	</section>
</div>
	<?php elseif ($type == 'categories' || $all):
		$categories = Category::model()->findAll("nama_kategori LIKE '$terms'");
	?>
	<?php elseif ($type == 'users' || $all):
		$users = User::model()->findAll("username LIKE '$terms' OR fullname LIKE '$terms'");
	?>
<?php endif; ?>
</div>
</div>
<?php $this->footer() ?>