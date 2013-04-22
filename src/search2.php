<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Todolist</title>
        <link rel="stylesheet" type="text/css" href="default.css" />
        <link rel="stylesheet" type="text/css" href="mediaqueries.css" />
    </head>
    <body onload='initializesearch()'>
        <div id="blanket"></div>
        <div class="page">
            <header class="content">
                <?php include 'header.php' ?>
            </header>
            <div class="content">
                <div class="sidebar">
                    <ul id="FilterKategori" class="nav">
                    <form action="">
                   	 	<input type="radio" name="searchmode" value="all" id="all_rb" checked>All<br>
                        <input type="radio" name="searchmode" value="username" id="username_rb">Username<br>
                       	<input type="radio" name="searchmode" value="category" id="category_rb">Category<br>
                        <input type="radio" name="searchmode" value="taskname" id="taskname_rb">Taskname<br>
                       	<input type="radio" name="searchmode" value="tag" id="tag_rb">Tag<br>
					</form>
                    </ul>
                </div>
                <div id="listTugas" class="main">
                    <h1 class="inlineblock">Search </h1>
					<div id = "searchtextfield">
                    	<input type="text" id="search_textfield" class="searchbox_textfield" name="q" value="" placeholder="Search.." />
						<button type="button"  name="submit" class="searchbox_submit_textfield" alt="search..." onClick="Searchmode()"/><img src="images/search.png" class="search_image_textfield"></button>
					</div>
                    <section id="main-F0">
                    </section>
                </div>
               
            </div>
            
            <footer class="content">
                This website is created solely for the purpose of fulfilling our college task.<br />
                IF3094 - Pemrograman Internet.
            </footer>
        </div>
        <script src="scripts/search.js" type="application/javascript"></script>
        <script type="text/javascript">
        <?php
			if ((ISSET($_GET['s']))&&(ISSET($_GET['m']))&&(ISSET($_GET['page'])))
			{
				echo "Searchbypage('".$_GET['s']."','".$_GET['m']."','".$_GET['page']."');";
			}else if ((ISSET($_GET['s']))&&(ISSET($_GET['m'])))
			{
				echo "Search('".$_GET['s']."','".$_GET['m']."');";
			}
		?>
		</script>	
    </body> 
</html>
