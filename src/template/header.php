<!DOCTYPE html>

<html><?php // TODO implement offline HTML5 ?>
	<head>
		<meta charset="utf-8">
		<title><?php echo $this->title ?></title>
		<link rel="stylesheet" href="css/style.css">
	</head>

	<body>
		<div class="site-container">
			<header class="site-header">
				<h1><a href="dashboard.html"><?php echo $this->appName ?></a></h1>
				<p><?php echo $this->appTagline ?></p>

				<?php if ($this->loggedIn): ?>
				<nav>
					<ul class="main-links">
						<li class="dashboard-link"><a href="dashboard.html">Dashboard</a></li>
						<li class="profile-link" id="profileLink"><a href="profile.html" id="userFullName">John Doe</a></li>
						<li class="profile-link"><a href="index.html">Logout</a></li>
					</ul>

					<div class="search-box">
						<form action="search.html" method="get" id="searchForm">
							<input type="search" name="q" placeholder="Search">
							<button type="submit">Search</button>
						</form>
					</div>
				</nav>
				<?php endif; ?>
			</header>
			<div id="content">