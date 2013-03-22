<?php
	if (!$this->loggedIn) 
	{
		header('Location: index');
		return;
	}
	
	if (ISSET($_GET['id']))
	{
		$task = Task::model()->find("id_task = ".addslashes($_GET['id']));
		if (!$task)
		{
			// redirect to error page
		}
		$users = $task->getAssignee();
		$tags = $task->getTags();
		$attachments = $task->getAttachment();
		$comments = $task->getComment();
	}
	else
	{
		// redirect to error page
	}
	
	$this->header('Dashboard', 'dashboard');
?>

		<div class="content">
			<div class="task-details not-editing">
				<header>
					<form method="POST">
						<h1>
							<label>
								<span class="task-checkbox"><input type="checkbox" class="task-checkbox"></span>
								<span class="task-title"><?php echo $task->nama_task; ?></span>
							</label>
						</h1>
					</form>

					<ul>
						<li><a href="#" id="editTaskLink">Edit Task</a></li>
					</ul>
				</header>
				<div id="current-task">
					<section class="details">
						<header>
							<h3>Details</h3>
						</header>
						<?php
						/*
						<p class="description">
							<span class="detail-label">Description:</span>
							<span>Lorem ipsum dolor sit amet, task description goes here.</span>
						</p>
						*/ ?>
						<p class="deadline">
							<span class="detail-label">Description:</span>
							<span class="detail-content"><?php echo (new DateTime($task->deadline))->format('j F Y'); ?></span>
						</p>
						<p class="assignee">
							<span class="detail-label">Assignee:</span>
							<span class="detail-content">
								<?php 
									$string = "";
									foreach ($users as $user)
									{
										$string .= "<a href='profile?id=".$user->id_user."'>".$user->username."</a>,"; 
									}
									$string = substr($string, 0, -1);
									echo $string;
								?>
							</span>
						</p>
						<p class="category">
							<span class="detail-label">Kategori:</span>
							<span class="detail-content"><?php echo $task->getCategory()->nama_kategori; ?></span>
						</p>
						<p class="tags">
							<span class="detail-label">Tag:</span>
							<?php
								foreach ($tags as $tag)
								{
									echo '<span class="tag">';
									echo $tag->tag_name;
									echo '</span>';
								}
							?>
						</p>
					</section>
					<section class="attachment">
						<header>
							<h3>Attachment</h3>
						</header>
						<?php
							foreach ($attachments as $attachment)
							{
								echo "<figure>";
								echo $attachment->attachment;
								echo "</figure>";
							}
						?>
					</section>
				</div>
				<div id="edit-task">
					<form id="new_tugas" action="edit_tugas" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id_task" value="<?php echo $task->idl ?>">
						<div class="field">
							<label>Task Name</label>
							<input size="25" maxlength="25" name="nama_task" id="nama" type="text" value="<?php echo $task->nama_task; ?>">
						</div>
						<div class="field">
							<label>Attachment</label>
							<input name="attachment[]" id="attachment" type="file" multiple="">
						</div>
						<?php
							$i = 1;
							foreach ($attachments as $attachment)
							{
								echo '<div class="field">';
								echo '<span class="detail-label">Attachment '.$i.':</span>';
								echo '<span class="detail-content">';
								echo $attachment->attachment;
								echo '</span>';
								echo '</div>';
								$i++;
							}
						?>
						<div class="field">
							<label>Deadline</label>
							<input name="deadline" id="deadline" type="text" value="<?php echo (new DateTime($task->deadline))->format('j F Y'); ?>">
						</div>
						<div class="field">
							<label>Assignee</label>
							<input name="assignee" id="assignee" type="text"
							value="<?php 
									$string = "";
									foreach ($users as $user)
									{
										$string .= $user->username.","; 
									}
									$string = substr($string, 0, -1);
									echo $string;
								?>">
						</div>
						<div class="field">
							<label>Tag</label>
							<input name="tag" id="tag" type="text" value="<?php 
									$string = "";
									foreach ($tags as $tag)
									{
										$string .= $tag->tag_name.",";
									}
									$string = substr($string, 0, -1);
									echo $string;
								?>">
						</div>
						<div class="buttons">
							<button type="submit">Save Changes</button>
						</div>
					</form>
				</div>
				<section class="comments">
					<header>
						<h3><?php echo count($comments); ?> Comment<?php echo (count($comments)>1)? "s" : ""; ?></h3>
					</header>

					<div id="commentsList">
						<?php
							foreach ($comments as $comment)
							{
								$user = $comment->getUser();
								echo '<article class="comment">';
									echo '<header>';
										echo '<h4>'.$user->username.'</h4>';
									echo '</header>';
									echo '<p>';
										echo $comment->komentar;
									echo '</p>';
								echo '</article>';
							}
						?>
					</div>

					<div class="comment-form">
						<h3>Add Comment</h3>
						<form id="commentForm" action="#" method="post">
							<textarea name="komentar" id="commentBody"></textarea>
							<button type="submit">Send</button>
						</form>
					</div>
				</section>
			</div>
		</div>
		<?php
			$this->calendar();
		?>
<?php
	$this->requireJS('datepicker');
	$this->requireJS('tugas');
	$this->footer();
?>
