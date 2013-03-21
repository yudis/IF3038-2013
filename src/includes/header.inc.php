<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!-- <meta name="viewport" content="width=device-width; initial-scale=1.0" /> -->
        <title><?php if (isset($title)) echo $title; else echo "Todolist"; ?></title>
        <link rel="stylesheet" type="text/css" href="./styles/default.css" />
        <link rel="stylesheet" type="text/css" href="./styles/mediaqueries.css" />
        <script src="./scripts/json2.js" type="application/javascript"></script>
        <script src="./scripts/helper.js" type="application/javascript"></script>
        <script src="./scripts/ajaxhelper.js" type="application/javascript"></script>
        <script src="./scripts/datetimepicker.js" type="application/javascript"></script>
		<?php if (isset($headTags)) echo $headTags; ?>
    </head>
    <body <?php if (isset($bodyAttrs)) echo $bodyAttrs; ?>>
        <div class="page">
            <header class="content">
                <nav>
                    <div class="logo"><a href="dashboard.php"><img alt="Home" src="images/logo.png" /></a></div>
                    <ul>
                        <li><div><a href="dashboard.php">Dashboard</a></div></li><li><div><a href="profile.php">Profile</a></div></li><li><div><a href="index.php?logout">Logout</a></div></li>
                    </ul>
                    <div class="search">
                        <div id="searchwrapper">
                            <form method="GET" action="search.php">
                                <input type="text" class="searchbox" name="q" placeholder="Enter task name here.." />
                                <select class="type" name="filter" id="filter">
                                    <option>All</option>
                                    <option>Username</option>
                                    <option>Title</option>
                                    <option>Task</option>
                                </select>
                                <button class="search"><img src="images/find.png" alt="Search"/></button>
                            </form>
                        </div>
                    </div>
                </nav>
                <div class="welcomebar">
                    <a href="./profile.php"><img src="./images/avatars/<?php echo $_SESSION["user"]["avatar"]; ?>" alt="<?php echo $_SESSION["user"]["full_name"]; ?>" width="32" height="32" /></a> Hi <strong><?php echo $_SESSION["user"]["full_name"]; ?></strong> (<a href="./profile.php"><?php echo $_SESSION["user"]["username"]; ?></a>)
                </div>
            </header>
            <!--
            <header class="content">
                <nav>
                    <div class="logo"><a href="dashboard.php"><img alt="Home" src="images/logo.png" /></a></div>
                    <ul>
                        <li><div><a href="dashboard.php">Dashboard</a></div></li><li><div><a href="profile.php">Profile</a></div></li><li><div><a href="index.php?logout">Logout</a></div></li>
                    </ul>
                    <div class="search">
                        <div id="searchwrapper">
                            <form action="search.php">
                                <input type="text" class="searchbox" name="q" placeholder="Enter task name here.." />
                                <select class="type">
                                    <option>All</option>
                                    <option>Username</option>
                                    <option>Title</option>
                                    <option>Task</option>
                                </select>
                                <button class=""><img src="images/find.png" alt="Search"/></button>
                            </form>
                        </div>
                    </div>
                </nav>
            </header>
        -->
            <div class ="content">