<!DOCTYPE html>
<html>
	<head>
		<title>Profile</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<script type="text/javascript" src="profile.js"></script>
		<script src="calendar.js"></script>
		<link href="calendar.css" rel="stylesheet">
	</head>
	<body onLoad="hidden_update_box()">
		<div id="main-body-general">
			<!--Header-->
			<div id="header">
				<?php
					include("header.php");
					require("../php/init_function.php");
					$user_show =  $_GET['username'];
					$avatar = getAvatar($user_show);
				?>
			</div>
			<div><hr id="border"></div>
			<!--Body-->
		<div id="profile-page-body">
			<div id="profile-header">
				<div id="left-profile-header">
					<img alt="Photo profile" id="photo" src="../avatar/<?php echo $avatar?>" width="250" height="325"/>
				</div>
				<div id="right-profile-header">
					<h2><?php $username = $_GET['username'];
							  echo $username;
						      $user = getUser($username);?></h2>
						
                        <?php
							if($username == $_SESSION['userlistapp']){
								echo "<div id=\"upload_button\"><a href=\"#\" onClick=\"edit_avatar('$user_show')\">Upload New Avatar</a></div>";
								echo "<div id=\"uploader\">";
								echo "<input type=\"file\" name=\"changeAvatar\" id=\"inputfileid\">";
								echo "</div>";
								echo "<div id=\"change_password\"><a href=\"#\" onClick=\"edit_password('$user_show')\">Change Password</a></div>";
								echo "<div id=\"password_form\">";
								echo "<div id=\"newpassword\">";
								echo "<div id=\"left-profile-body\">New Password</div><div id=\"right-profile-body\"> : <input type=\"text\" name=\"newPass\" id=\"newpasstext\"></div>";
								echo "</div>";
								echo "<br><br><br>";
								echo "</div>";
							}
						?>
						<br />
					<p>Joined on : <?php echo $user['join']?></p>
					<div>
						<div id="left-main-body"><p>About Me :</p></div>
						<div id="right-main-body">
                        	<?php 
                        		if($username == $_SESSION['userlistapp'] ){
									echo "<a href=\"#\" onClick=\"edit_aboutme('$user_show')\"><u><p>edit</p></u></a>";
								}else{
									echo "<br />";	
								}
							?>
                        </div>
					</div>
					<div id="about">
						<div id="aboutme_show"><?php echo $user['aboutme']?></div>
       					<div id="aboutme_edit"><input type="text" id="aboutme_to_edit" size=80></div>
					</div>
				</div>
			</div>
			<br><br>
			<div><hr id="border"></div>
			<div id="biodata">
				<div>
					<div id="left-profile-name"><p>Full Name : <?php echo $user['fullname']; ?></p></div>
					<div id="left-profile-newname"><p>Full Name : <input type="text" id="newname"></p></div>
					<div id="right-profile-editname">
                    	<?php 
                        		if($username == $_SESSION['userlistapp'] ){
									echo "<a href=\"#\" onclick=\"edit_fullname('$user_show')\"><u><p>edit</p></u></a>";
								}
						?>
                    </div>
				</div>
				<br><br><br>
				<div>
					<div id="left-profile-birthday"><p>Birth Date : <?php echo $user['birthday'];?></p></div>
					<div id="left-profile-newbirthday"><p>Birth Date : <input type="text" id="newbirthday" class="calendarSelectDate" name="textDeadline"/><div id="calendarDiv"></div></p></div>
					<div id="right-profile-editbirthday">
                    	<?php 
                        		if($username == $_SESSION['userlistapp'] ){
									echo "<a href=\"#\" onClick=\"edit_birthday('$user_show')\"><u><p>edit</p></u></a>";
								}
						?>
                    </div>
				</div>
				<br><br><br>
				<div>
					<div id="left-profile-email"><p>Email : <i><?php echo $user['email'];?></i></p></div>
					<div id="left-profile-newemail"><p>Email : <input type="text" id="newemail"></i></p></div>
					<div id="right-profile-editemail">
                    	<?php 
                        		if($username == $_SESSION['userlistapp'] ){
									echo "<a href=\"#\" onClick=\"edit_email('$user_show')\"><u><p>edit</p></u></a>";
								}
						?>
                    </div>
				</div>
			</div>
			<br><br><br>
			<div id="unfinished-task">
				<div>
					<div id="left-profile-unfinished"><h3>Unfinished Task</h3></div>
					<div id="right-profile-body"><p></p>
						<br />
						<br />
						<br />
						<br />
					</div>	
				</div>
				
				<div>
					<ul>
					<?php 
						$con = getConnection();
						$query = "SELECT distinct taskid FROM assignee WHERE username='".$user_show."'";
						$result = mysqli_query($con,$query);
						while($row = mysqli_fetch_array($result)){
							$task = getTask($row['taskid']);
							if(strcmp($task['status'],"UNCOMPLETE") == 0){
								echo "<li><a href=\"task_page.php?taskid=".$row['taskid']."\">".$task['taskname']."</a></li>";		
							}
						}
					?>
					</ul>
				</div>
			</div>
		
			<div id="finished-task">
				<div>
					<div id="left-profile-finished"><h3>Finished Task</h3></div>
					
					<div id="right-profile-body"><p></p>
						<br>
						<br>
						<br>
						<br>
					</div>	
				</div>
				
				<div>
					<ul>
						<?php 
							$con = getConnection();
							$query = "SELECT distinct taskid FROM assignee WHERE username='".$user_show."'";
							$result = mysqli_query($con,$query);
							while($row = mysqli_fetch_array($result)){
								$task = getTask($row['taskid']);
								if(strcmp($task['status'],"COMPLETE") == 0){
									echo "<li>".$task['taskname']."</li>";		
								}
							}
						?>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>