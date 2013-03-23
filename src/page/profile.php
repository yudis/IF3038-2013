<!DOCTYPE html>
<html>
	<head>
		<title>Profile</title>
		<link rel="stylesheet" type="text/css" href="style.css">
        <script>
			function edit_aboutme(){
				document.getElementById("aboutme_edit").style.display = 'block';
				var val = document.getElementById("aboutme_show").innerHTML;
				document.getElementById("aboutme_to_edit").value = val;
				document.getElementById("aboutme_show").style.display = 'none';
				document.getElementById("right-main-body").innerHTML = "<div id=\"right-main-body\"><a href=\"#\" onClick=\"just_edit_aboutme()\"><u><p>done</p></u></a></div>";
			}
			function just_edit_aboutme(){
				document.getElementById("aboutme_edit").style.display = 'none';
				document.getElementById("aboutme_show").style.display = 'block';
				document.getElementById("right-main-body").innerHTML = "<div id=\"right-main-body\"><a href=\"#\" onClick=\"edit_aboutme()\"><u><p>edit</p></u></a></div>";
			}
			
			function edit_fullname(){
				document.getElementById("left-profile-newname").style.display = 'block';
				var val = document.getElementById("left-profile-name").innerHTML;
				var l = val.length;
				val = val.substring(15, l - 4);
				document.getElementById("newname").value = val;
				document.getElementById("left-profile-name").style.display = 'none';
				document.getElementById("right-profile-editname").innerHTML = "<a href=\"#\" onclick=\"just_edit_fullname()\"><u><p>done</p></u></a>";
			}
			function just_edit_fullname(){
				document.getElementById("left-profile-newname").style.display = 'none';
				document.getElementById("left-profile-name").style.display = 'block';
				document.getElementById("right-profile-editname").innerHTML = "<a href=\"#\" onclick=\"edit_fullname()\"><u><p>edit</p></u></a>";
			}
			
			function edit_birthday(){
				document.getElementById("left-profile-newbirthday").style.display = 'block';
				var val = document.getElementById("left-profile-birthday").innerHTML;
				var l = val.length;
				val = val.substring(16, l - 4);
				document.getElementById("newbirthday").value = val;
				document.getElementById("left-profile-birthday").style.display = 'none';
				document.getElementById("right-profile-editbirthday").innerHTML = "<a href=\"#\" onclick=\"just_edit_birthday()\"><u><p>done</p></u></a>";
			}
			function just_edit_birthday(){
				document.getElementById("left-profile-newbirthday").style.display = 'none';
				document.getElementById("left-profile-birthday").style.display = 'block';
				document.getElementById("right-profile-editbirthday").innerHTML = "<a href=\"#\" onclick=\"edit_birthday()\"><u><p>edit</p></u></a>";
			}
			
			
			function edit_email(){
				document.getElementById("left-profile-newemail").style.display = 'block';
				var val = document.getElementById("left-profile-email").innerHTML;
				var l = val.length;
				val = val.substring(14, l - 8);
				document.getElementById("newemail").value = val;
				document.getElementById("left-profile-email").style.display = 'none';
				document.getElementById("right-profile-editemail").innerHTML = "<a href=\"#\" onclick=\"just_edit_email()\"><u><p>done</p></u></a>";
			}
			function just_edit_email(){
				document.getElementById("left-profile-newemail").style.display = 'none';
				document.getElementById("left-profile-email").style.display = 'block';
				document.getElementById("right-profile-editemail").innerHTML = "<a href=\"#\" onclick=\"edit_email()\"><u><p>edit</p></u></a>";
			}
			
			function edit_avatar(){
				document.getElementById("uploader").style.display = 'block';
				document.getElementById("upload_button").innerHTML = "<a href=\"#\" onClick=\"just_edit_avatar()\">Save</a>";
			}
			function just_edit_avatar(){
				document.getElementById("uploader").style.display = 'none';
				document.getElementById("upload_button").style.display = 'block';
				document.getElementById("upload_button").innerHTML = "<a href=\"#\" onClick=\"edit_avatar()\">Upload New Avatar</a>";
			}
			
			function hidden_update_box(){
				document.getElementById("aboutme_edit").style.display = 'none';
				document.getElementById("left-profile-newemail").style.display = 'none';
				document.getElementById("left-profile-newname").style.display = 'none';
				document.getElementById("left-profile-newbirthday").style.display = 'none';
				document.getElementById("uploader").style.display = 'none';
			}
		</script>
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
								echo "<div id=\"upload_button\"><a href=\"#\" onClick=\"edit_avatar()\">Upload New Avatar</a></div>";
								echo "<div id=\"uploader\">";
								echo "<input type=\"file\" name=\"changeAvatar\">";
								echo "</div>";
								echo "<div id=\"change_password\"><a href=\"#\">Change Password</a></div>";
								echo "<div id=\"newpassword\">";
								echo "<div id=\"left-profile-body\">New Password</div><div id=\"right-profile-body\"> : <input type=\"text\" name=\"newPass\"></div>";
								echo "</div>";
								echo "<div id=\"confirmpassword\">";
								echo "<div id=\"left-profile-body\">Confirm Password</div><div id=\"right-profile-body\"> : <input type=\"text\" name=\"confirmPass\"></div>";
								echo "</div>";
							}
						?>
							
					<br><br>
					<p>Joined on : <?php echo $user['join']?></p>
					<div>
						<div id="left-main-body"><p>About Me :</p></div>
						<div id="right-main-body">
                        	<?php 
                        		if($username == $_SESSION['userlistapp'] ){
									echo "<a href=\"#\" onClick=\"edit_aboutme()\"><u><p>edit</p></u></a>";
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
									echo "<a href=\"#\" onclick=\"edit_fullname()\"><u><p>edit</p></u></a>";
								}
						?>
                    </div>
				</div>
				<br><br><br>
				<div>
					<div id="left-profile-birthday"><p>Birth Date : <?php echo $user['birthday'];?></p></div>
					<div id="left-profile-newbirthday"><p>Birth Date : <input type="text" id="newbirthday"></p></div>
					<div id="right-profile-editbirthday">
                    	<?php 
                        		if($username == $_SESSION['userlistapp'] ){
									echo "<a href=\"#\" onClick=\"edit_birthday()\"><u><p>edit</p></u></a>";
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
									echo "<a href=\"#\" onClick=\"edit_email()\"><u><p>edit</p></u></a>";
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
							$query = "SELECT taskid FROM assignee WHERE username='".$user_show."'";
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