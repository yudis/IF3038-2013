<?php 
	session_start();
?>

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
				<select id="search_box_filter">
					<option> All </option>
					<option> User </option> <!-- username, email, nama lengkap, birthdate -->
					<option> Category </option>
					<option> Task </option> <!-- task name, tag, comment -->
				</select>
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
	<?php include 'navigation_bar.php'; ?>
	
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