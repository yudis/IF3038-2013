<!DOCTYPE html>
<html>
	<head>
		<title>TASK</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<script>
			function edit_deadline() {
				document.getElementById("deadline_edit").style.display = 'block';
				document.getElementById("deadline_done").style.display = 'none';
			}
			function finish_deadline() {
				document.getElementById("deadline_edit").style.display = 'none';
				document.getElementById("deadline_done").style.display = 'block';
			}
			function edit_assignee() {
				document.getElementById("assignee_edit").style.display = 'block';
				document.getElementById("assignee_done").style.display = 'none';
			}
			function finish_assignee() {
				document.getElementById("assignee_edit").style.display = 'none';
				document.getElementById("assignee_done").style.display = 'block';
			}
			function edit_tag() {
				document.getElementById("tag_edit").style.display = 'block';
				document.getElementById("tag_done").style.display = 'none';
			}
			function finish_tag() {
				document.getElementById("tag_edit").style.display = 'none';
				document.getElementById("tag_done").style.display = 'block';
			}
			function check_html5() {
				if (navigator.userAgent.indexOf('Chrome') != -1 || navigator.userAgent.indexOf('Opera') != -1){
					document.getElementById("date_html5").style.display = 'block';
					document.getElementById("date_html").style.display = 'none';
				} else {
					document.getElementById("date_html5").style.display = 'none';
					document.getElementById("date_html").style.display = 'block';
				}
			}
		</script>
	</head>
	<body onLoad="check_html5()">
		<div id="main-body-general">
			<!--Header-->
			<div id="header">
				<?php
					include("header.php");
					require('../php/init_function.php');
					$taskid = $_GET['taskid'];
					$task = getTask($taskid);
				?>
			</div>
			
			<div id="nomargin"><hr id="border"></div>
			
			<!--Body-->
			<div id="task-page-body">
				<div id="task-page-title">
					<div>
						<h1><?php echo $task['taskname']?></h1>
						Submit by <b><i><?php echo $task['username']?></i></b> at. <?php echo $task['createddate']?>
					</div>
					<div id="deadline_done">
						<div id="left-main-body">Deadline : <?php echo $task['deadline']?></div>
						<div id="right-main-body"><a href="#" onCLick="edit_deadline()"><u>edit</u></a></div>
					</div><br/>
					<div id="deadline_edit">
						<div id="left-main-body"><div id="label">Deadline : </div><input type="date" id="date_html5" />
						<div id="date_html">
							<select>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
								<option value="21">21</option>
								<option value="22">22</option>
								<option value="23">23</option>
								<option value="24">24</option>
								<option value="25">25</option>
								<option value="26">26</option>
								<option value="27">27</option>
								<option value="28">28</option>
								<option value="29">29</option>
								<option value="30">30</option>
								<option value="31">31</option>
							</select>&nbsp;
							<select>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
							</select>&nbsp;
							<select>
								<option value="1955">1955</option>
								<option value="1956">1956</option>
								<option value="1957">1957</option>
								<option value="1958">1958</option>
								<option value="1959">1959</option>
								<option value="1960">1960</option>
								<option value="1961">1961</option>
								<option value="1962">1962</option>
								<option value="1963">1963</option>
								<option value="1964">1964</option>
								<option value="1965">1965</option>
								<option value="1966">1966</option>
								<option value="1967">1967</option>
								<option value="1968">1968</option>
								<option value="1969">1969</option>
								<option value="1970">1970</option>
								<option value="1971">1971</option>
								<option value="1972">1972</option>
								<option value="1973">1973</option>
								<option value="1974">1974</option>
								<option value="1975">1975</option>
								<option value="1976">1976</option>
								<option value="1977">1977</option>
								<option value="1978">1978</option>
								<option value="1979">1979</option>
								<option value="1980">1980</option>
								<option value="1981">1981</option>
								<option value="1982">1982</option>
								<option value="1983">1983</option>
								<option value="1984">1984</option>
								<option value="1985">1985</option>
								<option value="1986">1986</option>
								<option value="1987">1987</option>
								<option value="1988">1988</option>
								<option value="1989">1989</option>
								<option value="1990">1990</option>
								<option value="1991">1991</option>
								<option value="1992">1992</option>
								<option value="1993">1993</option>
								<option value="1994">1994</option>
								<option value="1995">1995</option>
								<option value="1996">1996</option>
								<option value="1997">1997</option>
								<option value="1998">1998</option>
								<option value="1999">1999</option>
								<option value="2000">2000</option>
								<option value="2001">2001</option>
								<option value="2002">2002</option>
								<option value="2003">2003</option>
								<option value="2004">2004</option>
								<option value="2005">2005</option>
								<option value="2006">2006</option>
								<option value="2007">2007</option>
								<option value="2008">2008</option>
								<option value="2009">2009</option>
								<option value="2010">2010</option>
								<option value="2011">2011</option>
								<option value="2012">2012</option>
							</select>
						</div>              
						</div>
						<div id="right-main-body"><a href="#" onCLick="finish_deadline()"><u>done</u></a></div>
					</div>
                    <div>Status : <?php echo $task['status']?> <input name="changeStatus" type="button" value="Change Status"></div>
				</div>
				<div id="attachment">
					<div id="image-attachment">
						<div>
						<br>
						<br>
                        	<?php 
								$con = getConnection();
								$query = "SELECT filename FROM attachment WHERE taskid = $taskid";
								$result = mysqli_query($con,$query);
								while($row = mysqli_fetch_array($result)){
									$filename = $row['filename'];
									if(typeFile($filename) == 0){
										echo "<img id=\"screenshots\" src=\"../attachment/$filename\" alt=\"$filename\" title=\"$filename\"\">";
									}
								}
							?>
						</div>
					</div>
                    <div id="video-attachment">
                    	<?php 
								$con = getConnection();
								$query = "SELECT filename FROM attachment WHERE taskid = $taskid";
								$result = mysqli_query($con,$query);
								while($row = mysqli_fetch_array($result)){
									$filename = $row['filename'];
									if(typeFile($filename) == 1){
										echo "<video width=\"320\" height=\"240\" controls>";
										echo "<source src=\"../attachment/$filename\" type=\"video/mp4\">";
										echo "</video>";
									}
								}
						?>
					</div>
					<div id="other-attachment">
						<p>External File:</p>
						<ul>
                        	<?php 
								$con = getConnection();
								$query = "SELECT filename FROM attachment WHERE taskid = $taskid";
								$result = mysqli_query($con,$query);
								while($row = mysqli_fetch_array($result)){
									$filename = $row['filename'];
									if(typeFile($filename) == 2){
										echo "<li><a href=\"../attachment/$filename\">$filename</a></li>";
									}
								}
							?>
						<ul>
					</div>
				</div>
				<div>
					<div id="task-nomargin" name="1"><div id="assignee_done">
						<div id="left-main-body">Shared with : <i>
                        	<?php 
								$con = getConnection();
								$query = "SELECT username FROM assignee WHERE taskid = $taskid";
								$result = mysqli_query($con,$query);
								while($row = mysqli_fetch_array($result)){
									echo "<a href=\"profile.php?username=".$row['username']."\"><u>".$row['username']."</u></a> ";
								}
							?>
                        </i></div>
						<div id="right-main-body"><a href="#2" onClick="edit_assignee()"><u>edit</u></a></div>
					</div></div>
					<div id="assignee_edit" name="2">
						<div id="left-main-body">Shared with : <input type="text" list="assignee"/>
						<datalist id="assignee">
							<option value="dhanui">
							<option value="darara">
							<option value="kevinkw">
						</datalist>
						</div>
						<div id="right-main-body"><a href="#1" onClick="finish_assignee()"><u>done</u></a></div>
					</div>
					<br>
					<br>
					<div id="task-nomargin" name="3"><div id="tag_done">
						<div id="left-main-body">Tag : <i>
                        <?php 
								$con3 = getConnection();
								$result3 = mysqli_query($con3,"SELECT tagid FROM task_tag WHERE taskid = '".$taskid."'");
								while($row3 = mysqli_fetch_array($result3))
								{
									$tagname = getTagname($row3['tagid']);
									echo "<u>".$tagname."</u> ";
								}
							?>
                        </i></div>
						<div id="right-main-body"><a href="#3" onClick="edit_tag()"><u>edit</u></a></div>
					</div></div>
					<div id="tag_edit" name="4">
						<div id="left-main-body">Tag : <input type="text" /></div>
						<div id="right-main-body"><a href="#4" onClick="finish_tag()"><u>done</u></a></div>
					</div>
				</div>
			</div>
			
			<div id="nomargin"><hr id="border"></div>
			
			<!--Comment-->
			<div id="task-comment">
				<div id="user-comment">
					<p><b><?php echo getNComment($taskid);?></b></p>
					<div id="comment-list">
						<?php
                        	$con = getConnection();
							$query = "SELECT * FROM comment WHERE taskid = $taskid";
							$result = mysqli_query($con,$query);
							while($row = mysqli_fetch_array($result)){
 								echo " <div id=\"comment\">";
								echo " 	<div id=\"user-info\">";
								echo " 		<div id=\"left-comment-body\">";
								echo " 			<img src=\"../avatar/".getAvatar($row['username'])."\" width=\"50px\" height=\"50px\"/>";
								echo " 		</div>";
								echo " 		<div id=\"right-comment-body\">";
								echo " 			<b id=\"komentator\">".$row['username']."</b>";
								echo " 			<br>";
								echo " 			<b id=\"post-date\">Post at ".$row['createdate']."</b>";
								echo " 		</div>";
								echo " 	</div>";
								echo " 	<div id=\"comment-box\">";
								echo " 		<p>";
								echo $row['message'];
								echo " 		</p>";
								echo " 	</div>";
								echo " </div>";
							}
						?>
						
						<div id="new-comment"></div>
					</div>
				</div>
				<div id="add-comment">
					<p><b>Leave a comment</b></p>
					<form>
						<textarea id="textarea-comment" rows="8" cols="92" placeholder="Comment about this task..."></textarea>
						<div><button id="submit-comment" >Submit</button>&nbsp;</div>
					</form>
				</div>
			</div>
			
		</div>
	<script>
			function myFunction(e)
			{
				e.preventDefault();
				var x;
				var name = document.getElementById("textarea-comment").value;
				if (name!=null)
				{
					x="<div id=comment><div id=user-info><div id=left-comment-body><img src=../image/ecky.jpg width=50px height=50px"+
						"/></div><div id=right-comment-body><b id=komentator>meckyr</b><br><b id=post-date"+
						">Post at 4.30 AM, February 4th, 2013</b></div></div><div id=comment-box><p>"+name+"</p></div></div>";
					document.getElementById("new-comment").innerHTML=x;
				}
			}
			document.getElementById("submit-comment").addEventListener("click",myFunction);
		</script>
	</body>
</html>