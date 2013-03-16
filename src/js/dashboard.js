Rp(function() {

	if (currentCat === undefined || !currentCat) {
		Rp('#addTaskLink').hide();
	}

	createTaskElement = function(task) {
		// Parse and validate param
		if (task.tags === undefined)
			task.tags = [];
		if (task.done === undefined)
			task.done = false;
		if (task.date === undefined)
			task.date = '';

		// Main logic
		article = Rp.factory('article').addClass('task').attr('data-task-id', task.id);

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

	createCategoryElement = function(cat) {
		li = Rp.factory('li');
		a = Rp
			.factory('a')
			.attr('href', 'dashboard.php?cat=' + cat.id)
			.attr('data-category-id', cat.id)
			.prop('innerHTML', cat.name);

		li.append(a);

		return li;
	}

	fillTasks = function(tasks) {
		tasksList = Rp('#tasksList');
		tasksList.prop('innerHTML', '');
		completedTasksList = Rp('#completedTasksList');
		completedTasksList.prop('innerHTML', '');
		tasks.forEach(function(task) {
			if (task.done)
				completedTasksList.append(createTaskElement(task));
			else
				tasksList.append(createTaskElement(task));
		});
	}

	fillCategories = function(cats) {
		catsList = Rp('#categoryList');
		catsList.prop('innerHTML', '');
		cats.forEach(function(cat) {
			catsList.append(createCategoryElement(cat));
		});
	}

	catreq = Rp.ajaxRequest();
	catreq.onreadystatechange = function() {
		if (catreq.readyState == 4) {
			// Loaded
			response = catreq.responseText;
			tasks = Rp.parseJSON(response);
			Rp('#tasksList').removeClass('loading');
			fillTasks(tasks);
		}
		else if (catreq.readyState > 0) {
			// Still loading
			Rp('#tasksList').addClass('loading');
		}
	}

	var currentCat;
	loadCategory = function(catid) {
		currentCat = catid;
		if (catid) {
			catreq.get('api/retrieve_tasks?category_id=' + catid);
			Rp('#addTaskLink').show();
		}
		else {
			catreq.get('api/retrieve_tasks');
			Rp('#addTaskLink').hide();
		}
	}

	Rp('#categoryList li a').on('click', function(e) {
		e.preventDefault();
		catid = this.getAttribute('data-category-id');
		state = {'categoryID': catid};
		console.log(state);
		history.pushState(state, this.innerHTML, this.href);
		loadCategory(catid);
	});

	window.onpopstate = function(e) {
		console.log(e);
		if (!e.state)
			loadCategory(0);
		else {
			catid = e.state.categoryID;
			if (catid !== undefined)
				loadCategory(catid);
		}
	}

	showModal = function() {
		Rp('#modalOverlay').addClass('visible');
	}
	hideModal = function() {
		Rp('#modalOverlay').removeClass('visible');
	}
	Rp('.modal-overlay .close').on('click', function() {
		Rp(this.parentNode.parentNode).removeClass('visible');
	})


	Rp('#addCategoryButton').on('click', function() {
		showModal();
	});
});