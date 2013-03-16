Rp(function() {

/*
<article class="task" data-task-id="<?php echo $id_task ?>" data-category="a">
	<header>
		<h1>
			<label>
				<span class="task-checkbox"><input type="checkbox" class="task-checkbox"></span>
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
*/

	createTaskElement = function(task) {
		// Parse and validate param
		if (task.tags === undefined)
			task.tags = [];
		if (task.done === undefined)
			task.done = false;
		if (task.date === undefined)
			task.date = '';

		// Main logic
		article = Rp.factory('article').addClass('task');

		header = Rp.factory('header');
		h1 = Rp.factory('h1');
		label = Rp.factory('label');
		checkboxSpan = Rp.factory('span').addClass('task-checkbox');
		checkbox = Rp.factory('input').addClass('task-checkbox').prop('type', 'checkbox').prop('checked', task.done);
		space = document.createTextNode(' ');
		mainLink = Rp.factory('a').prop('href', 'tugas.php?id=' + task.id).prop('innerHTML', task.name);

		detailsDiv = Rp.factory('div').addClass('details');

		deadlineP = Rp.factory('p').addClass('deadline');
		deadlineContentSpan = Rp.factory('span').addClass('detail-content').prop('innerHTML', task.date);

		tagsP = Rp.factory('p').addClass('tags');
		task.tags.forEach(function(tag) {
			tagSpan = Rp.factory('span').addClass('tag').prop('innerHTML', tag);
			tagsP.append(tagSpan);
		})

		checkboxSpan.append(checkbox);
		label.append(checkboxSpan).append(space).append(mainLink);
		h1.append(label);
		header.append(h1);

		deadlineP.append(deadlineContentSpan);
		detailsDiv.append(deadlineP).append(tagsP);

		article.append(header).append(detailsDiv);	
		return article;
	}

});