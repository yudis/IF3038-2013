<?php

if (($_COOKIE['username'] == '') && ($_COOKIE['password'] == '')) {
    header('Location:../index.php') ; 
}

?>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="../css/desktop_style.css">
		<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
		<script type="text/javascript" src="../js/base_search.js"></script>
		<script type="text/javascript" src="../js/search.js"></script> 
		<script type="text/javascript" src="../js/animation.js"></script> 
		<script type="text/javascript" src="../js/profile.js"></script>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
		<title> do.Metro </title>
	</head>
	
	<body>
		<?php include 'connect.php'?>
		<?php
			$curr_username = $_COOKIE['username'];
			$sql_id = mysqli_query($conn, "SELECT id FROM user WHERE username LIKE '".$curr_username."'");
			$loginid = mysqli_fetch_array($sql_id);
			
			$result = mysqli_query($conn, "SELECT * FROM user WHERE id=".$loginid['id']);
			$login = mysqli_fetch_array($result);
			
			$prof_username = $_GET['user'];
			$sql_profid = mysqli_query($conn, "SELECT id FROM user WHERE username LIKE '".$prof_username."'");
			$profid = mysqli_fetch_array($sql_profid);
			
			$result = mysqli_query($conn, "SELECT * FROM user WHERE id=".$profid['id']);
			$profile = mysqli_fetch_array($result);
		?>
		<!-- Web Header -->
		<header>
			<div id="header_container"> 
				<div class="left">
					<a href="dashboard.php"> <img src="../img/logo.png" alt=""> </a>
				</div>
				<form id="search_form" action="search_results.php" method="get" class="sb_wrapper">
					<input id="search_box" name="search_query" type="text" placeholder="Search...">
					<button type="submit" id="search_button" value></button>
					<ul class="sb_dropdown">
						<li class="sb_filter">Filter your search</li>
						<li><input type="checkbox"/><label for="all"><strong>Select All</strong></label></li>
						<li><input type="checkbox" name="username" id="username" /><label for="Username">Username</label></li>
						<li><input type="checkbox" name="category" id="category" /><label for="Category">Category</label></li>
						<li><input type="checkbox" name="task" id="task" /><label for="Task">Task</label></li>
					</ul>
				</form>
				<div class="header_menu"> 
					<a href="dashboard.php"><div class="header_menu_button"> DASHBOARD </div></a>
					<?php
						echo "<a href='profile.php?user=".$curr_username."'>";
					?>
					<div class="header_menu_button current_header_menu">
						<?php echo "<img id='header_img' src='../img/avatar/".$login['avatar']."'>";?>
						<div id="header_profile">
							&nbsp;&nbsp;<?php echo $login['username'];?>
						</div>
					</div>
					</a>
					<a id="logout" href="logout.php"><div class="header_menu_button"> LOGOUT </div></a>
				</div>
			</div>
			<div class="thin_line"></div>
		</header>	
	
		
		<!-- Web Content -->
		<section>
			<div id="profile_left">
				<div id="upperprof">
					<?php echo "<img id='profpic' src='../img/avatar/".$profile['avatar']."'>";?>
					<div id="namauser">
					<?php
						echo $profile['fullname'];
					?>
					</div>
				</div>
				<div id="bio">
					<span id="left">
					<b>Username</b>
					<br/>
					<b>Email</b>
					<br/>
					<b>Birthdate</b>
					<br/>
					<?php if ($profid == $loginid) { ?>
						<button class="link_tosca" id="edit_profile_button" onclick="edit_profile()"> Edit Profile </button>
						<br>
					<?php } ?>
					</span>
					<span id="right">
						<?php
							echo " : " . $profile['username'];
						?>						
						<br/>
						<?php
							echo " : " . $profile['email'];
						?>						
						<br/>
						<?php
							echo " : " . $profile['birthdate'];
						?>
					</span>
					<br>
				</div>
				<div id="change_pass">
					<span id="left">
						<span id='change_password'>
							<?php if ($loginid == $profid) { ?>
								<button class='link_tosca' id='change_pass_button' onclick="change_pass()"> Change Password </button>
							<?php } ?>
						</span>
					</span>
					<span id="right">
						<span id='form_change_password'>
						</span>
					</span>
				</div>
				<div id="change_avatar">
					<form action="change_avatar.php" enctype="multipart/form-data" method="POST">
					<span id="left">
						<span id='change_ava'>
							<?php if ($loginid == $profid) { ?>
								<button class='link_tosca' id='change_avatar_button' onclick='change_avatar()'> Change Avatar </button>
							<?php } ?>
						</span>
					</span>
					<span id="right">
						<span id='new_avatar'>
						</span>
					</span>
					</form>
				</div>
			</div>
			<?php
				$sql_on = mysqli_query($conn, "SELECT * FROM assignee as a, tugas as t WHERE a.idtask = t.id AND a.nama_user LIKE '".$profile['username']."' AND status=0");
				$sql_done = mysqli_query($conn, "SELECT * FROM assignee as a, tugas as t WHERE a.idtask = t.id AND a.nama_user LIKE '".$profile['username']."' AND status=1");
			?>
			<div id="profile_right">
				<div class="headsdeh">Ongoing Tasks</div>
				<ul class="ul_none">
					<?php
						while ($row = $sql_on->fetch_assoc()) {
							echo "<li>".$row['namatask']."</li>";
						}
					?>
				</ul>
				<br>
				<div class="headsdeh">Finished Tasks</div>
				<ul class="ul_none">
					<?php
						while ($row = $sql_done->fetch_assoc()) {
							echo "<li>".$row['namatask']."</li>";
						}
					?>
				</ul>
			</div>
		</section>
		
		<!-- Footer Section -->
		<div class="thin_line"></div>
		<footer>
			<div id="footer_container"> 
				<br><br>
				About &nbsp;&nbsp;&nbsp; FAQ &nbsp;&nbsp;&nbsp; Feedback &nbsp;&nbsp;&nbsp; Terms &nbsp;&nbsp;&nbsp; Privay &nbsp;&nbsp;&nbsp; Copyright 
				<br>
				do.Metro 2013
			</div>
		</footer>
	</body>

<!-- ini nanti jadiin footer -->
</html>