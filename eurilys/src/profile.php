<!DOCTYPE html>
<html>
	<head>
		<link href='../css/desktop_style.css' rel='stylesheet' type='text/css'>
		<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
		<script type="text/javascript" src="../js/edit_task.js"> </script> 
		<script type="text/javascript" src="../js/animation.js"> </script> 
		<script type="text/javascript" src="../js/catselector.js"> </script> 
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >		
		<title> Eurilys </title>
	</head>
	
	<body>
		<!-- Web Header -->
		<header>
			<div id="header_container"> 
				<div class="left">
					<a href="dashboard.php"> <img src="../img/logo.png" alt="logo"> </a>
				</div>
				<input id="search_box" type="text" placeholder="search...">
				<div class="header_menu"> 
					<div id="menu_dashboard" class="header_menu_button"> <a href="dashboard.php"> DASHBOARD </a>  </div>
					<div id="menu_profile" class="header_menu_button current_header_menu">  <a href="profile.php"> PROFILE </a> </div>
					<div id="menu_logout" class="header_menu_button"> <a id="logout" href="../index.php"> LOGOUT </a> </div>
				</div>
			</div>
			<div class="thin_line"></div>
		</header>	
		
<!-- Web Content -->
<section>
	<div id="navbar">
		<div id="short_profile">
			<img id="profile_picture" src="../img/avatar1.png" alt="">
			<div id="profile_info">
				Ruth Natasha 
				<br><br>
				<div class="link_tosca" id="edit_profile_button"> Edit Profile </div>
			</div> 
		</div>
		<div id="category_list">
			<div class="link_blue_rect" id="category_title"><a href="#" onclick="catchange(0)">All Categories </a> </div>
			<ul id="category_item">
				<li><a href="dashboard.php" onclick="catchange(1)" id="kuliah">Kuliah</a></li>
				<li><a href="dashboard.php" onclick="catchange(2)" id="proyek">Proyek</a></li>
				<li><a href="dashboard.php" onclick="catchange(3)" id="tugas">Tugas</a></li>
				<li><a href="dashboard.php" onclick="catchange(4)" id="lomba">Lomba</a></li>
			</ul>
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
				<img src="../img/avatar1.png" alt="">
				<div id="namauser">Ruth Nattassha</div>
			</div>
			<input type="file" id="profile_pic_upload" name="profile_picture"/>
			<br/><br/>
			Email : salvaterarug@gmail.com
			<br/>
			Birthdate : 16-07-1992

		</div>
		<div class="half_div">
			<div class="half_tall">
				<div class="headsdeh">Current Tasks:</div>
				<ul class="ul_none">
					<li>Tubes Progin 1</li>
					<li>Catatan Progin </li>
				</ul>
			</div>
			<div class="half_tall">
				<div class="headsdeh">Finished Tasks:</div>
				<ul class="ul_none">
					<li>Tugas Individu IMK</li>
					<li>Tugas Keamanan Informasi </li>
					<li>Tugas Besar AI </li>
				</ul>
			</div>
		</div>
		
	</div>
</section>
		
<?php include '../footer.php'; ?>	