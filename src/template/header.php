<!DOCTYPE html>

<html><?php // TODO implement offline HTML5 ?>
	<head>
		<meta charset="utf-8">
		<title><?php echo $this->title ?></title>
		<base href="<?php echo $this->basePath ?>">
		<link rel="stylesheet" href="css/style.css">
		<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico">
	</head>

	<body>
		<div class="site-container">
			<header class="site-header">
				<?php if ($this->loggedIn): ?>
				<h1><a href="dashboard.html"><?php echo $this->appName ?></a></h1>
				<p><?php echo $this->appTagline ?></p>

				<nav>
					<ul class="main-links">
						<li class="dashboard-link"><a href="dashboard">Dashboard</a></li>
						<li class="profile-link" id="profileLink"><a href="profile" id="userFullName">John Doe</a></li>
						<li class="profile-link" id="logoutLink"><a href="logout">Logout</a></li>
					</ul>

					<div class="search-box">
						<form action="search.php" method="get" id="searchForm">
							<input type="search" name="q" placeholder="Search">
							<button type="submit">Search</button>
						</form>
					</div>
				</nav>
				<?php endif; ?>
			</header>
			<div id="content">