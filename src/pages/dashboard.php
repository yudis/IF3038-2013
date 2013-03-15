<?php

// Business Logic Here

$tasks = Task::model()->findAll();
$categories = Category::model()->findAll();

// Presentation Logic Here

$this->header()
?>
		<div class="content">
			<div class="dashboard">	
				<header>
					<h1>Dashboard</h1>
					<ul>
						<li class="add-task-link"><a href="new_tugas.html">New Task</a></li>
					</ul>
				</header>
				<div class="primary">
					<section class="tasks current">
						<header>
							<h3>All Tasks</h3>
						</header>

<?php
foreach ($tasks as $task):
	$deadline_datetime = new DateTime($task->deadline); ?>

						<article class="task" data-task-id="<?php echo $id_task ?>" data-category="a">
							<header>
								<h1>
									<label>
										<span class="task-checkbox"><input type="checkbox" class="task-checkbox"></span>
										<a href="view_tugas_5.html"><?php echo $task->nama_task; ?></a>
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
<?php /*

						<article class="task" data-task-id="1" data-category="a">
							<header>
								<h1>
									<label>
										<span class="task-checkbox"><input type="checkbox" class="task-checkbox"></span>
										<a href="view_tugas_6.html">Tugas 6</a>
									</label>
								</h1>
							</header>
							<div class="details">
								<!-- <p class="category">
									<span class="detail-label">Kategori:</span>
									<span class="detail-content">Makan</span>
								</p> -->
								<p class="deadline">
									<span class="detail-label">Deadline:</span>
									<span class="detail-content">
										19 Februari 2013
									</span>
								</p>
								<p class="tags">
									<span class="detail-label">Tag:</span>
									<span class="tag">satu</span>
									<span class="tag">dua</span>
									<span class="tag">tiga</span>
									<span class="tag">empat</span>
								</p>
							</div>
						</article>

						<article class="task" data-task-id="1" data-category="a">
							<header>
								<h1>
									<label>
										<span class="task-checkbox"><input type="checkbox" class="task-checkbox"></span>
										<a href="view_tugas_7.html">Tugas 7</a>
									</label>
								</h1>
							</header>
							<div class="details">
								<!-- <p class="category">
									<span class="detail-label">Kategori:</span>
									<span class="detail-content">Makan</span>
								</p> -->
								<p class="deadline">
									<span class="detail-label">Deadline:</span>
									<span class="detail-content">
										19 Februari 2013
									</span>
								</p>
								<p class="tags">
									<span class="detail-label">Tag:</span>
									<span class="tag">satu</span>
									<span class="tag">dua</span>
									<span class="tag">tiga</span>
									<span class="tag">empat</span>
								</p>
							</div>
						</article>
					</section>

					<section class="tasks completed">

						<header>
							<h3>Completed Tasks</h3>
						</header>
						
						<article class="task" data-task-id="1" data-category="a">
							<header>
								<h1>
									<label>
										<span class="task-checkbox"><input type="checkbox" class="task-checkbox"></span>
										<a href="view_tugas_1.html">Tugas 1</a>
									</label>
								</h1>
							</header>
							<div class="details">
								<!-- <p class="category">
									<span class="detail-label">Kategori:</span>
									<span class="detail-content">Makan</span>
								</p> -->
								<p class="deadline">
									<span class="detail-label">Deadline:</span>
									<span class="detail-content">
										19 Februari 2013
									</span>
								</p>
								<p class="tags">
									<span class="detail-label">Tag:</span>
									<span class="tag">satu</span>
									<span class="tag">dua</span>
									<span class="tag">tiga</span>
									<span class="tag">empat</span>
								</p>
							</div>
						</article>
						
						<article class="task" data-task-id="1" data-category="a">
							<header>
								<h1>
									<label>
										<span class="task-checkbox"><input type="checkbox" class="task-checkbox"></span>
										<a href="view_tugas_2.html">Tugas 2</a>
									</label>
								</h1>
							</header>
							<div class="details">
								<!-- <p class="category">
									<span class="detail-label">Kategori:</span>
									<span class="detail-content">Makan</span>
								</p> -->
								<p class="deadline">
									<span class="detail-label">Deadline:</span>
									<span class="detail-content">
										19 Februari 2013
									</span>
								</p>
								<p class="tags">
									<span class="detail-label">Tag:</span>
									<span class="tag">satu</span>
									<span class="tag">dua</span>
									<span class="tag">tiga</span>
									<span class="tag">empat</span>
								</p>
							</div>
						</article>


						<article class="task" data-task-id="1" data-category="a">
							<header>
								<h1>
									<label>
										<span class="task-checkbox"><input type="checkbox" class="task-checkbox"></span>
										<a href="view_tugas_3.html">Tugas 3</a>
									</label>
								</h1>
							</header>
							<div class="details">
								<!-- <p class="category">
									<span class="detail-label">Kategori:</span>
									<span class="detail-content">Makan</span>
								</p> -->
								<p class="deadline">
									<span class="detail-label">Deadline:</span>
									<span class="detail-content">
										19 Februari 2013
									</span>
								</p>
								<p class="tags">
									<span class="detail-label">Tag:</span>
									<span class="tag">satu</span>
									<span class="tag">dua</span>
									<span class="tag">tiga</span>
									<span class="tag">empat</span>
								</p>
							</div>
						</article>

						<article class="task" data-task-id="1" data-category="a">
							<header>
								<h1>
									<label>
										<span class="task-checkbox"><input type="checkbox" class="task-checkbox"></span>
										<a href="view_tugas_4.html">Tugas 4</a>
									</label>
								</h1>
							</header>
							<div class="details">
								<!-- <p class="category">
									<span class="detail-label">Kategori:</span>
									<span class="detail-content">Makan</span>
								</p> -->
								<p class="deadline">
									<span class="detail-label">Deadline:</span>
									<span class="detail-content">
										19 Februari 2013
									</span>
								</p>
								<p class="tags">
									<span class="detail-label">Tag:</span>
									<span class="tag">satu</span>
									<span class="tag">dua</span>
									<span class="tag">tiga</span>
									<span class="tag">empat</span>
								</p>
							</div>
						</article>
						
						<article class="task" data-task-id="1" data-category="a">
							<header>
								<h1>
									<label>
										<span class="task-checkbox"><input type="checkbox" class="task-checkbox"></span>
										<a href="view_tugas_8.html">Tugas 8</a>
									</label>
								</h1>
							</header>
							<div class="details">
								<!-- <p class="category">
									<span class="detail-label">Kategori:</span>
									<span class="detail-content">Makan</span>
								</p> -->
								<p class="deadline">
									<span class="detail-label">Deadline:</span>
									<span class="detail-content">
										19 Februari 2013
									</span>
								</p>
								<p class="tags">
									<span class="detail-label">Tag:</span>
									<span class="tag">satu</span>
									<span class="tag">dua</span>
									<span class="tag">tiga</span>
									<span class="tag">empat</span>
								</p>
							</div>
						</article>

*/ ?>
					</section>
				</div>
			
				<div class="secondary">
					<section class="categories">
						<header>
							<h3>Categories</h3>
						</header>
						<ul>
							<?php foreach ($categories as $cat): ?>
							<li><a href="?" data-category-id="<?php echo $cat->id_kategori ?>"><?php echo $cat->nama_kategori ?></a></li>
							<?php endforeach; ?>
							<li><a href="#"><?php  ?></a></li>
						</ul>
					</section>
				</div>

			</div>

		</div>
<?php $this->footer() ?>