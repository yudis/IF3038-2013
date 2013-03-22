<html>
	<head>
		<title>Shared To Do List</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<link rel="shortcut icon" href="favicon.ico">
	</head>
	<body>
		<div id="navigation">
			<img src="images/logo.gif">
			<a href="dashboard.php">DASHBOARD</a>
			<a href="profile.php">PROFILE</a>
			<a href="#" onclick="toggleSearch()">SEARCH</a>
			<a href="index.php">LOGOUT</a>			
		</div>
		<div id="search">
			<input type="text" size="50%">
		</div>		
		<div class="clearall container" id="registerform">
			<h2>Add Category</h2>
			<form action="#" method="post">
			<fieldset>
				<p>
					<label>Category name <abbr title="Required">*</abbr></label>
					<input value=""
					required="required" aria-required="true"					
					title="Your full name (first and last name)"
					type="text" spellcheck="false" size="20" />
				</p>
				<p>
					<label>User <abbr title="Required">*</abbr></label>
					<input value=""
					required="required" aria-required="true"
					title="Your email address"
					type="text" spellcheck="false" size="20" />
				</p>			
			</fieldset>
			<fieldset>
			<button id="register" type="submit" onclick="alert('Category successfully added!')">Add Category</button>
			</fieldset>
			</form>
		</div>		
		
		<script type="text/javascript" src="validation.js"></script>		
	</body>
</html>