<!DOCTYPE html>
<?php
include_once("../php/loginchecker.php");
?>
<?php

if(isset($_SESSION['uname'])){
    $username = $_SESSION['uname'];
    print_r("session sekarang usernya".$username);
}
?>

<?php include '../php/datapengguna.php'?>

<html>
	
	<head>
		<link href='../css/desktop_style.css' rel='stylesheet' type='text/css'>
		<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
		<script type="text/javascript" src="../js/animation.js"> </script> 
                <script type="text/javascript" src="../js/edituser.js"> </script> 
                <script type="text/javascript" src="../js/editprofile.js"> </script> 
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
		<title> Eurilys </title>
	</head>
<?php
$datauser = datapengguna($username);

?>
	<body>
		<!-- Web Header -->
		<?php
			include_once("header.php");
		?>
		
		
		<!-- Web Content -->
                <?php foreach($datauser as $eachdata){?>
                     <section>
			<div id="navbar">
				<div id="short_profile">
					<img id="profile_picture" src="../file/<?php echo $eachdata['avatar']?>" alt="">
					<div id="profile_info">
						<?php echo $eachdata['fullname']?>
						<br><br>
						<div class="link_tosca" id="edit_profile_button" onclick="edit_profile(<?php echo $eachdata['username']?>)"> Edit Profile </div>
					</div> 
				</div>
				<div id="category_list">
					<div class="link_blue_rect" id="category_title"><a href="#" onclick="catchange(0)">All Categories </a> </div>
					<ul id="category_item">
						<li><a href="dashboard.html" onclick="catchange(1)" id="kuliah">Kuliah</a></li>
						<li><a href="dashboard.html" onclick="catchange(2)" id="proyek">Proyek</a></li>
						<li><a href="dashboard.html" onclick="catchange(3)" id="tugas">Tugas</a></li>
						<li><a href="dashboard.html" onclick="catchange(4)" id="lomba">Lomba</a></li>
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
						<img src="../file/<?php echo $eachdata['avatar']?>" alt="">
						<div id="namauser"><?php echo $eachdata['full_name']?></div>
					</div>
					
					<br/>
                                       Username :<?php echo $eachdata['username']?>
                                        <br>
					Email : <?php echo $eachdata['email']?>
					<br>
					Birthdate : <?php echo $eachdata['birthdate']?>

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
                <?php }?>
               
		
		<!-- Footer Section -->
		<div class="thin_line"></div>
		<footer>
			<div id="footer_container"> 
				<br><br>
				About &nbsp;&nbsp;&nbsp; FAQ &nbsp;&nbsp;&nbsp; Feedback &nbsp;&nbsp;&nbsp; Terms &nbsp;&nbsp;&nbsp; Privay &nbsp;&nbsp;&nbsp; Copyright 
				<br>
				Eurilys 2013
			</div>
		</footer>
	</body>

<!-- ini nanti jadiin footer -->
</html>