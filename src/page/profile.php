<!DOCTYPE html>
<html>
	<head>
		<title>Profile</title>
		<link rel="stylesheet" type="text/css" href="style.css">
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
					<h2><?php echo $_SESSION['userlistapp'];
							  require('../php/init_function.php');
						      $user = getUser($_SESSION['userlistapp']);?></h2>
					<br>
					<p>Joined on : <?php echo $user['join']?></p>
					<div>
						<div id="left-main-body"><p>About Me :</p></div>
						<div id="right-main-body"><a href="#"><u><p>edit</p></u></a></div>
					</div>
					<div id="about">
						<p>
						<?php echo $user['aboutme']?>
						</p>
					</div>
				</div>
			</div>
			<div><hr id="border"></div>
			<div id="biodata">
				<div>
					<div id="left-profile-body"><p>Full Name : <?php echo $user['fullname']; ?></p></div>
					<div id="right-profile-body"><a href="#"><u><p>edit</p></u></a></div>
				</div>
				<div>
					<div id="left-profile-body"><p>Birth Date : <?php echo $user['birthday'];?></p></div>
					<div id="right-profile-body"><a href="#"><u><p>edit</p></u></a></div>
				</div>
				<div>
					<div id="left-profile-body"><p>Email : <i><?php echo $user['email'];?></i></p></div>
					<div id="right-profile-body"><a href="#"><u><p>edit</p></u></a></div>
				</div>
			</div>
			
			<div id="unfinished-task">
				<div>
					<div id="left-profile-body"><h3>Unfinished Task</h3></div>
					<div id="right-profile-body"><p><!--Sort by :
						<select name="Sort by">
							<option value="Auto">Auto</option>
							<option value="Name">Name</option>
							<option value="Date">Date</option>
						</select>--></p>
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
					<div id="left-profile-body"><h3>Finished Task</h3></div>
					
					<div id="right-profile-body"><p><!--Sort by :
						 <select name="Sort by">
							<option value="Auto">Auto</option>
							<option value="Name">Name</option>
							<option value="Date">Date</option>
						</select>---></p>
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