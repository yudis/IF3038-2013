<!DOCTYPE html>
<html>
	<head>
		<title>Search Result</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<div id="main-body-general">
			<div id="header">
				<?php
					include("header.php");
					require("../php/init_function.php");
				?>
			</div>
            <div><hr id="border"></div>
			<!--Body-->
			<div id="search-result-body">
				<?php
					$mode = $_POST['modesearch'];
					$text = $_POST['search_text'];
					switch($mode)
					{
						case "1":
							echo "<div id=\"main-dashboard\"><div id=\"dashboard-title\"><i><b>USER<br /></b></i></div><br><div><hr id=\"border-search\"></div><br>";
							$con = getConnection();
							$query = "SELECT * FROM user WHERE username LIKE '%$text%'";
							$result = mysqli_query($con,$query);
							while($row = mysqli_fetch_array($result)){
									echo "<div id=\"user-result\">
										<div id=\"user-result-image\">
											<a href=\"profile.php?username=".$row['username']."\"><img alt=\"Avatar\" id=\"photo\" src=\"../avatar/".$row['avatar']."\" width=\"100\" height=\"120\"/></a>
										</div>
										<div id=\"user-result-name\">
											<div id=\"dashboard-title\"><a href=\"profile.php?username=".$row['username']."\"><b>".$row['username']."</b></a></div><br>
											Full Name : ".$row['fullname']."<br>
											Joined On : ".$row['join']."
										</div>
									</div>";
							}
									
							echo "<div><hr id=\"border-search\"></div><br>
									<div id=\"dashboard-title\"><i><b>CATEGORY<br /></b></i></div><br>
									<div><hr id=\"border-search\"></div><br>";
							
							$query = "SELECT * FROM category WHERE categoryname LIKE '%$text%'";
							$result = mysqli_query($con,$query);
							while($row = mysqli_fetch_array($result)){
									echo "<div class=\"task-category-body\">
										<br>
										<div>
											<div id=\"category-title\"><b>".$row['categoryname']."</b></div>
										</div>
										<div class=\"kosong\">Created by : <i>".$row['username']."</i>, at ".$row['createddate']."</div>
										<ul>";
									$query2 = "SELECT * FROM task WHERE categoryid=".$row['categoryid'];
									$result2 = mysqli_query($con,$query2);
										while($row2 = mysqli_fetch_array($result2)){
											echo "<li><a href = \"task_page.php?taskid=".$row2['taskid']."\">".$row2['taskname']."</a></li>";
										}
									echo "	</ul>
										</div>";								
							}
									
							echo "  <div><hr id=\"border-search\"></div><br>
									<div id=\"dashboard-title\"><i><b>TASK<br /></b></i></div><br>
									<div><hr id=\"border-search\"></div><br>";
									
							$query3 = "SELECT * FROM task WHERE taskname LIKE '%$text%'";
							$result3 = mysqli_query($con,$query3);
							while($row3 = mysqli_fetch_array($result3)){		
								echo "	<div id=\"task-result\">
										<ul>
											<li><a href = \"task_page.php?taskid=".$row3['taskid']."\">".$row3['taskname']."</a><div class=\"task-tag\">submit by : <b><i>".$row3['username']."</i></b>, deadline : ".$row3['deadline'].", status : <b id=\"red-text\">".$row3['status']."</b></div>
											<br><div>
											<div class=\"task-tag\">Set as <a href=\"#\">Change Status</a></div></div><br><br>
											<div id=\"task-tag\">
												Tag :<br>";
											$query4 = "SELECT * FROM task_tag WHERE taskid=".$row3['taskid'];
											$result4 = mysqli_query($con,$query4);
											while($row4 = mysqli_fetch_array($result4)){
												$tagname = getTagname($row4['tagid']);
												echo "<u>$tagname</u> ";
											}
								echo "				</div>
											</li><br><br>
										</ul>
									</div>";
							}
							echo "</div>";	
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
									</div>";
									
									$con = getConnection();
							$query = "SELECT * FROM user WHERE username LIKE '%$text%'";
							$result = mysqli_query($con,$query);
							while($row = mysqli_fetch_array($result)){
									echo "<div id=\"user-result\">
										<div id=\"user-result-image\">
											<a href=\"profile.php?username=".$row['username']."\"><img alt=\"Avatar\" id=\"photo\" src=\"../avatar/".$row['avatar']."\" width=\"100\" height=\"120\"/></a>
										</div>
										<div id=\"user-result-name\">
											<div id=\"dashboard-title\"><a href=\"profile.php?username=".$row['username']."\"><b>".$row['username']."</b></a></div><br>
											Full Name : ".$row['fullname']."<br>
											Joined On : ".$row['join']."
										</div>
									</div>";
							}
									
								echo "</div>";
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
									</div>";
									
							$query = "SELECT * FROM category WHERE categoryname LIKE '%$text%'";
							$result = mysqli_query(getConnection(),$query);
							while($row = mysqli_fetch_array($result)){
									echo "<div class=\"task-category-body\">
										<br>
										<div>
											<div id=\"category-title\"><b>".$row['categoryname']."</b></div>
										</div>
										<div class=\"kosong\">Created by : <i>".$row['username']."</i>, at ".$row['createddate']."</div>
										<ul>";
									$query2 = "SELECT * FROM task WHERE categoryid=".$row['categoryid'];
									$result2 = mysqli_query(getConnection(),$query2);
										while($row2 = mysqli_fetch_array($result2)){
											echo "<li><a href = \"task_page.php?taskid=".$row2['taskid']."\">".$row2['taskname']."</a></li>";
										}
									echo "	</ul>
										</div>";								
							}
									
							echo "</div>";
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
									</div>";
									
							$query3 = "SELECT * FROM task WHERE taskname LIKE '%$text%'";
							$result3 = mysqli_query(getConnection(),$query3);
							while($row3 = mysqli_fetch_array($result3)){		
								echo "	<div id=\"task-result\">
										<ul>
											<li><a href = \"task_page.php?taskid=".$row3['taskid']."\">".$row3['taskname']."</a><div class=\"task-tag\">submit by : <b><i>".$row3['username']."</i></b>, deadline : ".$row3['deadline'].", status : <b id=\"red-text\">".$row3['status']."</b></div>
											<br><div>
											<div class=\"task-tag\">Set as <a href=\"#\">Change Status</a></div></div><br><br>
											<div id=\"task-tag\">
												Tag :<br>";
											$query4 = "SELECT * FROM task_tag WHERE taskid=".$row3['taskid'];
											$result4 = mysqli_query(getConnection(),$query4);
											while($row4 = mysqli_fetch_array($result4)){
												$tagname = getTagname($row4['tagid']);
												echo "<u>$tagname</u> ";
											}
								echo "				</div>
											</li><br><br>
										</ul>
									</div>";
							}	
									
							echo "</div>";
							break;
					}
				?>
			</div>
		</div>
	</body>
</html>