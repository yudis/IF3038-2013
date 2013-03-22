<!DOCTYPE html>
<html>
	<head>
		<title>Search Result</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<div id="main-body-general">
			<div id="header">
				<?php
					include("header.php");
				?>
			</div>
            <div><hr id="border"></div>
			<!--Body-->
			<div id="search-result-body">
				<?php
					$mode = $_POST['modesearch'];
					switch($mode)
					{
						case "1":
							echo "<div id=\"main-dashboard\">
				
									<div id=\"dashboard-title\"><i><b>USER<br /></b></i></div><br>
									<div><hr id=\"border-search\"></div><br>
									
									<div id=\"user-result\">
										<div id=\"user-result-image\">
											<a href=\"#\"><img alt=\"\" id=\"photo\" src=\"../avatar/meckyr.jpg\" width=\"100\" height=\"120\"/></a>
										</div>
										<div id=\"user-result-name\">
											<div id=\"dashboard-title\"><a href=\"#\"><b>meckyr</b></a></div><br>
											Full Name : Muhammad Ecky Rabani<br>
											Joined On : 0000-00-00
										</div>
									</div>
									
									<div><hr id=\"border-search\"></div><br>
									<div id=\"dashboard-title\"><i><b>CATEGORY<br /></b></i></div><br>
									<div><hr id=\"border-search\"></div><br>
									
									<div class=\"task-category-body\">
										<br>
										<div>
											<div id=\"category-title\"><b>Kategori 1</b></div>
										</div>
										<div class=\"kosong\">Created by : <i>meckyr</i>, at 2013-03-12 09:45:53</div>
										<ul>
											<li><a href = \"#\">Task 1</a></li>
											<li><a href = \"#\">Task 2</a></li>
											<li><a href = \"#\">Task 3</a></li>
										</ul>
									</div>
									<div class=\"task-category-body\">
										<br>
										<div>
											<div id=\"category-title\"><b>Kategori 2</b></div>
										</div>
										<div class=\"kosong\">Created by : <i>meckyr</i>, at 2013-03-12 09:45:53</div>
										<ul>
											<li><a href = \"#\">Task 1</a></li>
											<li><a href = \"#\">Task 2</a></li>
										</ul>
									</div>
									
									<div><hr id=\"border-search\"></div><br>
									<div id=\"dashboard-title\"><i><b>TASK<br /></b></i></div><br>
									<div><hr id=\"border-search\"></div><br>
									
									<div id=\"task-result\">
										<ul>
											<li><a href = \"#\">Taskname 1</a><div class=\"task-tag\">submit by : <b><i>meckyr</i></b>, deadline : 2013-03-12 22:50:17, status : <b id=\"red-text\">UNCOMPLETE</b></div>
											<br><div>
											<div class=\"task-tag\">Set as <a href=\"#\">Completed Task</a></div></div><br><br>
											<div id=\"task-tag\">
												Tag :<br>
												<u>informatika</u>
											</div>
											</li><br><br>
										</ul>
									</div>
								</div>";	
							break;
						case "2":
							echo "<div class=\"task-category-body\">
				
									<div id=\"dashboard-title\"><b>USER<br /></b><br /></div>
									
									<div id=\"sort\">	
										Sort by :
										<select name=\"Sort by\">
											<option value=\"Auto\">Auto</option>
											<option value=\"Name\">Name</option>
											<option value=\"Date\">Date</option>
										</select>&nbsp;			
									</div>
									
									<div id=\"user-result\">
										<div id=\"user-result-image\">
											<a href=\"#\"><img alt=\"\" id=\"photo\" src=\"../avatar/meckyr.jpg\" width=\"100\" height=\"120\"/></a>
										</div>
										<div id=\"user-result-name\">
											<div id=\"dashboard-title\"><a href=\"#\"><b>meckyr</b></a></div><br>
											Full Name : Muhammad Ecky Rabani<br>
											Joined On : 0000-00-00
										</div>
									</div>
									
									<div id=\"user-result\">
										<div id=\"user-result-image\">
											<a href=\"#\"><img alt=\"\" id=\"photo\" src=\"../avatar/meckyr.jpg\" width=\"100\" height=\"120\"/></a>
										</div>
										<div id=\"user-result-name\">
											<div id=\"dashboard-title\"><a href=\"#\"><b>meckyr</b></a></div><br>
											Full Name : Muhammad Ecky Rabani<br>
											Joined On : 0000-00-00
										</div>
										<br>
									</div>
									
								</div>";
							break;
						case "3":
							echo "<div class=\"task-category-body\">
				
									<div id=\"dashboard-title\"><b>CATEGORY<br /></b><br /></div>
									
									<div id=\"sort\">	
										Sort by :
										<select name=\"Sort by\">
											<option value=\"Auto\">Auto</option>
											<option value=\"Name\">Name</option>
											<option value=\"Date\">Date</option>
										</select>&nbsp;			
									</div>
									
									<div class=\"task-category-body\">
										<br>
										<div>
											<div id=\"category-title\"><b>Kategori 1</b></div>
										</div>
										<div class=\"kosong\">Created by : <i>meckyr</i>, at 2013-03-12 09:45:53</div>
										<ul>
											<li><a href = \"#\">Task 1</a></li>
											<li><a href = \"#\">Task 2</a></li>
											<li><a href = \"#\">Task 3</a></li>
										</ul>
									</div>
									
									<div class=\"task-category-body\">
										<br>
										<div>
											<div id=\"category-title\"><b>Kategori 2</b></div>
										</div>
										<div class=\"kosong\">Created by : <i>meckyr</i>, at 2013-03-12 09:45:53</div>
										<ul>
											<li><a href = \"#\">Task 1</a></li>
											<li><a href = \"#\">Task 2</a></li>
										</ul>
									</div>
									
									<div class=\"task-category-body\">
										<br>
										<div>
											<div id=\"category-title\"><b>Kategori 3</b></div>
										</div>
										<div class=\"kosong\">Created by : <i>meckyr</i>, at 2013-03-12 09:45:53</div>
										<ul>
										</ul>
									</div>
									
								</div>";
							break;
						case "4":
							echo "<div id=\"main-dashboard\">
									<div id=\"dashboard-title\"><b>TASK<br /></b><br /></div>
									<div id=\"sort\">	
										Sort by :
										<select name=\"Sort by\">
											<option value=\"Auto\">Auto</option>
											<option value=\"Name\">Name</option>
											<option value=\"Date\">Date</option>
										</select>&nbsp;			
									</div>
									
									<div id=\"task-result\">
										<ul>
											<li><a href = \"#\">Taskname 1</a><div class=\"task-tag\">submit by : <b><i>meckyr</i></b>, deadline : 2013-03-12 22:50:17, status : <b id=\"red-text\">UNCOMPLETE</b></div>
											<br><div>
											<div class=\"task-tag\">Set as <a href=\"#\">Completed Task</a></div></div><br><br>
											<div id=\"task-tag\">
												Tag :<br>
												<u>informatika</u>
											</div>
											</li><br><br>
										</ul>
									</div>
									
									<div id=\"task-result\">
										<ul>
											<li><a href = \"#\">Taskname 2</a><div class=\"task-tag\">submit by : <b><i>meckyr</i></b>, deadline : 2013-03-12 22:50:17, status : <b id=\"red-text\">UNCOMPLETE</b></div>
											<br><div>
											<div class=\"task-tag\">Set as <a href=\"#\">Completed Task</a></div></div><br><br>
											<div id=\"task-tag\">
												Tag :<br>
												<u>informatika</u>
											</div>
											</li><br>
										</ul>
									</div>
								</div>";
							break;
					}
				?>
			</div>
		</div>
	</body>
</html>