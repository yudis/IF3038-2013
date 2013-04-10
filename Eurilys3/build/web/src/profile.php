<!DOCTYPE html>
<?php
	if(!isset($_COOKIE['name'])){
		header("Location: ../index.php");
	}
?>
<?php include 'connect.php'?>
<?php
	$result = mysql_query("SELECT * FROM user WHERE username ='".$_COOKIE['name']."'");
	$user = mysql_fetch_array($result);
?>
<html>
	
	<head>
		<link href='../css/desktop_style.css' rel='stylesheet' type='text/css'>
		<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
		<script type="text/javascript" src="../js/animation.js"> </script>
		<script type="text/javascript" src="../js/profil.js"> </script>		
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
		<title> Eurilys </title>
	</head>
	
	<body>
		<!-- Web Header -->
		<header>
			<div id="header_container"> 
				<div id="logo_container">
					<a href="dashboard.php"> <img src="../img/logo.png" alt=""> </a>
				</div>
				<input id="search_box" type="text" placeholder="Type here to search">
				<div id="header_menu"> 
					<div class="header_menu_button"> <a href="dashboard.php"> DASHBOARD </a>   </div>
					<div class="header_menu_button current_header_menu">  
					<a href="profile.php">
						<?php echo "<img id='header_img' src='../".$user['avatar']."'>";?>
						<div id="header_profile">
							&nbsp;&nbsp;<?php echo $user['username'];?>
						</div>					 
					</a> 
					</div>
					<div class="header_menu_button"> <a id="logout" href="../clear.php"> LOGOUT </a> </div>
				</div>
				
			</div>
		</header>	
	
		
		<!-- Web Content -->
		<section>
			<div id="navbar">
				<div id="short_profile">
					<?php 
						$image = '<img class = "left" id="profile_picture" src="../';
						$image.=$user['avatar'];
						$image.='">';
						echo $image;
					?>
					<div id="profile_info" class="left">
						Welcome,
						<br>
						<a href="profile.php"><?php echo $user['name']?></a>
					</div>
				</div>
				<div id="category_list">
					<div class="link_blue_rect" id="category_title"><a href="#" onclick="allCategory()">All Categories </a></div>
					<ul id="category_item">
					</ul>
					<div id="add_task_link"><a href='javascript:void(0)' onclick="getCategoryURL()"> + new task </a> </div>
					<div id="delete_category" onclick="deleteCategory()">- delete category</a></div>
					<div id="add_new_category" onclick="toggle_visibility('category_form');"> + new category </div>
					<div id="category_form">
						<div id="category_form_inner">	
							Category name : <br>
							<input type="text" id="add_category_name" value="">
							<br><br>
							Assignee(s) : <br>
							<input type="text" id="add_category_asignee_name" value="">
							<br><br>
							<div id="add_category_button" class="link_red" onclick="add_category()"> Add </div>
						</div>
					</div>
				</div>
			</div>
			<div id="dynamic_content">
				<div class="half_div">
					<div id="upperprof">
						<img id="profile_picture2" src="../img/avatar1.png" alt="">
						<div id="namauser">-</div>
					</div>
					<br/><br/>
					<form name="updateProfileForm" id="updateProfileForm" action="../updateProfile.php" method="post" target="_self" enctype="multipart/form-data">
						<a id="user_image"></a>
						<br/>
						User Name : <a id="user_name">-</a>
						<br/>
						Full Name : <a id="full_name">-</a>
						<br/>
						Email : <a id="user_email">-</a>
						<br/>
						Birthdate : <a id="user_bday">-</a>
						<a id="user_button"></a>
						<br/>
					</form>
				</div>
				<div class="half_div">
					<div class="half_tall">
						<div class="headsdeh">Current Tasks:</div>
						<ul id="currentTask" class="ul_none">
							
						</ul>
					</div>
					<div class="half_tall">
						<div class="headsdeh">Finished Tasks:</div>
						<ul id="finishedTask" class="ul_none">
							
						</ul>
					</div>
				</div>
				
			</div>
		</section>
		
		<!-- Footer Section -->
		<footer>
			<div id="footer_container"> 
				About &nbsp;&nbsp;&nbsp; FAQ &nbsp;&nbsp;&nbsp; Feedback &nbsp;&nbsp;&nbsp; Terms &nbsp;&nbsp;&nbsp; Privay &nbsp;&nbsp;&nbsp; Copyright 
				<br>
				Eurilys 2013
			</div>
		</footer>
	</body>
<script>
	var UpdateProfile = setInterval(function(){updateProfile()},300);
	var UpdateTask = setInterval(function(){updateProfileTask()},300);
	// initial value
	var fullname = "";
	var email = "";
	var bday = "";
	var pass = "";
</script>
	
</html>