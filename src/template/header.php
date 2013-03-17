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
				<h1><a href="dashboard.html"><?php echo $this->appName ?></a></h1>
				<p><?php echo $this->appTagline ?></p>

				<?php if ($this->loggedIn): ?>
				<nav>
					<ul class="main-links">
						<?php
						$pages = array(
							'dashboard' => 'Dashboard',
							'profile' => $this->currentUser->fullname,
							'logout' => 'Logout'
						);

						foreach ($pages as $page => $label):
							$active = ($this->currentPage == $page);
						?>

						<li class="<?php echo $page ?>-link<?php if ($active) echo ' active' ?>" id="<?php echo $page ?>Li"><a href="<?php echo $page ?>.php" id="<?php echo $page ?>Link"><?php echo $label ?></a></li>

					<?php endforeach; ?>
					</ul>

					<div class="search-box">
						<form action="search.php" method="get" id="searchForm">
							<select name="type">
								<option value="all">All</option>
								<option value="user">Users</option>
								<option value="category">Categories</option>
								<option value="task">Tasks</option>
							</select>
							<input type="search" name="q" placeholder="Search">
							<button type="submit">Search</button>
						</form>
					</div>
				</nav>
				<?php endif; ?>
			</header>
			<div id="content">