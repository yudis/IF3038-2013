<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width; initial-scale=1.0" />
        <title>Todolist</title>
        <link rel="stylesheet" type="text/css" href="default.css" />
        <link rel="stylesheet" type="text/css" href="mediaqueries.css" />
        <script src="scripts/helper.js" type="application/javascript"></script>
        <script src="scripts/popup.js" type="application/javascript"></script>
        <script src="scripts/dashboard.js" type="application/javascript"></script>
    </head>
    <body onload="initialize(true);">
        <div id="blanket"></div>
        <div id="popUpDiv">
            <h1>Create new category</h1>
            <div class="padding12px"><label for="txtNewKategori">Name</label>:<br />
                <input id="txtNewKategori" type="text" placeholder="eg: IF40XX" /></div>
            <br />
            <div class="padding12px">
                Priviledge users:<br />
                <ul class="tag">
                    <li>Abraham Krisnanda Santoso</li>
                    <li>Edward Samuel Pasaribu</li>
                    <li>Stefanus Thobi Sinaga</li>
                </ul>
            </div>
            <br />
            <div class="rightalign padding12px"><button onclick="popup('popUpDiv','blanket',300,600); NewKategori()">OK</button> <button onclick="popup('popUpDiv','blanket',300,600)">Cancel</button></div>
            <br />
        </div>
        <div class="page">
            <header class="content">
                <nav>
                    <div class="logo"><a href="dashboard.html"><img alt="Home" src="images/logo.png" /></a></div>
                    <ul>
                        <li><div><a href="dashboard.php">Dashboard</a></div></li><li><div><a href="profile.html">Profile</a></div></li><li><div><a href="index.html" onclick="signout()">Logout</a></div></li>
                    </ul>
                    <div class="search">
                        <div id="searchwrapper">
                            <form action="#">
                                <input type="text" class="searchbox" name="q" value="" placeholder="Enter task name here.." />
                                <input type="image" src="images/search.png" name="sumbit" class="searchbox_submit" alt="search..."/>
                            </form>
                        </div>
                    </div>
                </nav>
            </header>
            <div class="content">
				<div id="leftcolumn">
					<div class="sidebar" id="sidebar"></div>
				</div>
                <div id="listTugas" class="main">
                    <?php
						$username=$_GET["uname"];
						$kategori=$_GET["cat"];
						
						$con = mysql_connect('localhost', 'root', 'rootadmin');
						if (!$con)
						{
							die('Could not connect: ' . mysql_error());
						}
						
						mysql_select_db("progin_405_13510035", $con);
							
						if ($kategori == "all") {
							$iterator = 0;
							
							$sqlcat="SELECT DISTINCT category_name, category_id FROM category, task, task_incharge WHERE category.category_id = task.task_category AND task.task_id = task_incharge.task_id AND assignee = '".$username."'";
							
							$result = mysql_query($sqlcat);
							
							echo ("<h1 class='inlineblock'>Dashboard</h1> <button id='addTask' onclick='NewTask()'>add new task...</button>");
							
							while ($row = mysql_fetch_array($result)) {
								echo ("<section id='main-K".$iterator."'>
										<h2>".$row["category_name"]."</h2>");
							
								$sql="SELECT * FROM task, category WHERE task.assignee = '".$username."' AND task_category = '".$row["category_id"]."' AND task.task_category = category.category_id";
						
								$result2 = mysql_query($sql);
								
								while ($row2 = mysql_fetch_array($result2)) {
									echo ("<div class='tugas'><div><a href='tugas.html?id=".$row2["task_id"]."'>".$row2["task_name"]."</a></div>
											<div>Deadline: <strong>".$row2["deadline"]."</strong></div>
											<div>
												Tags: 
												<ul class='tag'>");
									
									$sqltag="SELECT DISTINCT tag.tag_name FROM tag, tasktag WHERE tasktag.task_id = '".$row2["task_id"]."' AND tag.tag_id = tasktag.tag_id";
								
									$result3 = mysql_query($sqltag);
									
									while ($row3 = mysql_fetch_array($result3)) {
									echo ("
										<li>".$row3["tag_name"]."</li>
										");
									}
									echo ("</ul></div></div>");
								}
								echo ("</section>");
								$iterator++;
							}	
						}
						else {
							$iterator = 0;
							
							$sqlcat="SELECT * FROM category WHERE category.category_id = '".$kategori."'";
							
							$result = mysql_query($sqlcat);
							
							echo ("<h1 class='inlineblock'>Dashboard</h1> <button id='addTask' onclick='NewTask()'>add new task...</button>");
							
							while ($row = mysql_fetch_array($result)) {
								echo ("<section id='main-K".$iterator."'>
										<h2>".$row["category_name"]."</h2>");
							
								$sql="SELECT * FROM task, category WHERE task.assignee = '".$username."' AND task_category = '".$row["category_id"]."' AND task.task_category = category.category_id";
						
								$result2 = mysql_query($sql);
								
								while ($row2 = mysql_fetch_array($result2)) {
									echo ("<div class='tugas'><div><a href='tugas.html?id=".$row2["task_id"]."'>".$row2["task_name"]."</a></div>
											<div>Deadline: <strong>".$row2["deadline"]."</strong></div>
											<div>
												Tags: 
												<ul class='tag'>");
									
									$sqltag="SELECT DISTINCT tag.tag_name FROM tag, tasktag WHERE tasktag.task_id = '".$row2["task_id"]."' AND tag.tag_id = tasktag.tag_id";
								
									$result3 = mysql_query($sqltag);
									
									while ($row3 = mysql_fetch_array($result3)) {
									echo ("
										<li>".$row3["tag_name"]."</li>
										");
									}
									echo ("</ul></div></div>");
								}
								echo ("</section>");
								$iterator++;
							}
						}		
						mysql_close($con);
					?>
                </div>
            </div>
            <footer class="content">
                This website is created solely for the purpose of fulfilling our college task.<br />
                IF3094 - Pemrograman Internet.
            </footer>
        </div>
    </body> 
</html>
