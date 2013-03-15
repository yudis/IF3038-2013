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
			
			
		</script>
	</head>
	<body>
		<div id="main-body-general">
			<!--Header-->
			<div id="header">
				<?php
					include("header.php");
				?>
			</div>
			<div><hr id="border"></div>
			<!--Body-->
		<div id="profile-page-body">
			<div id="profile-header">
				<div id="left-profile-header">
					<img alt="" id="photo" src="../avatar/<?php echo $_SESSION["userlistapp"]?>.png" width=235 height="240"/>
   					<img alt="" id="photo" src="../avatar/<?php echo $_SESSION["userlistapp"]?>.jpg" width=235 height="240"/>
				</div>
				<div id="right-profile-header">
					<h2><?php $username = $_GET['username'];
							  echo $username;
							  require('../php/init_function.php');
						      $user = getUser($username);?></h2>
					<br>
					<p>Joined on : <?php echo $user['join']?></p>
					<div>
						<div id="left-main-body"><p>About Me :</p></div>
						<div id="right-main-body"><a href="#" onClick="edit_aboutme()"><u><p>edit</p></u></a></div>
					</div>
					<div id="about">
						<div id="aboutme_show"><?php echo $user['aboutme']?></div>
       					<div id="aboutme_edit"><input type="text" id="aboutme_to_edit" size=80></div>
					</div>
				</div>
			</div>
			<div><hr id="border"></div>
			<div id="biodata">
				<div>
					<div id="left-profile-name"><p>Full Name : <?php echo $user['fullname']; ?></p></div>
					<div id="left-profile-newname"><p>Full Name : <?php echo $user['fullname']; ?></p></div>
					<div id="right-profile-editname"><a href="#"><u><p>edit</p></u></a></div>
				</div>
				<div>
					<div id="left-profile-birthday"><p>Birth Date : <?php echo $user['birthday'];?></p></div>
					<div id="left-profile-newbirthday"><p>Birth Date : <?php echo $user['birthday'];?></p></div>
					<div id="right-profile-editbirthday"><a href="#"><u><p>edit</p></u></a></div>
				</div>
				<div>
					<div id="left-profile-email"><p>Email : <i><?php echo $user['email'];?></i></p></div>
					<div id="left-profile-newemail"><p>Email : <i><?php echo $user['email'];?></i></p></div>
					<div id="right-profile-editemail"><a href="#"><u><p>edit</p></u></a></div>
				</div>
			</div>
			
			<div id="unfinished-task">
				<div>
					<div id="left-profile-unfinished"><h3>Unfinished Task</h3></div>
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
						$query = "SELECT taskid FROM assignee WHERE username='".$_SESSION['userlistapp']."'";
						$result = mysqli_query($con,$query);
						while($row = mysqli_fetch_array($result)){
							$task = getTask($row['taskid']);
							if(strcmp($task['status'],"UNCOMPLETE") == 0){
								echo "<li><a href = \"task_page.php?taskid=".$task['taskid']."\">".$task['taskname']."</a></li>";		
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
							$query = "SELECT taskid FROM assignee WHERE username='".$_SESSION['userlistapp']."'";
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