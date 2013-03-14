<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width; initial-scale=1.0" />
        <title><?php echo $title ?></title>
        <link rel="stylesheet" type="text/css" href="./styles/default.css" />
        <link rel="stylesheet" type="text/css" href="./styles/mediaqueries.css" />
		<?php echo $headTags ?>
    </head>
    <body>
        <div class="page">
            <header class="content">
                <nav>
                    <div class="logo"><a href="dashboard.html"><img alt="Home" src="images/logo.png" /></a></div>
                    <ul>
                        <li><div><a href="dashboard.php">Dashboard</a></div></li><li><div><a href="profile.php">Profile</a></div></li><li><div><a href="index.php?logout">Logout</a></div></li>
                    </ul>
                    <div class="search">
                        <div id="searchwrapper">
                            <form action="search.php">
                                <input type="text" class="searchbox" name="q" value="" placeholder="Enter task name here.." />
								<button class="searchbox_submit search"><img src="images/search.png" alt="search..."/></button>
                            </form>
                        </div>
                    </div>
                </nav>
            </header>
            <div class ="content">