username = 'admin';
password = 'admin';

Rp(function() {
	genComment = function(author, body) {
		article = document.createElement('article');
		article.className = 'comment';
		header = document.createElement('header');
		h4 = document.createElement('h4');
		h4.appendChild(document.createTextNode(author));
		header.appendChild(h4);
		body = body.replace('\n', '<br>');
		p = document.createElement('p');
		p.appendChild(document.createTextNode(body));

		article.appendChild(header);
		article.appendChild(p);

		return article;
	};

	Rp('#commentForm').on('submit', function(e) {
		e.preventDefault();
		author = Rp('#userFullName').text();
		body = Rp('#commentBody').val();
		comment = genComment(author, body);
		Rp('#commentsList').append(comment);
	});

	Rp('#loginLink').on('click', function(e) {
		e.preventDefault();

		Rp('#loginBox').toggleClass('visible');

		Rp('#login_username').nodes[0].focus();
	})

	Rp('#loginForm').on('submit', function(e) {

		e.preventDefault();
		validate = function() {
			u = Rp('#login_username').val();
			p = Rp('#login_password').val();
			return u == username && p == password;
		};

		if (validate())
			window.location.href = 'dashboard.html';
		else
			alert('Invalid username/password combination.');
	});

	Rp('#editTaskLink').on('click', function(e) {
		e.preventDefault();

		if (Rp('#editTaskLink').hasClass('editing')) {
			Rp('#editTaskLink').nodes[0].innerHTML = 'Edit Task';
			Rp('#editTaskLink').removeClass('editing');
			Rp('#edit-task').hide();
			Rp('#current-task').show();
		}
		else {
			Rp('#editTaskLink').nodes[0].innerHTML = 'Save';
			Rp('#editTaskLink').addClass('editing');
			Rp('#current-task').hide();
			Rp('#edit-task').nodes[0].style.display = 'block';


		}
	});
});